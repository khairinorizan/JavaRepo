package hw2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ChatServer {
	
	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;  // 1. serversocket
		String clientN = "";
		ArrayList<Socket> socketList = new ArrayList<Socket>();

		// 1. CREATE A NEW SERVERSOCKET
		try {
			serverSocket = new ServerSocket(4444); // provide MYSERVICE at port 
													// 4444
			System.out.println(serverSocket);
		} catch (IOException e) {
			System.out.println("Could not listen on port: 4444");
			System.exit(-1);
		}

		
		// 2. LOOP FOREVER - SERVER IS ALWAYS WAITING TO PROVIDE SERVICE!
		while (true) { // 3.
			Scanner sc = null;
			Socket clientSocket = null;
			String accessCode = "";
			
			
			try {

				// 2.1 WAIT FOR CLIENT TO TRY TO CONNECT TO SERVER
				clientSocket = serverSocket.accept(); // // 4.
				
				//in act as a pipe stream to get message from client to server
				Scanner in = new Scanner(new BufferedInputStream(clientSocket.getInputStream()));
				//get client name
				clientN = in.nextLine();
				//get accessCode
				accessCode = in.nextLine();
				//out act as a stream to send messages to client
				PrintWriter out = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
				//access code verification
				while(!accessCode.equals("cs319spring2020")) {
					//send out false to current connecting client if access code given is incorrect
					out.println("False");
					out.flush();
					
					accessCode = in.nextLine();
					
				}
				//send out true to current connecting client if access code given is correct
				out.println("True");
				out.flush();
				
				// 2.2 SPAWN A THREAD TO HANDLE CLIENT REQUEST
				
				socketList.add(clientSocket);
				//print out on server side that clientN are connected to the server
				System.out.println(clientN + " are connected");
				
				for(Socket socket: socketList) {
					out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
					
					if(!socket.equals(clientSocket)) {
						//printing out at client end
						out.println(clientN + " has joined the chat");
						out.flush();
					}
				}
				
				Thread t = new Thread(new ListClientHandler(clientSocket, clientN, socketList));
				t.start();
				

			} catch (IOException e) {
				System.out.println("Accept failed: 4444");
				System.exit(-1);
			}


		} // end while loop
		
		
	

	} // end of main method

} // end of class MyServer

class ListClientHandler implements Runnable {
	Socket s; // this is socket on the server side that connects to the CLIENT
	String clientName; // keeps track of its number just for identifying purposes
	ArrayList<Socket> socketList;

	ListClientHandler(Socket s, String name, ArrayList<Socket> list) {//int n
		this.s = s;
		clientName = name;
		socketList = list;
	}

	// This is the client handling code
	// This keeps running handling client requests 
	// after initially sending some stuff to the client
	public void run() { 
		Scanner in;
		PrintWriter out;
		
		try {
			// 1. GET SOCKET IN/OUT STREAMS
			in = new Scanner(new BufferedInputStream(s.getInputStream())); 
			out = new PrintWriter(new BufferedOutputStream(s.getOutputStream()));
	
			// 2. PRINT SOME STUFF TO THE CLIENT
			while(true) {
				String string = in.nextLine();
				handleRequest(string);
				
				//sending out messages to every client
				for(Socket socket: socketList) {
					out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
					if(!socket.equals(s)) {
						//printing out at client end
						out.println(clientName + ": " + string);
						out.flush();
					}
				}	
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			socketList.remove(s);
			System.out.println(clientName + " has left the chat");
			
			for(Socket socket: socketList) {
				try {
					out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
					
					if(!socket.equals(s)) {
						//printing out at client end
						out.println(clientName + " has left the chat");
						out.flush();
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
		}	
		// This handling code dies after doing all the printing
	} // end of method run()
	
	void handleRequest(String s) {
		System.out.println(clientName + ": " + s);
		
	}

}
