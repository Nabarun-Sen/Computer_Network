import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // Connect to the server running on localhost, port 9999
            Socket socket = new Socket("localhost", 9999);

            // Create input and output streams for communication with the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send messages to the server

            out.println("Hello, Server!");
            out.println("How are you?");
            out.println("Exit"); // Sending "Exit" will close the server

            // Receive and print responses from the server
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Server response: " + response);
                if (response.equals("Exit"))
                    break;
            }

            // Close streams and socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}