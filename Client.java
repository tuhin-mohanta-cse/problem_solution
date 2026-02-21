import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "192.168.1.127";
        int port = 5000;

        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server.");

            // Send to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true
            );

            // Receive from server
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            Scanner scanner = new Scanner(System.in);
            String message;

            while (true) {
                System.out.print("Enter message: ");
                message = scanner.nextLine();

                out.println(message);
                System.out.println(in.readLine());

                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Client closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}