package MultiThreaded;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        int port = 8010;

        ExecutorService executor =
                Executors.newFixedThreadPool(10);

        try {

            ServerSocket serverSocket =
                    new ServerSocket(port);

            System.out.println(
                    "Thread Pool Server Started on Port "
                            + port);

            while (true) {

                Socket socket =
                        serverSocket.accept();

                System.out.println(
                        "Client Connected : "
                                + socket.getRemoteSocketAddress());

                executor.submit(() -> {

                    try {

                        PrintWriter toClient =
                                new PrintWriter(
                                        socket.getOutputStream(),
                                        true);

                        toClient.println(
                                "Hello from Thread Pool Server");

                        socket.close();

                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                });
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            executor.shutdown();
        }
    }
}