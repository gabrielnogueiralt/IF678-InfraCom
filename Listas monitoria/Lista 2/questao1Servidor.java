package primeira;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class questao1Servidor {

    public static void main(String[] args) throws InterruptedException {
        int port = 8877;

        while (true) {
            try {
                ServerSocket tmpSocket = new ServerSocket(port);
                System.out.println("Wainting client...");
                Socket socket = tmpSocket.accept();
                long time = System.currentTimeMillis();
                socket.setSoTimeout(10000);
                while (socket.isConnected()) {    
                    if ((System.currentTimeMillis() - time) <= 10000) {
                        InputStreamReader in = new InputStreamReader(socket.getInputStream());
                        BufferedReader br = new BufferedReader(in);
                        String answer = br.readLine();
                        System.out.println("Client: " + answer);
                        socket.setSoTimeout(10000 - (int) (System.currentTimeMillis() - time));
                    } else {
                        socket.close();
                    }
                }
                socket.close();
            } catch (BindException e) {
                System.out.println("Busy address");
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
    }
}