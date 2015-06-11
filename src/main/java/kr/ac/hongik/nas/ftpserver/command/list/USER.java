package kr.ac.hongik.nas.ftpserver.command.list;

import kr.ac.hongik.nas.ftpserver.FtpConnection;
import kr.ac.hongik.nas.ftpserver.command.COMM;

public class USER extends COMM {

	public void excute(FtpConnection conn, String in) {

		// if( in == "" || in == null ) No input
		conn.getAccount().setUser(in);
		conn.output("331 Please Specified Password"); // Sending Message
	}
}
