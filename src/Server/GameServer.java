package Server;

import Main.Game_obj;
import Network.ChatTCPConnection;
import Network.Event.*;
import Network.GameTCPConnection;
import Network.GameTCPConnectionListener;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import sun.rmi.transport.tcp.TCPChannel;
import sun.rmi.transport.tcp.TCPConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

// GameServer принимает запросы на соединение от клиентов,
// соединяет их (создавая новый GameTCPConnection)
// и начинает новые игровые сессии с помощью класса Session;
// Одновременно с этим он обрабатывает события ещё не подключенных к игровой сессии клиентов.

public class GameServer implements GameTCPConnectionListener {
    public static void main(String[] args) {
        new GameServer();
    }

    private final ArrayList<GameTCPConnection> connections = new ArrayList<>();
    private LinkedBlockingQueue<Event> eventQueue;

    private ArrayList<Session> sessions;

    public GameServer() {
        System.out.println("Server running...");

        this.eventQueue = new LinkedBlockingQueue<>(200); // размер рандомный
        this.sessions = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            while (connections.size() < 13) { // Так, временное ограничение на всякий случай
                try {
                    new GameTCPConnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCP exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Server shut down...");
    }

    public void putEventInQueue(Event event) {
        eventQueue.offer(event); // Ивенты не попадают в очередь, если их там слишком много
    }

    public Event getEventFromQueue() {
        try {
            return eventQueue.take();
        }
        catch (InterruptedException e) { // Хз, может нужно кидать этот ексепшн из метода
            return null;
        }
    }

    @Override
    public synchronized void onConnectionReady(GameTCPConnection tcpConnection) {
        System.out.println("New connection made");
        connections.add(tcpConnection);
        tcpConnection.start();
    }


    @Override
    public synchronized void onDisconnect(GameTCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections(new AnotherPlayerDisconnectedEvent("Placeholder_name"));
    }

    @Override
    public synchronized void onException(GameTCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private void sendToAllConnections(Event event) {
        System.out.println(event.type);
        for (GameTCPConnection connection : connections) connection.sendEvent(event);
    }

    @Override
    public void onReceiveEvent(Event event) {
        if (event instanceof ErrorEvent) {
            System.err.println("Error received: " + ((ErrorEvent)event).message);
            return;
        }

        // Тест пока
        if (event instanceof RequestGameEvent) {
            for (Session session : sessions) { // Это конечно так себе распределение по сессиям, но временное решение же
                if (!session.isFull()) {
                    RequestGameEvent e = (RequestGameEvent)event;
                    session.putEventInQueue(event); // Дальше всё делает Session

                    connections.remove(event.tcpConnection); // Теперь session занимается этим подключением

                    return;
                }
            }
            Session session = new Session();
            sessions.add(session);
            session.putEventInQueue(event); // Дальше всё делает Session
            session.start();
        }
    }

    // Класс Session отвечает за проведение конкретной игры с конкретными игроками.
    // Отслеживание ходов и информирование о ходе игры происходит с помощью событий

    class Session extends Thread implements GameTCPConnectionListener {
        Session(ArrayList<GameTCPConnection> connections, String[] player_names) {
            game = new Game_obj();
            this.players = new ArrayList<>();
            for (int i = 0; i < connections.size(); i++) {
                this.players.add(new Player(connections.get(i), player_names[i]));
            }
            this.eventQueue = new LinkedBlockingQueue<>(200);
        }

        Session() {
            game = new Game_obj();
            this.players = new ArrayList<>();
            this.eventQueue = new LinkedBlockingQueue<>(200);
        }

        public Player addPlayer(GameTCPConnection tcpConnection, String player_name) {
            Player new_player = new Player(tcpConnection, player_name);
            this.players.add(new_player);
            return new_player;
        }

        @Override
        public void run() {
            super.run();

            while(players.size() < 2 && !playersReady()) {
                Event event = getEventFromQueue();
                if (event instanceof PlayerReadyEvent) {
                    Player ready_player = getPlayer(event.tcpConnection);
                    ready_player.ready = true;
                }
                else if (event instanceof RequestGameEvent) {
                    Player player = addPlayer(event.tcpConnection,((RequestGameEvent)event).player_name);
                    String[] player_names = new String[players.size()];
                    for (int i = 0; i < player_names.length; i++) {
                        player_names[i] = players.get(i).player_name;
                    }
                    player.sendEvent(new AddToGameEvent(player_names));
                }
            }

            started = true;
            System.out.println("The game started! Players are: ");
            for (Player player : players) {
                System.out.println(player.player_name);
            }

            while (!interrupted()) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                // stuff
            }

            System.out.println("Session concluded");
        }

        @Override
        public synchronized void onException(GameTCPConnection tcpConnection, Exception e) {
            GameServer.this.onException(tcpConnection, e);
        }

        @Override // Ну вообще здесь этот метод не нужен и не должен вызываться
        public void onConnectionReady(GameTCPConnection tcpConnection) {

        }

        @Override
        public void onDisconnect(GameTCPConnection tcpConnection) {
            for (Player player : players) {
                if (tcpConnection.equals(player)) {
                    players.remove(player);
                    break;
                }
            }

            if (started) {
                interrupt();
            }
        }

        public void putEventInQueue(Event event) {
            eventQueue.offer(event);
        }

        public Event getEventFromQueue() {
            try {
                return eventQueue.take();
            }
            catch (InterruptedException e) { // Хз, может нужно кидать этот ексепшн из метода
                return null;
            }
        }

        @Override
        public void onReceiveEvent(Event event) {
            if (event instanceof ErrorEvent) {
                System.err.println("Error received: " + ((ErrorEvent)event).message);
                return;
            }

            putEventInQueue(event);
        }

        ArrayList<Player> players;
        Game_obj game;
        boolean started = false;

        private LinkedBlockingQueue<Event> eventQueue;

        // Класс Player - расширение GameTCPConnection, помимо информации о соединении содержит имя игрока,
        // принадлежащего игроку GamePerson и другие вспомогательные переменные и функции. Класс Session работает
        // с Player, а не с GameTCPConnection (ну мб всё поменяется, мне откуда это знать 26 декабря)
        class Player extends GameTCPConnection {
            public Player(GameTCPConnection tcpConnection, String player_name) {
                super(tcpConnection);
                this.player_name = player_name;
                this.ready = false;
            }

            Game_obj.GamePerson person;
            String player_name;
            boolean ready;
        }

        boolean playersReady() {
            if (players.size() == 0) {
                return false;
            }

            for (Player player : players) {
                if (!player.ready) {
                    return false;
                }
            }
            return true;
        }

        Player getPlayer(GameTCPConnection tcpConnection) throws RuntimeException {
            for (Player player : players) {
                if (player.equals(tcpConnection)) {
                    return player;
                }
            }
            throw new RuntimeException("Player not found");
        }

        boolean isFull() {
            return players.size() == 6;
        }
    }

}


