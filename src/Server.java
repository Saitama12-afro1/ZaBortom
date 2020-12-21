import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
 public static void main(String[] args) throws IOException
 {
  try (ServerSocket serverSocket = new ServerSocket(8189);
       Socket socket = serverSocket.accept())
  {
   Scanner scanner = new Scanner(socket.getInputStream());
   PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
   writer.println("hello");
  }
 }
}
