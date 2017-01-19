package com.patten;

import java.sql.*;

public class Member_DAO {
	private static Connection conn;
	
	public Member_DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex){
			System.out.println("드라이버를 찾을 수 없습니다!");
		}
		
		try{
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/LevelUp", "root", "1111");
		}catch(SQLException ex){
			System.out.println("SQL 오류! "+ex.getLocalizedMessage());
		}
	}
	
	public boolean insertMember(Member_DTO dto){
		String query = "insert into levelup.member values (?, ?, ?, ?)";
		boolean check = false;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getTel());
			pstmt.setString(4, dto.getAddr());
			
			int x = pstmt.executeUpdate();
			
			if (x < 1){
				System.out.println("정상적으로 저장되지 않았습니다!");
			}else{
				check = true;
			}
			
			pstmt.close();
			
		}catch(SQLException ex){
			System.out.println("SQL insert 오류 : "+ex.getLocalizedMessage());
		}
		
		return true;
	}
	
	public boolean updateMember(Member_DTO dto){
		
		return true;
	}
	
	public Member_DTO selectMember(){
		
		return null;
	}
	
	public void close(){
		try {
			if (conn != null){
				conn.close();
				conn = null;
			}
		}catch(SQLException ex){
			System.out.println("SQL 오류 : "+ex.getLocalizedMessage());
		}
	}
}
