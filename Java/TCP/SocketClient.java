import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server...");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            out.println("Hello from client!");
            
            String response = in.readLine();
            System.out.println("Server response: " + response);
            
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}