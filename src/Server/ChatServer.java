package Server;

import Network.TCPConnection;
import Network.TCPConnectionListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer implements TCPConnectionListener
{
 public static void main(String[] args) throws IOException
 {
  new ChatServer();
 }
private final ArrayList<TCPConnection> connections = new ArrayList<>();
 private ChatServer()
 {
  System.out.println("Server running...");
  try(ServerSocket serverSocket = new ServerSocket(8189))
  {
   while(true)
   {
    try{
     new TCPConnection(this,serverSocket.accept());
    } catch (IOException e){System.out.println("TCP exception: "+ e);}
   }
  } catch (IOException e) {throw new RuntimeException(e); }
 }


 @Override
 public synchronized void onConnectionReady(TCPConnection tcpConnection) {
connections.add(tcpConnection);
sendToAllConnections("Client connected: "+tcpConnection);
 }

 @Override
 public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
 sendToAllConnections(value);
 }

 @Override
 public synchronized void onDisconnect(TCPConnection tcpConnection) {
  connections.remove(tcpConnection);
  sendToAllConnections("Client disconnected: "+ tcpConnection);
 }

 @Override
 public synchronized void onException(TCPConnection tcpConnection, Exception e) {
 System.out.println("TCPConnection exception: "+ e);
 }
 private void sendToAllConnections(String value){
  System.out.println(value);
  final int cnt = connections.size();
  for(int i =0;i<cnt;i++) connections.get(i).sendString(value);
 }
}


