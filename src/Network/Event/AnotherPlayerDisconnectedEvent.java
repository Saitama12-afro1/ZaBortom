package Network.Event;

import java.io.Serializable;

// Сообщает клиенту о дисконнекте другого игрока

// Сервер клиенту
public class AnotherPlayerDisconnectedEvent extends Event implements Serializable {

    public AnotherPlayerDisconnectedEvent(String player_name) {
        super("another_player_disconnected");
        this.player_name = player_name;
    }

    public String player_name;
}
