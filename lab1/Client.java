package lab1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		final int PORT = 2011;
		Socket s = new Socket("localhost", PORT);
		PrintWriter out = 
				new PrintWriter(s.getOutputStream(), true);
		BufferedReader bfr = new BufferedReader(
				new InputStreamReader(s.getInputStream()));
		BufferedReader terminalReader = new BufferedReader(
				new InputStreamReader(System.in));
				
		while (true){
			String message = terminalReader.readLine();
			if (message.equals(".")) {
				terminalReader.close();
				break;
			}else {
				out.println(message);
				out.flush();				
				System.out.println("Message sent");
			}
			System.out.println(bfr.readLine());		
		}
				
		
	
	}
}
