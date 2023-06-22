import java.net.Socket;
import java.io.*;
import java.util.*;

public class client {
    Socket socket;
    BufferedReader br ;
    PrintWriter out;
     public client()
     {
        try {
             System.out.println("Sending request to server ");
             socket = new Socket("127.0.0.1",7777);
             System.out.println("Connection established");
             br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             out = new PrintWriter(socket.getOutputStream());
             startReading();
             startWriting();
        } catch (Exception e) {
            // TODO: handle exception
        }
     }
     public void startReading()
     {
        //continously reads the data from the client
        Runnable r1=()->{
            System.out.println("Reader started");
            
           try{
            while(true)
            {
                String message = br.readLine();
                if(message.equals("Abort"))
                {
                    System.out.println("Server has ended the chat");
                    break;
                }
                System.out.println("Server: "+ message);
            }
        }
        catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
        }
         

        };
         new Thread(r1).start();
     }
      public void startWriting()
      {
        // sends the data to client 
            Runnable r2=()->{
                System.out.println("Writer started...");
                 while(true)
                 {
                    try {
                         BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                         String content = br1.readLine();
                         out.println(content);
                         out.flush();

                    } catch (Exception e) {
                        // TODO: handle exception
                       
                        System.exit(0);
                    }
                 }
               
                

            };
             new Thread(r2).start();
      }
     public static void main(String[] args) {
        System.out.println(" this is client");
        new client();
     }
    
}
