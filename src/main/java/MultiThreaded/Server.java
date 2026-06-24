package MultiThreaded;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        int port = 8010;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("MultiThreaded Server Started on Port " + port);

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println(
                        "Client Connected : "
                                + socket.getRemoteSocketAddress());

                Thread thread = new Thread(() -> {

                    try {

                        PrintWriter toClient =
                                new PrintWriter(
                                        socket.getOutputStream(),
                                        true);

                        toClient.println(
                                "Hello from MultiThreaded Server");

                        socket.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });

                thread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}