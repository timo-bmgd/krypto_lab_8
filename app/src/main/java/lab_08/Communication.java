package lab_08;

import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.math.BigInteger;

public class Communication {

    int clientport;
    int serverport;
    ServerSocket ss;
    Socket s;
    Socket clientSocket;
    DataInputStream dis;

    int publickey_N = 111;
    int publickey_G = 3;


    //Constructor to start the server
    public Communication(int serverport, int clientport) {
        this.serverport = serverport;
        this.clientport = clientport;
    }

    public int[] getPublicKeyNG() {
        return new int[]{publickey_N, publickey_G};
    }

    public String getMessage() {
        try {
            // If the server or socket is running, close it
            if(this.ss != null){
                this.ss.close();
            }
            if(this.s != null){
                this.s.close();
            }
            this.ss = new ServerSocket(serverport); //An diesem Punkt bei Alice kommt es zum Bind Fehler
            this.s = ss.accept();
            System.out.println("Connected");
            this.dis = new DataInputStream(this.s.getInputStream());
            String str = (String) this.dis.readUTF();
            System.out.println("received a message= " + str);
            //Close connection
            this.ss.close();
            this.s.close();
            return str;
        } catch (Exception e) {
            System.out.println(e);
            return "error"+e;
        }
    }

    public void sendMessage(String message){
        try {
            //If the client is not connected, do it
            if(this.clientSocket != null){
                this.clientSocket.close();
            }
            this.s = new Socket("localhost", clientport);
            DataOutputStream dos = new DataOutputStream(this.clientSocket.getOutputStream());
            dos.writeUTF(message);
            System.out.println("sent a message = " + message);
            //Close connection
            this.clientSocket.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Could not send message = " + message);

        }
    }

    public static void main(String[] args) {
        Communication communication = new Communication(6666, 6666);
    }
}