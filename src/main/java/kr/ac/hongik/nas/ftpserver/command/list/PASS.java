package kr.ac.hongik.nas.ftpserver.command.list;

import kr.ac.hongik.nas.ftpserver.FtpConnection;

public class PASS extends COMM {

	public void excute(FtpConnection conn, String in) {

		conn.setPassword(in);
	}
}
