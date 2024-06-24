import java.io.IOException;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Create a UDP socket
            socket = new DatagramSocket(); // Client socket

            // Initialize buffers for sending and receiving data
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer = new byte[1024];

            // Initialize InetAddress and port for server
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;

            while (true) {
                // Read message from client console
                System.out.print("Client: ");
                String sendMessage = new String(System.console().readLine());

                // Convert message to bytes
                sendBuffer = sendMessage.getBytes();

                // Send message to server
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
                socket.send(sendPacket);

                // Receive message from server
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                // Convert received data to string
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + receivedMessage);
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
