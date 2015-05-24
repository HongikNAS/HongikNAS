package org.hongikNAS.utility;

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

		/*
		 * // sqlite version try { Class.forName("org.sqlite.JDBC"); c =
		 * DriverManager.getConnection("jdbc:sqlite:hongikNAS.db");
		 * c.setAutoCommit(false);
		 * System.out.println("Opened database successfully");
		 * 
		 * stmt = c.createStatement(); String sql =
		 * "SELECT count(*) as cnt FROM IDENTITY " + "WHERE user = '" + userName
		 * + "'" + " AND password = '" + password + "';"; // String sql =
		 * "SELECT * FROM IDENTITY"; System.out.println(sql); rs =
		 * stmt.executeQuery(sql); System.out.println(rs.getRow()); /* if
		 * (rs.getRow() == 1) { // existing userName authorized = true; }
		 */

		/*
		 * while (rs.next()) {
		 * 
		 * // String innerName; String innerPassword; // innerName =
		 * rs.getString("user"); // innerPassword = rs.getString("password"); //
		 * System.out.println("ID = " + innerName); //
		 * System.out.println("PASSWORD = " + innerPassword);
		 * 
		 * cnt = rs.getInt("cnt"); System.out.println(cnt); } if (cnt == 1)
		 * authorized = true; rs.close(); stmt.close(); c.close();
		 * 
		 * } catch (Exception e) { System.err.println(e.getClass().getName() +
		 * ": " + e.getMessage()); System.exit(1); }
		 */
		/*
		 * catch (ClassNotFoundException e) { } e.printStackTrace();
		 * System.err.println(e.getClass().getName() + ": " + e.getMessage()); }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); System.err.println(e.getClass().getName() + ": "
		 * + e.getMessage()); }
		 */

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
