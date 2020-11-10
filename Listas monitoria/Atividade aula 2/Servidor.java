package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {

	public static void main(String[] args) throws IOException {
		DatagramSocket serverSocket = new DatagramSocket(5000);
		byte[] receberDados = new byte[1024];
		byte[] enviarDados;
		InetAddress ipCliente;
		int porta;
		
		while(true) {
			DatagramPacket receberPacote = new DatagramPacket(receberDados, receberDados.length);
			serverSocket.receive(receberPacote);
			
			String mensagem = new String(receberPacote.getData(), "UTF-8");
			System.out.println(mensagem);
			
			ipCliente = receberPacote.getAddress();
			porta = receberPacote.getPort();
			enviarDados = ("2").getBytes();
			DatagramPacket enviarPacotes = new DatagramPacket(enviarDados, enviarDados.length, ipCliente, porta);
			serverSocket.send(enviarPacotes);
		}

	}

}