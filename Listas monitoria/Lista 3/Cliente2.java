import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int port = 3002;
		String address = "localhost", res = "";
		String data[] = new String[2];
		int counter = 0;
		
		try {
			Socket socket = new Socket(address, port);
			InputStreamReader input = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(input);
			res = br.readLine();
			socket.close();
		} catch (ConnectException e) {
			System.out.println("Mensagem não entregue ao destinatário");
		} catch (Exception e) {
			System.out.println("Erro 1 " + e);
		}
		data = res.split(" ");
		try {
			Socket socket = new Socket(address, Integer.parseInt(data[0]));
			
			new Thread(new Runnable() {
	            @Override
	            public void run() {
	            	while(socket.isConnected()) {
	            		try {
	            			DataInputStream din = new DataInputStream(socket.getInputStream());
			    			
			    			String msgin = "";
			    			
			    			msgin = din.readUTF();
		    				System.out.println("Cliente 1 : " + msgin); //imprimindo mensagem do cliente
						} catch (Exception e) {
							System.out.println("Erro 2 " + e);
						}
	            		
	            	}
	            }
			}).start();
			while(socket.isConnected()) {
				String msgout = "";
				DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				msgout = br.readLine();
				dout.writeUTF(msgout + "(" + counter + ")");
				dout.flush();
				counter++;
			}
		} catch (Exception e) {
			System.out.println("Erro 3 " + e);
		}
		
	}
}