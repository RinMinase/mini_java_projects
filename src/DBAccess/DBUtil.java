package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private final String DBDriver = "com.mysql.jdbc.Driver";
	private final String DBUrl = "jdbc:mysql://127.0.0.1/";
	private final String DBName = "JSPAddressbook";
	private final String DBUser = "root";
	private final String DBPass = "root";
	
	private Connection con;
	private Statement stm;

	public DBUtil() {
		try { Class.forName(DBDriver); } catch (ClassNotFoundException ex) { System.err.println(ex.getMessage()); }
		
		try {
			con = DriverManager.getConnection(DBUrl + DBName, DBUser, DBPass);
			stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException ex) { System.err.println(ex.getMessage()); }
	}
	
	public ResultSet retrieve(String command) {
		try {
			ResultSet rs = stm.executeQuery(command);
			return rs;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	public int insert(String commandStr) { 
		try {
			return stm.executeUpdate(commandStr);
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
			return 0;
		}
	}
	
	public void disconnect() {
		try {
			stm.close();
			con.close();
		} catch( SQLException ex) { System.err.println(ex.getMessage()); }
	}
}
