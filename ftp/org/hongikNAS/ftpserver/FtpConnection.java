package org.hongikNAS.ftpserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.hongikNAS.utility.User;

public class FtpConnection implements Runnable {

	private Socket incoming;
	public User account;
	private PrintWriter outflow;
	private BufferedReader inflow;
	private boolean isRunning;

	/*
	 * public FtpConnection(Socket i) {
	 * 
	 * System.out.println("Conn Setting Up"); incoming = i;
	 * System.out.println("Conn Set up Done"); }
	 */
	public FtpConnection(Socket i) {
		incoming = i;
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

	public void connectionClose() {
		isRunning = false;
	}

	public boolean ftpLogin() {

		String id, password;
		id = password = "";
		try {
			id = inflow.readLine();
			System.out.println(id);

			output("331 Please specify the password");
			password = inflow.readLine();
			System.out.println(password);

		} catch (Exception e) { // IOException??

			output("530 user information not come properly");
			connectionClose();
			return false;
		} // read id and password

		account = new User(id, password);
		if (account.isAuthorized()) {
			output("230 User logged in");
			return true;
		} else {
			output("530 Login incorrect");
			return false;
		}

	}

	public void start(boolean isConnect) {
		// running = true;
		try {
			outflow = new PrintWriter(new OutputStreamWriter(
					incoming.getOutputStream()), true);
			inflow = new BufferedReader(new InputStreamReader(
					incoming.getInputStream())); // BufferedReader?
		} catch (Exception e) {

			System.err.println("Stream ERROR");
		}

		output("220 (login HongikNAS)"); // connection established

		if (isConnect) { // true

			if (ftpLogin())
				new Thread(this).start();

		} else {// isConnect == false
			output("221 Too many User");
		}
	}

	public void run() {

		String input;
		// control channel
		while (isRun()) {
			try {
				input = inflow.readLine();
			} catch (Exception e) {
				System.out.println("ERROR");
			}
		}

	}
}
