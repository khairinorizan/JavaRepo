package hw2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ChatClient {
	Socket serverSocket;
	String serverHostName = "localhost";
	int serverPortNumber = 4444;
	ServerListener sl;
	
	public ChatClient() {
		// 1. CONNECT TO THE SERVER
		try {
			serverSocket = new Socket(serverHostName, serverPortNumber);
			Scanner sc = null;
			String accessCode = "";
			String clientN = "";
			//out act as a stream to send messages to server
			PrintWriter out = new PrintWriter(new BufferedOutputStream(serverSocket.getOutputStream()));
			
			//Client console prompt for name
			System.out.println("Enter your name: ");
			sc = new Scanner(System.in);
			clientN = sc.nextLine();
			//stream clientN to server and flush so server could get
			out.println(clientN);
			out.flush();
			
			String serverOutput = "False";
			
			while(serverOutput.equals("False")) {
				//Client console prompt for access code
				System.out.println("Enter access code: ");
				sc = new Scanner(System.in);
				accessCode = sc.nextLine();
				//stream accessCode to server and flush so server could get
				out.println(accessCode);
				out.flush();
				
				//in act as a pipe stream to get message from server to client
				Scanner in = new Scanner(new BufferedInputStream(serverSocket.getInputStream()));
				//get message from server
				serverOutput = in.nextLine();
				
				if(serverOutput.equals("False")) {
					System.out.println("Incorrect access code!");
				}
			}
			
			System.out.println("You are connected");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2. SPAWN A LISTENER FOR THE SERVER. THIS WILL KEEP RUNNING
		// when a message is received, an appropriate method is called
		sl = new ServerListener(this, serverSocket);
		new Thread(sl).start();//youd always listen

		PrintWriter out;
		Scanner sc;
		
		try {
			out = new PrintWriter(new BufferedOutputStream(serverSocket.getOutputStream()));
			sc = new Scanner(System.in);
			
			while(true) {
				
				String newInput = sc.nextLine();
				
				out.println(newInput);
				out.flush();
			}
			
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

	public void handleMessage(String s) {

		System.out.println(s);
			
	}

	public static void main(String[] args) {
		ChatClient lc = new ChatClient();
	} // end of main method

} // end of ListClient

class ServerListener implements Runnable {
	ChatClient lc;
	Scanner in; // this is used to read which is a blocking call

	ServerListener(ChatClient lc, Socket s) {
		try {
			this.lc = lc;
			in = new Scanner(new BufferedInputStream(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) { // run forever
			String s = in.nextLine();
			lc.handleMessage(s);
		}

	}
}
