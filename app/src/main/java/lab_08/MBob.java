package lab_08;

import java.net.*;
import java.io.*;

public class MBob {
    public static void main(String[] args)
    {
        try {
            // Define the parameters for Diffie-Hellman key exchange.
            String pstr, gstr, Astr;
            int port = 8088;  // The port number on which the server is listening.

            // Public parameters: 
            int p = 23;  // A prime number 'p' used for the exchange.
            int g = 9;   // A generator 'g' modulo 'p'. This is a public value.
            
            // Client's private key 'a' (secret key, should never be shared).
            int a = 4;  

            // Variables to store calculated values and results.
            double Adash, Bdash;

            // 1. Establish the connection to the server.
            System.out.println("Connecting to Bob on port " + port);
            Socket client = new Socket("Bob", port);  // Connect to the server
            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            // 2. Send public parameters 'p', 'g' and 'A' (the client's public key) to the server.
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            pstr = Integer.toString(p);  // Convert 'p' to string
            out.writeUTF(pstr);  // Sending 'p' to the server

            gstr = Integer.toString(g);  // Convert 'g' to string
            out.writeUTF(gstr);  // Sending 'g' to the server

            // Calculate the client's public key 'A' using the formula: A = g^a % p
            double A = ((Math.pow(g, a)) % p);  // A = g^a mod p
            Astr = Double.toString(A);  // Convert 'A' to string
            out.writeUTF(Astr);  // Send 'A' to the server

            // 3. Output the private key 'a' for the client (just for understanding, not sent).
            System.out.println("From Client : Private Key = " + a);

            // 4. Receive the server's public key 'B'.
            DataInputStream in = new DataInputStream(client.getInputStream());
            Bdash = Double.parseDouble(in.readUTF());  // Read server's public key 'B'
            System.out.println("From Server : Public Key = " + Bdash);

            // 5. Compute the shared secret key using the formula: Adash = B^a % p
            // This is where the client calculates the shared secret key using the server's public key 'B' and its private key 'a'.
            Adash = ((Math.pow(Bdash, a)) % p);  // Calculate the shared secret key
            System.out.println("Secret Key to perform Symmetric Encryption = " + Adash);

            // 6. Close the client connection after completing the exchange.
            client.close();  // Close the connection to the server.

        } catch (Exception e) {
            // Catch and display any exceptions that may occur during the communication process.
            e.printStackTrace();
        }
    }
}
