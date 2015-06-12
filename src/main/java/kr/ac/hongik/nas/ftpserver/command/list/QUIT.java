package kr.ac.hongik.nas.ftpserver.command.list;

import kr.ac.hongik.nas.ftpserver.command.COMM;
import kr.ac.hongik.nas.ftpserver.FtpConnection;

public class QUIT extends COMM {

	public void excute(FtpConnection conn, String in) {
		conn.output("221 Connection Close");
		conn.connectionClose();
	}
}
