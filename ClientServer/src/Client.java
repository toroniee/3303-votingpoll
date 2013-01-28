// Client.java

import java.io.*;
import java.net.*;

public class Client {

   private Socket streamSocket;
   private BufferedReader in;
   private PrintWriter out;

   public Client()
   {
      try {
         // Bind a socket to any available port on the local host machine. 
    	 streamSocket = new Socket("127.0.0.1", 1234);
      } catch (UnknownHostException e1) {
          System.err.println("Don't know about host");
          System.exit(1);
     } catch (IOException e2) {
          System.err.println("Couldn't get port 5000");
          System.exit(1);
     } 
     try {
    	 out = new PrintWriter(streamSocket.getOutputStream(), true);
    	 in = new BufferedReader( new InputStreamReader( streamSocket.getInputStream()));
     } catch (IOException e2) {
       System.err.println("Couldn't get I/O connection");
       System.exit(1);
  }    	 
   

   }
   
   public void close()
   {
	   try {
		   out.close();
		   in.close();
		   streamSocket.close();   
	   } catch (IOException e) { 
		   System.err.println("Couldn't get I/O for the connection");
           System.exit(1);
	   }	   
   }

   public void sendAndReceive(String s )
   {
      try {
    	  out.println(s);
    	  System.out.println("Client Rxd: " + in.readLine());
      } catch (IOException e) { 
    	  System.err.println("Couldn't get I/O for the connection");
    	  System.exit(1);
      }
   }

   public static void main(String args[])
   {
      Client c = new Client();
      for (int i=0; i<10; i++)
      {
    	  c.sendAndReceive("Is anyone there?");
      }
      c.close();
   }
}
