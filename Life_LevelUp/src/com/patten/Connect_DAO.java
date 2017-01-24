package com.patten;

import java.sql.*;

public class Connect_DAO {
	private static Connection conn;
	
	public Connect_DAO() {
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
		String query = "insert into levelup.member values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean check = false;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getID());
			pstmt.setString(2,  dto.getSalt());
			pstmt.setString(3, dto.getHash());
			pstmt.setString(4, dto.getName());
			pstmt.setInt(5, dto.getSex());
			pstmt.setInt(6, dto.getAge());
			pstmt.setDouble(7, dto.getHeight());
			pstmt.setDouble(8, dto.getWeight());
			pstmt.setString(9, dto.getArea());
			pstmt.setString(10, dto.getIntro());
			pstmt.setInt(11, dto.getSex_vis());
			pstmt.setInt(12, dto.getAge_vis());
			pstmt.setInt(13, dto.getHei_vis());
			pstmt.setInt(14, dto.getWei_vis());
			
			int x = pstmt.executeUpdate();
			
			if (x < 1){
				System.out.println("정상적으로 저장되지 않았습니다!");
			}else{
				check = true;
			}
			
			pstmt.close();
			// 컨넥션은 아직 닫지 않는다.
			
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
