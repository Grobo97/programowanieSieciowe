import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class Server {

	public static void main(String[] args) throws IOException {
		List<String> keys = new ArrayList<String>();
		final int PORT = 2011;
		keys.add("A");
		keys.add("B");
		ServerSocket ss = new ServerSocket(PORT); 
		Socket s = ss.accept();
		BufferedReader bfr = new BufferedReader(
				new InputStreamReader(s.getInputStream()));
	
		String message = bfr.readLine();
		System.out.println("Received message: " + message);
		
		
	
	
	}

}
