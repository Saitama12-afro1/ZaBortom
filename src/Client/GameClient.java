package Client;

import Network.ChatTCPConnection;
import Network.Event.AddToGameEvent;
import Network.Event.ErrorEvent;
import Network.Event.Event;
import Network.Event.RequestGameEvent;
import Network.GameTCPConnection;
import Network.GameTCPConnectionListener;
import sun.rmi.transport.tcp.TCPConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class GameClient implements GameTCPConnectionListener {

    public static void main(String[] args) {
        GameClient client1 = new GameClient("NectoT");
        GameClient client2 = new GameClient("Vivarlee");
    }

    private static final String IP_ADDR="localhost";
    private static final int PORT=8189;

    private GameTCPConnection tcpConnection;

    private LinkedBlockingQueue<Event> eventQueue;

    private String player_name;

    // Аргумент здесь временный (здесь всё временно, ну да ладно)
    public GameClient(String player_name) {
        this.player_name = player_name;
        try {
            this.tcpConnection = new GameTCPConnection(this,IP_ADDR,PORT);
        } catch (IOException e) {
            System.err.println("Connection exception: " + e);
        }
        this.eventQueue = new LinkedBlockingQueue<>(200); // рандомный размер очереди

        this.tcpConnection.sendEvent(new RequestGameEvent(this.player_name)); // Просим добавить в сессию

        Event event = getEventFromQueue(); // Ожидаем подтверждение о добавлении в сессию
        if (!(event instanceof AddToGameEvent)) {
            System.out.println(player_name + " ожидал ивент AddToGameEvent");
            return;
        }
        AddToGameEvent e = (AddToGameEvent)event;
        System.out.print(player_name + ": ");
        for (String name : e.players_names) {
            System.out.print(name + " ");
        }
    }

    @Override
    public void onConnectionReady(GameTCPConnection tcpConnection) {
        System.out.println("Connection ready...");
        tcpConnection.start();
    }

    @Override
    public void onDisconnect(GameTCPConnection tcpConnection) {
        System.err.println("Connection closed...");
    }

    @Override
    public void onException(GameTCPConnection tcpConnection, Exception e) {
        System.err.println("TCPConnection exception: "+ e);
    }

    @Override
    public void onReceiveEvent(Event event) {
        if (event instanceof ErrorEvent) {
            System.err.println("Error received: " + ((ErrorEvent)event).message);
            return;
        }
        putEventInQueue(event);
    }


    public void putEventInQueue(Event event) {
        eventQueue.offer(event);
    }

    public Event getEventFromQueue() {
        try {
            return eventQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("Thread interrupted while waiting to take from event queue");
            return null;
        }
    }
}
