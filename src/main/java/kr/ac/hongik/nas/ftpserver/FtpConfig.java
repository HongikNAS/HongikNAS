package kr.ac.hongik.nas.ftpserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Arubirate
 *
 */
public class FtpConfig {
	public static final String CONFIG_PATH = "src/main/resource/ftp.config";

	private Scanner input;
	private int port = 21; // default port
	private int connectionLimit = 50; // default limit;

	// public void readConfig()
	// public void setPort(String newPort)
	// public int getPort()
	// public void setConnectionLimit(String newLimit)
	// public int getConnectionLimit()
	public FtpConfig() {
		// # is comment in config file
		File config = new File(CONFIG_PATH);

		try {
			input = new Scanner(config);
			readConfig();
		} catch (FileNotFoundException e) {
			System.err.println("Error : Can't open Config file");
			System.err.println("Setting Deafult Values");
			// System.exit(1);
		}

	}

	public void readConfig() {

		try {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				if (line.charAt(0) == '#') // it means just comment
					continue;

				StringTokenizer st = new StringTokenizer(line, ":");
				String key = st.nextToken();
				if (key.compareToIgnoreCase("port") == 0)
					setPort(st.nextToken());
				else if (key.compareToIgnoreCase("connectionLimit") == 0)
					setConnectionLimit(st.nextToken());
			}
		} catch (NoSuchElementException e) {
			System.err.println("FTPConfig Error(3) : config file format is wrong");
			System.exit(1);
		} catch (NullPointerException e) {
			System.err.println("FTPConfig Error(2) : Can't read Config file properly");
			System.exit(1);
		} catch (IllegalStateException e) {
			System.err.println("FTPConfig Error(1) : Can't read Config file");
			System.exit(1);
		}
	}

	public void setPort(String newPort) {
		try {
			port = Integer.parseInt(newPort);
		} catch (NumberFormatException e) {
			System.err.println("FTPConfig Error(5) : Can't set Port number");
			port = 21; // default port
		}
	}

	public int getPort() {
		return port;
	}

	public void setConnectionLimit(String newLimit) {
		try {
			connectionLimit = Integer.parseInt(newLimit);
		} catch (NumberFormatException e) {
			System.err.println("FTPConfig Error(6) : Can't set Limit number");
			connectionLimit = 50;// default value;
		}
	}

	public int getConnectionLimit() {
		return connectionLimit;
	}

	public static void main(String[] args) {

		FtpConfig a = new FtpConfig();
		System.out.println(a.getConnectionLimit());
		System.out.println(a.getPort());
	}
}
