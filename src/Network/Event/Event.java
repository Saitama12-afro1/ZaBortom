package Network.Event;

import Network.GameTCPConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Event implements Serializable {

    public Event(String type) {
        this.type = type;
    }

    public String type;
    transient public GameTCPConnection tcpConnection;

    public void setTCPConnection(GameTCPConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }
}
