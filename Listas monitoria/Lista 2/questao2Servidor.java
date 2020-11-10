package segunda;

import java.net.*;
import java.io.*;

public class questao2Servidor {
    public static void main(String[] args) throws IOException {
    	try {
			ServerSocket ss = new ServerSocket(8877);
			Socket s =  ss.accept();
			
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String msgin = "", msgout = "";
			
			while(!msgin.equals("end")) {
				msgin = din.readUTF();
				System.out.println("Cliente: " + msgin); //imprimindo mensagem do cliente
				msgout = br.readLine();
				dout.writeUTF(msgout);
				dout.flush();
			}
			s.close();
		} catch (Exception e) {
			System.out.println(e);
		}

    }
}