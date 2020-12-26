package Network.Event;

import java.io.Serializable;

// Сообщеает клиенту о подключении игрока к игре

// Сервер клиенту
public class AnotherPlayerConnectedEvent extends Event implements Serializable {

    public AnotherPlayerConnectedEvent(String player_name) {
        super("another_player_connected");
        this.player_name = player_name;
    }

    public String player_name;
}
