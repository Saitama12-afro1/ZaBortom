package Network.Event;

// Сообщает серверу о желании начать игру

import Network.GameTCPConnection;

import java.io.Serializable;

// Клиент серверу
public class RequestGameEvent extends Event implements Serializable {

    public RequestGameEvent(String player_name) {
        super("request_game");
        this.player_name = player_name;
    }

    public String player_name;
}
