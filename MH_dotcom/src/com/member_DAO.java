package com;

import java.sql.*;
import com.member_VO;

public class member_DAO {
	private static Connection conn;
	private static String db_Name = "MH_dotcom";
	private static String db_Host = "root";
	private static String db_Pass = "falsh21";
	
	public member_DAO() {
		try {
			Class.forName("com.sql.jdbc.Driver");
		}catch(ClassNotFoundException ex){
			System.out.println("JDBC 드라이버 연결 실패!");
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_Name, db_Host, db_Pass);
		}catch(SQLException ex){
			
		}
	}
	
	public member_VO veiw_Member(String ID) {
		String query = "SELECT * from member WHERE ID = "+ID;
		member_VO m_obj = new member_VO();
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
		
			if (rs.next()){
				m_obj.setName(rs.getString("name"));
				m_obj.setSalt(rs.getString("salt"));
				m_obj.setHash(rs.getString("hash"));
				m_obj.setPhone(rs.getString("phone"));
				m_obj.setEmail(rs.getString("email"));
				m_obj.setGender(rs.getString("gender"));
			}
			
			stmt.close();
			rs.close();
			
			return m_obj;
			
		}catch(SQLException ex){
			System.out.println("SQL 멤버 뷰 에러! : "+ex.getLocalizedMessage());
		}
		
		return null;
	}
	
	public boolean insertMember(member_VO m_obj){
		String query = "insert into member values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m_obj.getID());
			pstmt.setString(2, m_obj.getSalt());
			pstmt.setString(3, m_obj.getHash());
			pstmt.setString(4, m_obj.getName());
			pstmt.setString(5, m_obj.getPhone());
			pstmt.setString(6, m_obj.getEmail());
			pstmt.setString(7, m_obj.getGender());
			
			int x = pstmt.executeUpdate();
			
			pstmt.close();
			
			if (x < 1){
				System.out.println("회원정보 DB전송 실패!");
				return false;
			}
			return true;
			
		}catch(SQLException ex){
			System.out.println("SQL member insert error : "+ex.getLocalizedMessage());
		}
		
		return false;
	}
	
	public void conn_close(){
		try {
			if (conn != null){
				conn.close();
				conn = null;
			}
		}catch(SQLException ex){
			System.out.println("CONN close error : "+ex.getLocalizedMessage());
		}
	}
}
