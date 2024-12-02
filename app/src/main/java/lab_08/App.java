package lab_08;

import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(6666);  
            Socket s = ss.accept();//establishes connection and waits for the client
            System.out.println("Connected");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}