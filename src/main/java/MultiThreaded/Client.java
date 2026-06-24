package MultiThreaded;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        for(int i=1;i<=10;i++){

            new Thread(() -> {

                try{

                    Socket socket =
                            new Socket("localhost",8010);

                    BufferedReader reader =
                            new BufferedReader(
                                    new InputStreamReader(
                                            socket.getInputStream()));

                    System.out.println(
                            reader.readLine());

                    socket.close();

                }catch(Exception e){
                    e.printStackTrace();
                }

            }).start();
        }
        try {

            String host = "localhost";
            int port = 8010;

            Socket socket =
                    new Socket(host, port);

            BufferedReader fromServer =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            String response =
                    fromServer.readLine();

            System.out.println(response);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}