import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Server implements Runnable{

 private String Ip;
 private int port;
 private boolean running=false;
 private Selector selector;
 private ServerSocket serverSocket;
 private ByteBuffer buffer;

  public Server(int port, int bufferSize)
  {
   this.port= port;
   this.buffer=ByteBuffer.allocate(bufferSize);
  }

 public void start()
 {
  new Thread(this).start();
 }
 @Override
 public void run()
 {
  running=true;
  while(running)
  {
   try {
    int client = selector.select();
    if(client==0)
    continue;
    Set<SelectionKey> keys = selector.selectedKeys();
    Iterator<SelectionKey> it= keys.iterator();

    while (it.hasNext())
    {
     SelectionKey key=(SelectionKey)it.next();

     if((key.readyOps() & SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT) {
      Socket socket = serverSocket.accept();

      System.out.println("connecion from: " + socket);
     }
    }



   }
   catch (IOException e) {
    e.printStackTrace();
   }
  }
 }
}
