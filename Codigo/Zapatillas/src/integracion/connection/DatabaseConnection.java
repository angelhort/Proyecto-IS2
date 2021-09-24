package integracion.connection;

import java.sql.*;

public class DatabaseConnection {
	
	//public static final String DB_URL = "jdbc:mysql://db4free.net/zapatillas";
	//public static final String USERNAME = "zapatillas";
	//public static final String PASSWORD = "zapatillas";
	//public static final String DB_URL = "jdbc:mysql://sql11.freemysqlhosting.net/sql11413968";
	//public static final String USERNAME = "sql11413968";
	//public static final String PASSWORD = "jQAb62VXga";
	//public static final String DB_URL = "jdbc:mysql://sql11.freemysqlhosting.net/sql11422479";
	//public static final String USERNAME = "sql11422479";
	//public static final String PASSWORD = "WtYADF47LH";
	public static final String DB_URL = "jdbc:mysql://sql11.freemysqlhosting.net/sql11424123";
	public static final String USERNAME = "sql11424123";
	public static final String PASSWORD = "R4Bg6b9BNV";
	
	
	public DatabaseConnection() {
		// Empty
	}

	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println(conn);
			return (conn != null) ? conn : null;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int dropRegisterWithID(String sql, int ID) {
		Connection conn = getConnection();
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, ID);
			
			result = statement.executeUpdate();
			
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		killConnection(conn);
		
		return result;
	}
	
	public static void killConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}