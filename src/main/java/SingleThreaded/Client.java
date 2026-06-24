package SingleThreaded;

import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client {

    public void run() throws IOException {

        String host = "localhost";
        int port = 8010;

        Socket socket = new Socket(host, port);

        System.out.println("Connected to Server");

        PrintWriter toServer =
                new PrintWriter(
                        socket.getOutputStream(), true);

        BufferedReader fromServer =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));

        toServer.println("Hello from Client");

        String response = fromServer.readLine();

        System.out.println(response);

        socket.close();
    }

    public static void main(String[] args) {

        try {

            Client client = new Client();
            client.run();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}