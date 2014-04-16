import java.sql.*;


public class DB {
	public static void writeOn(String querry){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String dbURL = "jdbc:mysql://127.0.0.1/test";
			String username = "root";
			String password = "root";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, username, password);
			stmt = conn.createStatement();
			stmt.execute(querry);
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}
	public static void createOrReplaceTable(String tableName){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String dbURL = "jdbc:mysql://127.0.0.1/test";
			String username = "root";
			String password = "root";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, username, password);
			stmt = conn.createStatement();
			String querry = "create table "+tableName+" (name varchar(200),date varchar(200))";
			stmt.execute(querry);
		} catch (Exception e) {
			System.err.println("Table already exist");
			System.err.println(e.getMessage());
		}finally{
			System.out.println("Table Created or already existed");
		}
		
	}
	public static void fireQuerry(String querry) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String dbURL = "jdbc:mysql://127.0.0.1/test";
			String username = "root";
			String password = "root";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, username, password);

			stmt = conn.createStatement();
			stmt.execute(querry);
			System.out.println("Cool welcome to our security System"+username);
			
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

	public static ResultSet readData() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String dbURL = "jdbc:mysql://127.0.0.1/test";
			String username = "root";
			String password = "root";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, username, password);

			stmt = conn.createStatement();

			if (stmt.execute("select * from auth")) {
				rs = stmt.getResultSet();
			} else {
				System.err.println("select failed");
			}
		} catch (ClassNotFoundException ex) {
			System.err.println("Failed to load mysql driver");
			System.err.println(ex);
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		}
		return rs;
	}
	public static ResultSet readFromTable(String tableName){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String dbURL = "jdbc:mysql://127.0.0.1/test";
			String username = "root";
			String password = "root";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, username, password);

			stmt = conn.createStatement();

			if (stmt.execute("select * from "+tableName)) {
				rs = stmt.getResultSet();
			} else {
				System.err.println("select failed");
			}
		} catch (ClassNotFoundException ex) {
			System.err.println("Failed to load mysql driver");
			System.err.println(ex);
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		}
		return rs;
	}
}