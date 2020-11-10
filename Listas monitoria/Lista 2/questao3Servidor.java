package terceira;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class questao3Servidor {
    public static void main(String[] args) throws IOException{
    	DatagramSocket serverSocket = new DatagramSocket(8888);
        byte[] receiveData = new byte[1000];
        byte[] sendData;
        InetAddress ipClient;
        int port;
          
        while (true) {            
            DatagramPacket receivePackage = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePackage);
            ipClient = receivePackage.getAddress();
            port = receivePackage.getPort();
            String msgDecode  = new String(receivePackage.getData(), "UTF-8");
            System.out.println("Message received" + msgDecode);
            sendData = ("Adeus").getBytes();
            DatagramPacket sendPackage = new DatagramPacket(sendData, sendData.length, ipClient, port);
            serverSocket.send(sendPackage);
            if(msgDecode.equals("Tchau")){
                System.exit(0);
            }
        }
    }
}