package kr.ac.hongik.nas.ftpserver.command.list;

import kr.ac.hongik.nas.ftpserver.FtpConnection;
import kr.ac.hongik.nas.ftpserver.command.COMM;

public class PASS extends COMM {

	public void excute(FtpConnection conn, String in) {

		conn.setPassword(in);
	}
}
