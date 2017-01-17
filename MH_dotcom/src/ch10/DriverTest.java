package ch10;

import java.sql.*;


public class DriverTest {
	public static void main(String[] args) {
		Connection con;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// JDBC Driver 이름 입력
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybooks", "root", "1111");
			System.out.println("Success!!!");
		}
		catch(SQLException ex){ System.out.println("SQLException! "+ex);}
		catch(Exception ex){ System.out.println("Exception : "+ex); }
	}
}
