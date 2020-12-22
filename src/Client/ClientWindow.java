package Client;

import Network.TCPConnection;
import Network.TCPConnectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientWindow extends JFrame implements ActionListener, TCPConnectionListener {

 private static final String IP_ADDR="localhost";
 private static final int PORT=8189;
 private static final int WIDTH = 600;
 private static final int HEIGHT = 400;

 public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
 @Override
 public void run() {
  new ClientWindow();
 }
});
 }
  private final JTextArea log = new JTextArea();
  private final JTextField fieldNickname = new JTextField("Nick");
  private final JTextField fieldInput = new JTextField();

  private TCPConnection connection;

   private ClientWindow(){
 setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 setSize(WIDTH,HEIGHT);
 setLocationRelativeTo(null);

 log.setEditable(false);
 log.setLineWrap(true);
 add(log, BorderLayout.CENTER);

 fieldInput.addActionListener(this);
 add(fieldInput, BorderLayout.SOUTH);
 add(fieldNickname, BorderLayout.NORTH);

 setVisible(true);
    try {
     connection = new TCPConnection(this,IP_ADDR,PORT);
    } catch (IOException e) {
     printMsg("Connection exception: "+ e);
    }
   }

 @Override
 public void actionPerformed(ActionEvent e) {
String msg = fieldInput.getText();
if(msg.equals("")) return;
fieldInput.setText(null);
connection.sendString(fieldNickname.getText()+": "+msg);
 }

 @Override
 public void onConnectionReady(TCPConnection tcpConnection) {
  printMsg("Connection ready...");
 }

 @Override
 public void onReceiveString(TCPConnection tcpConnection, String value) {
  printMsg(value);
 }

 @Override
 public void onDisconnect(TCPConnection tcpConnection) {
  printMsg("Connection closed...");
 }

 @Override
 public void onException(TCPConnection tcpConnection, Exception e) {
 printMsg("Connection exception: "+ e);
 }

 private synchronized void printMsg(String msg){
    SwingUtilities.invokeLater(new Runnable() {
     @Override
     public void run() {
      log.append(msg+ "\n");
      log.setCaretPosition(log.getDocument().getLength());
     }
    });
   }
}
