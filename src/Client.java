import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client
{
 public static void main(String[] args)
 {
  try(Socket socket = new Socket())
  {
   socket.connect(new InetSocketAddress("25.62.47.143",8189),20000);
   Scanner scanner = new Scanner(socket.getInputStream());
   while(scanner.hasNextLine())
   {
    System.out.println(scanner.nextLine());
   }
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}