package lab_08;

import java.io.*;
import java.net.*;

public class Server {

    int port = 6667;
    ServerSocket ss;
    Socket s;
    DataInputStream dis;

    public void startServer() {
        try {
            this.ss = new ServerSocket(port);
            this.s = ss.accept();// establishes connection
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getMessage() {
        try {
            this.dis = new DataInputStream(this.s.getInputStream());
            String str = (String) this.dis.readUTF();
            System.out.println("message= " + str);
            return str;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
        server.getMessage();
        server.getMessage();
    }
}