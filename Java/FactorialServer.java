import java.io.*;
import java.net.*;
 
public class FactorialServer {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started and listening on port " + port);
 
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new ClientHandler(clientSocket).start();
        }
    }
 
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
 
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
 
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
 
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    int ch;
                    String[] tokens = inputLine.split(" ");
                    String command = tokens[0];
                    String output = "Invalid command";
 
                    try {
                        switch (command.toUpperCase()) {
                            case "FACTORIAL":
                                int number = Integer.parseInt(tokens[1]);
                                output = String.valueOf(factorial(number));
                                break;
                            case "FIBONACCI":
                                number = Integer.parseInt(tokens[1]);
                                output = fibonacci(number).toString();
                                break;
                        }
                    } catch (Exception e) {
                        output = "Error processing command";
                    }
 
                    out.println(output);
                }
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
        private long factorial(int n) {
            if (n <= 1) return 1;
            else return n * factorial(n - 1);
        }
 
        private String fibonacci(int n) {
            if (n <= 1) return String.valueOf(n);
            int a = 0, b = 1, c;
            StringBuilder sb = new StringBuilder("0 1");
            for (int i = 2; i < n; i++) {
                c = a + b;
                sb.append(" ").append(c);
                a = b;
                b = c;
            }
            return sb.toString();
        }
    }
}