import java.io.*;
import java.net.*;

public class UppercaseServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error: Must include port number as argument. (Should be: java UppercaseServer.java <port number>)");
            System.exit(1);
        }
        int portNumber = 0;
        try {
            portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Argument must be valid port number between 2000 and 10000.");
            System.exit(1);
        }
        if (portNumber <= 2000 || portNumber >= 10000) {
            System.err.println("Port must be between 2000 and 10000.");
            System.exit(1);
        }

        System.out.println("Server is running on port " + portNumber + ". Waiting for client connection...");

        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()))
        ) {
            System.out.println("Client connected.");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                out.println(inputLine.toUpperCase());
            }
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
