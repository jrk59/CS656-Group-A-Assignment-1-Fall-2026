import java.io.*;
import java.net.*;

public class UppercaseClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                    "Error: Must include host name and port number. (Should be: java UppercaseClient <host name> <port number>)");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to server. Type a message and press Enter:");
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                String response = in.readLine();
                if (response == null) {
                    System.out.println("Server has disconnected.");
                    break;
                }
                System.out.println("Server: " + response);
            }
        } catch (UnknownHostException e) {
            System.err.println("Can't find host " + hostName);
        } catch (IOException e) {
            System.err.println("Couldn't connect to " + hostName + ": " + e.getMessage());
        }
    }
}
