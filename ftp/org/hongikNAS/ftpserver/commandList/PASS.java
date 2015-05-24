package org.hongikNAS.ftpserver.commandList;

import org.hongikNAS.ftpserver.FtpConnection;
import org.hongikNAS.ftpserver.command.COMM;

public class PASS extends COMM {

	public void excute(FtpConnection conn, String in) {

		conn.setPassword(in);
	}
}
