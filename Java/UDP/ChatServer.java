import java.io.IOException;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Create a UDP socket
            socket = new DatagramSocket(12345); // Server socket listening on port 12345
            System.out.println("Server is running...");

            // Initialize buffers for sending and receiving data
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer = new byte[1024];

            // Initialize InetAddress for client
            InetAddress clientAddress = null;
            int clientPort;

            while (true) {
                // Receive message from client
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                // Extract client's address and port
                clientAddress = receivePacket.getAddress();
                clientPort = receivePacket.getPort();

                // Convert received data to string
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client: " + receivedMessage);

                // Read message from server console
                System.out.print("Server: ");
                String sendMessage = new String(System.console().readLine());

                // Convert message to bytes
                sendBuffer = sendMessage.getBytes();

                // Send message to client
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
