import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            // Create a server socket on port 9999
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server started, waiting for client...");

            // Listen for incoming connections
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            // Create input and output streams for communication with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read input from the client and echo it back
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received from client: " + inputLine);
                out.println(inputLine); // Echo back to the client
            }

            // Close streams and sockets
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}