package lab_08;

import java.io.*;
import java.net.*;

public class Communication {

    int clientport;
    int serverport;
    ServerSocket ss;
    Socket s;
    DataInputStream dis;

    //Constructor to start the server
    public Communication(int serverport, int clientport) {
        this.serverport = serverport;
        this.clientport = clientport;
    }

    public String getMessage() {
        try {
            this.ss = new ServerSocket(serverport);
            this.s = ss.accept();
            System.out.println("Connected");
            this.dis = new DataInputStream(this.s.getInputStream());
            String str = (String) this.dis.readUTF();
            System.out.println("message= " + str);
            return str;
        } catch (Exception e) {
            System.out.println(e);
            return "error"+e;
        }
    }

    public void sendMessage(String message){
        try {
            DataOutputStream dos = new DataOutputStream(this.s.getOutputStream());
            dos.writeUTF("Hello");
            System.out.println("sent a message = " + message);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Could not send message = " + message);

        }
    }

    public static void main(String[] args) {

    }
}