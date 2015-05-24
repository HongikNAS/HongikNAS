package org.hongikNAS.ftp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.hongikNAS.utility.User;

public class FtpConnection implements Runnable {

	private Socket incoming;
	public User user;
	private PrintWriter outflow;
	private BufferedReader inflow;
	private boolean isRunning;

	/*
	 * public FtpConnection(Socket i) {
	 * 
	 * System.out.println("Conn Setting Up"); incoming = i;
	 * System.out.println("Conn Set up Done"); }
	 */
	public FtpConnection(Socket i, boolean isConnect) {
		if (isConnect == false) { // We should not connect
			System.out.println("Connection denied");
			output("221 Too many User");
		} else {
			System.out.println("Conn Setting");
			incoming = i;
		}
	}

	public boolean isRun() {
		return isRunning;
	}

	public void output(String out) {
		try {
			outflow.println(out);
		} catch (Exception e) {
			System.out.println("Error printing to outflow");
		}
	}

	public void start() {
		// running = true;
		try {
			outflow = new PrintWriter(new OutputStreamWriter(
					incoming.getOutputStream()), true);
			inflow = new BufferedReader(new InputStreamReader(
					incoming.getInputStream())); // BufferedReader?
		} catch (Exception e) {

		}
		// buffer size unspecified <- not good.
		// catch (IOException e) {

		// }
		new Thread(this).start();
	}

	public void run() {

		try {
			outflow = new PrintWriter(new OutputStreamWriter(
					incoming.getOutputStream()), true);
			inflow = new BufferedReader(new InputStreamReader(
					incoming.getInputStream()));
		} catch (Exception e) {
			System.out.println("Error creating flows");
			System.out.println(e);
			return;
		}

		// TODO Auto-generated method stub
		System.out.println(incoming.getPort());
		System.out.println("Conn is Start");
		String id, password;
		id = password = null;
		// output("220");
		output("220 FTP Server v1.0"); // First Message;
		output("User Name : ");
		// System.out.println("220 FTP SERVER");

		System.out.println("Conn is FIRST SENDING");
		try {
			id = inflow.readLine();
			if (id == null)
				System.out.println("ERROR");
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}

		try {
			output("331 User ID OKAY");
		} catch (Exception e) {
			System.out.println("OMG");
		}

		System.out.println("Conn is SECOND SENDING");
		try {
			password = inflow.readLine();
			if (password == null)
				System.out.println("ERROR");
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}

		user = new User(id, password);
		if (user.tryToAuthorize() == true)
			output("230");
		else
			output("530");
		System.out.println("Conn is THRID SENDING");
	}
}
