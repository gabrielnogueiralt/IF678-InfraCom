import java.io.DataOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

class Servidor {
	public static void main(String[] args) {
		int port1 = 3001, port2 = 3002;
		String address = "localhost";
		int pt = 0, pt2 = 0;
		String ip, ip2;
		
		try {
			ServerSocket serverSocket = new ServerSocket(port1);
			ServerSocket serverSocket2 = new ServerSocket(port2);
			System.out.println("Esperando cliente se conectar na porta 3001");
			System.out.println("Esperando cliente se conectar na porta 3002");
			
			Socket socket = serverSocket.accept();
			Socket socket2 = serverSocket2.accept();
			
			DataOutputStream data = new DataOutputStream(socket.getOutputStream());
			DataOutputStream data2 = new DataOutputStream(socket2.getOutputStream());
			
			if(socket.isConnected()) {
				pt = socket.getPort();
				ip = socket.getInetAddress().getHostAddress();
				String aux = pt + " " + ip;
				data2.write(aux.getBytes());
				System.out.println(pt + " " + ip);
			}
			if(socket2.isConnected()) {
				pt2 = socket2.getPort();
				ip2 = socket2.getInetAddress().getHostAddress();
				String aux = pt + " " + ip2;
				data.write(aux.getBytes());
				System.out.println(pt + " " + ip2);
			}
			socket.close();
			socket2.close();
		} catch (BindException e) {
			System.out.println("Endereço ocupado");
		} catch (Exception e) {
			System.out.println("Erro " + e);
		}

	}
}