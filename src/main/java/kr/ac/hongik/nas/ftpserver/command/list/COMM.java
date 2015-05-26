package kr.ac.hongik.nas.ftpserver.command.list;

import kr.ac.hongik.nas.ftpserver.FtpConnection;

abstract public class COMM {

	abstract public void excute(FtpConnection conn, String in);
}
