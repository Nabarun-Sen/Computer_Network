import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage;
            String clientMessage;

            while (true) {
                System.out.print("Client: ");
                clientMessage = inFromUser.readLine();
                outToServer.println(clientMessage);
                if (clientMessage.equals("bye")) {
                    break;
                }

                serverMessage = inFromServer.readLine();
                if (serverMessage.equals("bye")) {
                    break;
                }
                System.out.println("Server: " + serverMessage);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
