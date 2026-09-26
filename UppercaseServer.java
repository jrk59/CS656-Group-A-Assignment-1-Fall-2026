import java.io.*;
import java.net.*;

public class UppercaseServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error: Must include port number. (java UppercaseServer <port number>)");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        if (portNumber < 2000 || portNumber > 10000) {
            System.err.println("Port must be between 2000 and 10000.");
            System.exit(1);
        }

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine.toUpperCase());
            }
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
