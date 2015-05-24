package org.hongikNAS.ftpserver.command;

import java.util.HashMap;
import java.util.StringTokenizer;

import org.hongikNAS.ftpserver.FtpConnection;
import org.hongikNAS.ftpserver.commandList.PASS;
import org.hongikNAS.ftpserver.commandList.USER;

public class FtpCommand {

	static private final HashMap<String, COMM> CommandList;
	static {
		CommandList = new HashMap<String, COMM>();
		CommandList.put("USER", new USER());
		CommandList.put("PASS", new PASS());
	}

	static public void analyzer(String in, FtpConnection conn) {

		StringTokenizer st = new StringTokenizer(in);
		String token = st.nextToken();
		COMM func;

		func = CommandList.get(token);

		token = st.nextToken();
		func.excute(conn, token);
	}
}
