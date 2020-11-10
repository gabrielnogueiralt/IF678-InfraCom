package primeira;

import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class questao1Cliente {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int port = 8877;
        String address = "127.0.0.1", message = "";
        try {
            Socket socket = new Socket(address, port);
            while(socket.isConnected()){
                System.out.print("Type a message: ");
                message = in.nextLine() + "\n";
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.write(message.getBytes());
                System.out.println("message sent!");
            }
            socket.close();
        } catch (ConnectException e) {
            System.out.println("Could not deliver the message");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}