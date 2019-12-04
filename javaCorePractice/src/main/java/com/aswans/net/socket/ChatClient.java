package com.aswans.net.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) {// Passing null to getByName()
											// produces the
		// special "Local Loopback" IP address, for
		// testing on one machine w/o a network:
		InetAddress addr = null;
		// Alternatively, you can use
		// the address or name:
		// InetAddress addr =
		// InetAddress.getByName("127.0.0.1");
		// InetAddress addr =
		// InetAddress.getByName("localhost");
		Socket socket = null;
		// Guard everything in a try-finally to make
		// sure that the socket is closed:
		try {
			addr = InetAddress.getByName(null);
			System.out.println("addr = " + addr);
			socket = new Socket(addr, ChatServer.PORT);
			System.out.println("socket = " + socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// Output is automatically flushed
			// by PrintWriter:
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			//for (int i = 0; i < 10; i++) {
			Scanner scanner = new Scanner(System.in);
			while(true){
				 out.println(scanner.nextLine());
				 String str = in.readLine();
				 System.out.println(str);
				 if(str.indexOf("END1") !=-1)
					 break;
			}
			//}
			//out.println("END");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			System.out.println("closing...");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
