package kr.ac.hongik.nas.ftpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import kr.ac.hongik.nas.ftpserver.command.list.FtpCommand;
import kr.ac.hongik.nas.ftpserver.util.Login;

public class FtpConnection implements Runnable {

	private Socket incoming;
	private Login account;
	private PrintWriter outflow;
	private BufferedReader inflow;
	private boolean isRunning;
	private final int debug = 1;

	/*
	 * public FtpConnection(Socket i) {
	 * 
	 * System.out.println("Conn Setting Up"); incoming = i;
	 * System.out.println("Conn Set up Done"); }
	 */
	public FtpConnection(Socket i) {
		incoming = i;
		account = new Login();
	}

	public String getUserName() {
		return account.getUser();
	}

	public String getPassword() {
		return account.getPass();
	}

	public void setUserName(String str) {
		System.out.println("setUserName" + str);
		account.setUser(str);
	}

	public void setPassword(String str) {
		account.setPass(str);
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

	public void printRecMessage(String str) {
		if (debug == 1)
			System.out.println(str);
	}

	public boolean ftpLogin() {

		String recMessage;// , id, password;
		// id = password = "";
		try {
			recMessage = inflow.readLine();
			printRecMessage(recMessage);
			FtpCommand.analyzer(recMessage, this);
			// System.out.println(id);

			output("331 Please specify the password");
			recMessage = inflow.readLine();
			printRecMessage(recMessage);
			FtpCommand.analyzer(recMessage, this);
			// System.out.println(password);

		} catch (IOException e) { // IOException??

			output("530 user information not come properly");
			connectionClose();
			return false;
		} // read id and password

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

		String recMessage;
		// control channel
		while (isRun()) {
			try {
				recMessage = inflow.readLine();
				printRecMessage(recMessage);
			} catch (Exception e) {
				System.out.println("ERROR");
			}
		}

	}
}
