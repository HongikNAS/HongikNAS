package kr.ac.hongik.nas.ftpserver.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Arubirate
 *
 */
public final class DBController {
	// Connection ����
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	final int debug = 1;

	public DBController() {
	}

	/**
	 * try to connect mysql Server
	 * 
	 * @return Connection
	 */
	public void close() {

		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("Database Close ERROR");
			System.exit(1);
		}
	}

	public void getConn() {
		// basic option : mysql
		if (debug == 1)
			System.out.println("DB Conn Start");

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hongiknas", "root", "1234");
		} catch (SQLException e) {
			System.out.println("Database Connection ERROR");
			System.exit(1);
		}

	}

	public void excuteSelect(String strQuery) {

		if (debug == 1)
			System.out.println(strQuery);
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQuery);

		} catch (SQLException e) {
			System.out.println("Database SELECT QUERY ERROR");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public boolean resultSetNext() {

		boolean res = false;
		try {
			res = rs.next();
		} catch (SQLException e) {
			System.out.println("Database ResultSetNext ERROR");
			System.exit(1);
		}

		return res;
	}

	public int resultSetGetInt(String column) {
		int res = 0;
		try {
			res = rs.getInt(column);
		} catch (SQLException e) {
			System.out.println("Database GetInt ERROR");
			System.exit(1);
		}
		return res;
	}
	// executeSelect -> ResultSet
	// executeDelete
	// executeUpdate

}
