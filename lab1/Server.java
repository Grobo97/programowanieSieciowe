package lab1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class Server {

	public static void main(String[] args) throws IOException {
		try {
				final int PORT = 2011;
			KeyProtocol kP = new KeyProtocol();
			
			
			ServerSocket ss = new ServerSocket(PORT); 
			Socket s = ss.accept();
			System.out.println("Client connected" + s.getPort());
			BufferedReader bfr = new BufferedReader(
					new InputStreamReader(s.getInputStream()));
			PrintWriter out = 
					new PrintWriter(s.getOutputStream(), true);
			
			while (true) {
				String message = bfr.readLine();
				System.out.println("Received a message: " + message);
				out.println(kP.encodeMessage(message));
				out.flush();
			}
		}catch (SocketException e) {
			System.out.println("Client disconected, shutting down the Server");
		}
		
		
		
	
	
	}

}
