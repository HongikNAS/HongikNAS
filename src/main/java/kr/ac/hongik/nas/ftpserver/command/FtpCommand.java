package kr.ac.hongik.nas.ftpserver.command;

import java.util.HashMap;
import java.util.StringTokenizer;

import kr.ac.hongik.nas.ftpserver.FtpConnection;
import kr.ac.hongik.nas.ftpserver.command.list.*;
/**
 * 
 * @author Arubirate
 *
 */
public class FtpCommand {

	static private final HashMap<String, COMM> CommandList;
	static {
		CommandList = new HashMap<String, COMM>();
		CommandList.put("USER", new USER());
		CommandList.put("PASS", new PASS());
		CommandList.put("SYST", new SYST());
		CommandList.put("QUIT", new QUIT());
		CommandList.put("UNKNOWN", new Unknown());
	}

	static public void analyzer(String in, FtpConnection conn) throws CommandNullException {

		StringTokenizer st = new StringTokenizer(in);
		COMM func;
		String funcName="", inData="";
		
		if( st.hasMoreTokens() ) { // multithread enviroment (is it ok?) 
			funcName = st.nextToken();
			if( st.hasMoreTokens() )
				inData = st.nextToken();
		}
		
		if( funcName != "" ) {
			if( (func = CommandList.get(funcName)) == null  ) { 
				func = CommandList.get("UNKNOWN");
			}
			func.excute(conn, inData);
		}else {
			throw new CommandNullException();
		}
	}
}
