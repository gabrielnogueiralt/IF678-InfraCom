package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int porta = 5000;
		String endereco = "localhost";
		
		DatagramSocket clienteSocket = new DatagramSocket();
		InetAddress ipServidor = InetAddress.getByName(endereco);
		byte[] enviarDados;
		
		enviarDados = in.nextLine().getBytes();
			
		DatagramPacket enviarPacote = new DatagramPacket(enviarDados, enviarDados.length, ipServidor, porta);
		
		long tempInicial = System.nanoTime();
		clienteSocket.send(enviarPacote);
		byte[] receberDados = new byte[1];
		DatagramPacket receberPacote = new DatagramPacket(receberDados, receberDados.length);
		clienteSocket.receive(receberPacote);
		System.out.println("RTT: " + (System.nanoTime() - tempInicial) / 1000);
		clienteSocket.close();
	}

}