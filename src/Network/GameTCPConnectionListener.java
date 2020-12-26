package Network;

import Network.Event.Event;

public interface GameTCPConnectionListener {
    void onConnectionReady(GameTCPConnection tcpConnection);
    void onReceiveEvent(Event event); // tcpConnection хранится в ивенте
    void onDisconnect(GameTCPConnection tcpConnection);
    void onException(GameTCPConnection tcpConnection, Exception e);
}
