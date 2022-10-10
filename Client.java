import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
	final int PORT = 2011;
	
	
		Socket s = new Socket("localhost", PORT);
		PrintWriter out = 
				new PrintWriter(s.getOutputStream(), true);
		out.println(args[0]);
		out.flush();
				
		
	
	}
}
