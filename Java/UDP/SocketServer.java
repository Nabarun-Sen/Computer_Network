import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        
        try {
            // Create a UDP socket
            socket = new DatagramSocket(9876);

            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while (true) {
                // Receive data from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                // Extract client's IP address and port
                InetAddress clientIPAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Convert received data to string
                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + sentence);

                // Process the received data (in this example, we just convert it to uppercase)
                String capitalizedSentence = sentence.toUpperCase();

                // Convert the processed data to bytes
                sendData = capitalizedSentence.getBytes();

                // Send the processed data back to client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
