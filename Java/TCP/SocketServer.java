import java.io.*;
import java.net.*;

public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server listening on port 1234...");
            
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            String message = in.readLine();
            System.out.println("Received from client: " + message);
            
            out.println("Message received by server: " + message);
            
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}