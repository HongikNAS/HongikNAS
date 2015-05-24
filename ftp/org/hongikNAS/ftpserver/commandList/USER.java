package org.hongikNAS.ftpserver.commandList;

import org.hongikNAS.ftpserver.FtpConnection;
import org.hongikNAS.ftpserver.command.COMM;

public class USER extends COMM {

	public void excute(FtpConnection conn, String in) {

		conn.setUserName(in);
	}
}
