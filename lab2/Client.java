package lab2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	
	public Client(Socket socket, String username) {
		try {
			this.socket = socket;
			this.username = username;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}catch (Exception e) {
			closeEverything(socket, bufferedReader, bufferedWriter);
		}
	}
	
	
	public void sendMessage() {
		try {
			bufferedWriter.write(username);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			Scanner scanner = new Scanner(System.in);
			while(socket.isConnected()) {
				String message = scanner.nextLine();
				bufferedWriter.write(username + ": " + message);
				bufferedWriter.newLine();
				bufferedWriter.flush();
				
			}
			
		}catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public void listenForMessage() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String messageFromGroupChat;
				while (socket.isConnected()) {
					try {
						messageFromGroupChat = bufferedReader.readLine();
						System.out.println(messageFromGroupChat);
					} catch (IOException e) {
						closeEverything(socket, bufferedReader, bufferedWriter);
					}
				}
			}
		}).start();;
	}
	
	
	
	private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
			
			if (socket != null) {
				socket.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username for the group chat");
		String username = scanner.nextLine();
		Socket socket = new Socket("localhost", 1234);
		Client client = new Client(socket, username);
		client.listenForMessage();
		client.sendMessage();
	}
}
