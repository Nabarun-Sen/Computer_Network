import java.io.*;
import java.net.*;
 
public class FactorialClient {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 12345;
 
        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Enter commands (FACTORIAL <number>, FIBONACCI <number>): ");
            String userInput;
            while ((userInput = stdIn.readLine()) != null && !userInput.equalsIgnoreCase("exit")) {
                out.println(userInput);
                System.out.println("Response from server: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }
}