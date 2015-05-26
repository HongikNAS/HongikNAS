package kr.ac.hongik.nas.ftpserver.command.list;

import java.util.HashMap;
import java.util.StringTokenizer;

import kr.ac.hongik.nas.ftpserver.FtpConnection;

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
