package org.hongikNAS.ftpserver;

import java.net.ServerSocket;
import java.net.Socket;

public class FtpServer implements Runnable {

	boolean isRun;
	Socket connection;
	ServerSocket server;
	final int debug = 1;

	FtpConnection[] ftpConnState;
	private FtpConfig config;

	// FtpConfig
	// Connection Limit 설정
	// Server Port 설정

	public FtpServer() {

		config = new FtpConfig(); // read config;
		ftpConnState = new FtpConnection[config.getConnectionLimit()];
		try {
			server = new ServerSocket(config.getPort());
		} catch (Exception e) {
			System.err.println("SERVER PORT IS NOT SET");
			System.err.println(e);
			System.exit(1);
		}
	}

	public boolean running() {
		return isRun;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FtpServer ftpServer = new FtpServer();
		Thread serverThread = new Thread(ftpServer);
		serverThread.start();
	}

	public void run() {
		// TODO Auto-generated method stub
		isRun = true;
		System.out.println("FTP Server by HongikNAS");
		while (running()) {

			boolean isConnected;
			try {
				connection = server.accept();
				if (debug == 1)
					System.out.println("FTP CONNECTION ESTABILISHED");

				isConnected = false;
				for (int j = 0; j < config.getConnectionLimit(); j++) {

					if (ftpConnState[j] == null
							|| ftpConnState[j].isRun() == false) {
						ftpConnState[j] = new FtpConnection(connection);
						ftpConnState[j].start(true);
						isConnected = true;
						break;
					}
				}
				if (isConnected == false) {
					System.err.println("Error : Too many Connection");
					FtpConnection ftpTemp = new FtpConnection(connection);
					ftpTemp.start(false); // just send 221 code
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("FTP SERVER IS GONE");
				System.exit(1);
			}
		}
	}
}
