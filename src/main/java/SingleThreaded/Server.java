package SingleThreaded;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server {

    public void run() throws IOException {

        int port = 8010;

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server started on port " + port);

        Socket socket = serverSocket.accept();


        System.out.println("Client connected: "
                + socket.getRemoteSocketAddress());

        BufferedReader fromClient =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));

        PrintWriter toClient =
                new PrintWriter(
                        socket.getOutputStream(), true);

        String message = fromClient.readLine();

        System.out.println("Client says: " + message);

        toClient.println("Hello from Server");

        System.out.println("Client connected: "
                + socket.getRemoteSocketAddress());

        socket.close();
        serverSocket.close();
    }

    public static void main(String[] args) {

        try {

            Server server = new Server();
            server.run();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}