import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage;
            String serverMessage;

            while (true) {
                clientMessage = inFromClient.readLine();
                if (clientMessage.equals("bye")) {
                    break;
                }
                System.out.println("Client: " + clientMessage);

                System.out.print("Server: ");
                serverMessage = inFromUser.readLine();
                outToClient.println(serverMessage);
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
