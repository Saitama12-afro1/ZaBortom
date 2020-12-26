package Network;

import Network.Event.*;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Objects;

public class GameTCPConnection extends Thread {
    private final Socket socket;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;
    private ArrayList<GameTCPConnectionListener> eventListeners;

    // Авто-генерация классная штука, тут короче если сокеты совпадают у двух Connection, то они считаются равными
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameTCPConnection)) return false;
        GameTCPConnection that = (GameTCPConnection) o;
        return socket.equals(that.socket) &&
                input.equals(that.input) &&
                output.equals(that.output);
    }

    // тоже авто-генерация, вряд ли пригодится, но вроде вместе с equals нужно перегружать и этот метод
    @Override
    public int hashCode() {
        return Objects.hash(socket, input, output);
    }

    // Конструктор копирования, чё. Новый объект ссылается на те же поля
    public GameTCPConnection(GameTCPConnection tcpConnection) {
        this.socket = tcpConnection.socket;
        this.eventListeners = new ArrayList<>();
        this.eventListeners.addAll(tcpConnection.eventListeners);
        this.input = tcpConnection.input;
        this.output = tcpConnection.output;
    }

    public GameTCPConnection(GameTCPConnectionListener eventListener, String ipAddr, int port) throws IOException {
        this(eventListener, new Socket(ipAddr, port));
    }

    public GameTCPConnection(GameTCPConnectionListener eventListener, Socket socket) throws IOException {
        this.eventListeners = new ArrayList<>();
        this.eventListeners.add(eventListener);
        this.socket = socket;
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());

        // Только первый слушатель узнаёт об успешном подключении
        eventListener.onConnectionReady(GameTCPConnection.this);
    }

    @Override
    public void run() {
        super.run();
        try {
            int timeout_time = 500;
            this.socket.setSoTimeout(timeout_time); // в милисекундах
            while (!isInterrupted()) {
                try {
                    Event event = (Event) input.readObject();
                    receiveEvent(event);
                } catch (ClassNotFoundException e) {
                    sendEvent(new ErrorEvent("GameTCPConnection couldn't read the event"));
                    for (GameTCPConnectionListener listener : eventListeners) {
                        listener.onException(GameTCPConnection.this, e);
                    }
                } catch (SocketTimeoutException e) {
                    // Возникает каждые timeout_time милисекунд
                }
            }
        } catch (StreamCorruptedException e) { // По идее возникает, когда readObject не успевает прочесть объект за timeout_time
            for (GameTCPConnectionListener eventListener : eventListeners) {
                eventListener.onException(GameTCPConnection.this, e);
            }
        } catch (IOException e) {
            for (GameTCPConnectionListener eventListener : eventListeners) {
                eventListener.onException(GameTCPConnection.this, e);
            }
        } finally {
            disconnect();
        }
    }

    public void addEventListener(GameTCPConnectionListener listener) {
        eventListeners.add(listener);
    }

    public void removeEventListener(GameTCPConnectionListener listener) {
        eventListeners.remove(listener);
    }

    public synchronized void disconnect() {
        for (GameTCPConnectionListener eventListener : eventListeners) {
            eventListener.onDisconnect(GameTCPConnection.this);
        }
        try {
            socket.close();
        } catch (IOException e) {
            for (GameTCPConnectionListener eventListener : eventListeners) {
                eventListener.onException(GameTCPConnection.this, e);
            }
        }
    }

    // Отсылает на другой конец сокета ивент
    public synchronized void sendEvent(Event event) {
        try {
            output.writeObject(event);
            output.flush();
        } catch (IOException e) {
            for (GameTCPConnectionListener eventListener : eventListeners) {
                eventListener.onException(GameTCPConnection.this, e);
            }
            disconnect();
        }
    }

    // Получает с другого конца сокета ивент
    public synchronized void receiveEvent(Event event) {
        event.setTCPConnection(this);

        for (GameTCPConnectionListener eventListener : eventListeners) {
            eventListener.onReceiveEvent(event);
        }
    }

    @Override
    public String toString() {
        return "TCPConnection: " + socket.getInetAddress() + ": " + socket.getPort();
    }

}