package kr.ac.hongik.nas.ftpserver.util;

/**
 * 
 * @author Arubirate
 *
 */
public class Login {

	boolean authorized;
	String userName;
	String password;
	DBController db;

	public Login() {
		userName = "";
		password = "";
		authorized = false;
		db = new DBController();
	}

	public Login(String inputuserName, String inputPassword) {
		userName = inputuserName;
		password = inputPassword;
		authorized = false;
		db = new DBController();
	}

	public String getUser() {
		return userName;
	}

	public String getPass() {
		return password;
	}

	public void setUser(String str) {
		userName = str;
	}

	public void setPass(String str) {
		password = str;
	}

	public boolean tryToAuthorize() {

		
		if( getPass() == "" || getUser() == "" )
			return false;
		String sql = null;
		int cnt = 0;

		// mysql Version
		db.getConn();

		sql = "SELECT count(*) as cnt FROM IDENTITY  ";
		sql = sql + "WHERE user = '" + userName + "' ";
		sql = sql + "AND password = '" + password + "'; ";

		db.excuteSelect(sql);
		while (db.resultSetNext()) {
			cnt = db.resultSetGetInt("cnt");
		}
		if (cnt == 1)
			authorized = true;
		db.close();

		return authorized;
	}

	public boolean isAuthorized() {
		if (authorized == false)
			authorized = tryToAuthorize();

		return authorized;
	}

	/***
	 * For Testing
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		System.out.println("TEST FOR DATABSE");
		Login test = new Login("root", "1234");
		if (test.tryToAuthorize()) {
			System.out.println("Successfully Login");
		} else {
			System.out.println("Unable to Comply");
		}

		/*
		 * Connection c = null; Statement stmt = null;
		 * 
		 * try { Class.forName("org.sqlite.JDBC"); c =
		 * DriverManager.getConnection("jdbc:sqlite:test.db");
		 * System.out.println("Opened database successfully");
		 * 
		 * } catch (ClassNotFoundException e) { e.printStackTrace();
		 * System.err.println(e.getClass().getName() + ": " + e.getMessage()); }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); System.err.println(e.getClass().getName() + ": "
		 * + e.getMessage()); }
		 */
	}
}
