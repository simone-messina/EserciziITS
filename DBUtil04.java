package esercizio_04;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil04 {
	private static final String PASS = "";
	private static final String USER = "root";
	private static final String DB_URL = "jdbc:mysql://localhost/garage";

	public static Connection connect() {
		try{ 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			System.out.println("Connecting to database..."); 
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			return conn;
		}catch(SQLException se){ 
			//Handle errors for JDBC 
			se.printStackTrace(); 
		}catch(Exception e){ 
			//Handle errors for Class.forName 
			e.printStackTrace(); 
		}
		return null;
	}
}
