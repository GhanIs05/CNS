import java.net.*;
import java.io.*;

public class DHSClient {
    public static void main(String[] args) {
        try {
            String pstr, gstr, Astr;
            String serverName = "localhost";
            int port = 8088;

            // Declare p, g, and Key of client as integers
            int p = 23;
            int g = 9;
            int a = 4;
            long Adash;
            double serverB; // Use double to handle floating-point numbers

            // Establish the connection
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            // Sends the data to the client
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            pstr = Integer.toString(p);
            out.writeUTF(pstr); // Sending p
            gstr = Integer.toString(g);
            out.writeUTF(gstr); // Sending g

            // Calculate A using integer exponentiation and modulo
            long A = (long) Math.pow(g, a) % p;
            Astr = Long.toString(A);
            out.writeUTF(Astr); // Sending A

            // Client's Private Key
            System.out.println("From Client : Private Key = " + a);

            // Accepts the data from the server
            DataInputStream in = new DataInputStream(client.getInputStream());
            String serverBStr = in.readUTF();
            serverB = Double.parseDouble(serverBStr); // Parse serverB as double
            System.out.println("From Server : Public Key = " + serverB);

            // Calculate the shared secret key using modular exponentiation
            Adash = (long) Math.pow(serverB, a) % p;
            System.out.println("Secret Key to perform Symmetric Encryption = " + Adash);

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

