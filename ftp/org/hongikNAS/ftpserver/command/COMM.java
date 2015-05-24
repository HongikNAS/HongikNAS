package org.hongikNAS.ftpserver.command;

import org.hongikNAS.ftpserver.FtpConnection;

abstract public class COMM {

	abstract public void excute(FtpConnection conn, String in);
}
