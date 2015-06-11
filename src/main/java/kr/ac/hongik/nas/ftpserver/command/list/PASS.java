package kr.ac.hongik.nas.ftpserver.command.list;

import kr.ac.hongik.nas.ftpserver.FtpConnection;
import kr.ac.hongik.nas.ftpserver.command.COMM;
import kr.ac.hongik.nas.ftpserver.util.Login;

public class PASS extends COMM {

	public void excute(FtpConnection conn, String in) {

		// if( in == "" || in == null ) No input
		Login account = conn.getAccount();
		account.setPass(in);
		
		if (account.isAuthorized()) {
			conn.output("230 User logged in");
		} else {
			conn.output("530 Login incorrect");
		}
	}
}
