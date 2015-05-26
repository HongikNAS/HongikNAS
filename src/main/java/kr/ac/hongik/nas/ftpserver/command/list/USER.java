package kr.ac.hongik.nas.ftpserver.command.list;

import kr.ac.hongik.nas.ftpserver.FtpConnection;

public class USER extends COMM {

	public void excute(FtpConnection conn, String in) {

		conn.setUserName(in);
	}
}
