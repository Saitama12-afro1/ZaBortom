package Network.Event;

import Network.GameTCPConnection;

import java.io.Serializable;

// Клиент серверу
public class PlayerReadyEvent extends Event implements Serializable {

    public PlayerReadyEvent() {
        super("player_ready");
    }
}
