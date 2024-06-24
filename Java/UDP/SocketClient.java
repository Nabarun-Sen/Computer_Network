import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Create a UDP socket
            socket = new DatagramSocket();

            InetAddress serverIPAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            while (true) {
                System.out.print("Enter message to send to server (type 'exit' to quit): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                // Convert message to bytes
                byte[] sendData = message.getBytes();

                // Create packet to send to server
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, serverPort);

                // Send packet to server
                socket.send(sendPacket);

                // Receive response from server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                // Convert received data to string and print
                String modifiedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("From server: " + modifiedMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
            scanner.close();
        }
    }
}
