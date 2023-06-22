import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;
public class Server {
    ServerSocket server;
        Socket socket;
        BufferedReader br ;
        PrintWriter out;
    public Server()
    { 
        try{
       server = new ServerSocket(7777);  
       System.out.println("Server is ready for connnection");
       System.out.println("Waiting...");
       socket= server.accept();
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
        startReading();
        startWriting();
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
                    System.out.println("Client has ended the chat");
                    break;
                }
                System.out.println("Client: "+ message);
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
                try{
                 while(true)
                 {
                   
                         BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                         String content = br1.readLine();
                         out.println(content);
                         out.flush();

                    } 
                }
                    
                 catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                 }
               
                

            };
             new Thread(r2).start();
      }
     public static void main(String[] args) {
        System.out.println(" this is server.. going to start server");
        new Server();
     }
    
}
