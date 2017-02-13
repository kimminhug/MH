package com.patten;

import java.sql.*;

public class Connect_DAO {
	private static Connection conn;
	
	public Connect_DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex){
			System.out.println("JDBC 드라이버 연결 실패!");
		} 
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LevelUp", "root", "1111");
		}catch(SQLException ex){
			System.out.println("SQL 오류! "+ex.getLocalizedMessage());
		}
	}
	
	public boolean login_LevelUp(Member_DTO m_dto, Level_DTO l_dto, String ID, String hash){
		String m_query = "select * from LevelUp.member where ID = '"+ID+"'AND hash = '"+hash+"'";
		String l_query = "select * from LevelUp.level where ID = '"+ID+"'";
		
		try {
			Statement stmt = conn.createStatement(); 
			// Statement 생성!
			
			ResultSet rs = stmt.executeQuery(m_query);
			// ID, 패스워드가 일치하는 결과값만을 ResultSet에 저장.
			
			if (rs.next()){
				m_dto.setID(rs.getString("ID"));
				m_dto.setSalt(rs.getString("salt"));
				m_dto.setHash(rs.getString("hash"));
				m_dto.setName(rs.getString("name"));
				m_dto.setSex(rs.getInt("sex"));
				m_dto.setAge(rs.getInt("age"));
				m_dto.setHeight(rs.getInt("height"));
				m_dto.setWeight(rs.getInt("weight"));
				m_dto.setJob(rs.getString("job"));
				m_dto.setArea(rs.getString("area"));
				m_dto.setIntro(rs.getString("intro"));
				m_dto.setSex_vis(rs.getInt("sex_vis"));
				m_dto.setAge_vis(rs.getInt("age_vis"));
				m_dto.setHei_vis(rs.getInt("hei_vis"));
				m_dto.setWei_vis(rs.getInt("wei_vis"));
			}
			
			int sex = m_dto.getSex();
			int age = m_dto.getAge();
			int height = m_dto.getHeight();
			int weight = m_dto.getWeight();
			
			rs = stmt.executeQuery(l_query);
			
			if (rs.next()){
				l_dto.setID(rs.getString("ID"));
				l_dto.setLevel(rs.getInt("level"));
				l_dto.setB_level(rs.getInt("b_level"));
				l_dto.setB_exp(rs.getDouble("b_exp"));
				l_dto.setE_level(rs.getInt("e_level"));
				l_dto.setE_exp(rs.getInt("e_exp"));
				l_dto.setAverage(sex, height);
				l_dto.setBMI(height, weight);
				l_dto.setBMR(sex, age, height, weight);
				l_dto.setObesity(weight, l_dto.getAverage());
			}
			
			stmt.close();
			rs.close();
			
			System.out.println("입렵한 아이디 : "+ID+", 조회된 아이디 : "+m_dto.getID());
			System.out.println("입렵한 비번 : "+hash+", 조회된 비번 : "+m_dto.getHash());
			
			try{
				if (!m_dto.getHash().equals(hash)){
					return false;
				}
			}catch(NullPointerException ex){
				return false;
			}
		}catch(SQLException ex){
			System.out.println("SQL Login error : "+ex.getLocalizedMessage());
		}
		
		return true;
	}
	
	public boolean insertMember(Member_DTO dto){
		String query = "insert into levelup.member values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getID());
			pstmt.setString(2, dto.getSalt());
			pstmt.setString(3, dto.getHash());
			pstmt.setString(4, dto.getName());
			pstmt.setInt(5, dto.getSex());
			pstmt.setInt(6, dto.getAge());
			pstmt.setDouble(7, dto.getHeight());
			pstmt.setDouble(8, dto.getWeight());
			pstmt.setString(9, dto.getJob());
			pstmt.setString(10, dto.getArea());
			pstmt.setString(11, dto.getIntro());
			pstmt.setInt(12, dto.getSex_vis());
			pstmt.setInt(13, dto.getAge_vis());
			pstmt.setInt(14, dto.getHei_vis());
			pstmt.setInt(15, dto.getWei_vis());
			
			int x = pstmt.executeUpdate();
			
			pstmt.close();
			
			if (x < 1){
				System.out.println("DB전송 실패!");
				return false;
			}
		}catch(SQLException ex){
			System.out.println("SQL insert error : "+ex.getLocalizedMessage());
		}
		
		return true;
	}
	
	public boolean insertLevel(Level_DTO dto){
		String query = "insert into levelup.level values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getID());
			pstmt.setInt(2, dto.getLevel());
			pstmt.setInt(3, dto.getB_level());
			pstmt.setDouble(4, dto.getB_exp());
			pstmt.setInt(5, dto.getE_level());
			pstmt.setInt(6, dto.getE_exp());
			pstmt.setDouble(7, dto.getAverage());
			pstmt.setDouble(8, dto.getBMI());
			pstmt.setDouble(9, dto.getBMR());
			pstmt.setDouble(10, dto.getObesity());
			
			int x = pstmt.executeUpdate();
			
			pstmt.close();
			
			if (x < 1){
				System.out.println("DB전송 실패!");
				return false;
			}
			
		}catch(SQLException ex){
			System.out.println("SQL insert error : "+ex.getLocalizedMessage());
		}
		
		return true;
	}
	
	public boolean updateMember(Member_DTO dto){
		
		return true;
	}
	
	public void close(){
		try {
			if (conn != null){
				conn.close();
				conn = null;
			}
		}catch(SQLException ex){
			System.out.println("SQL error : "+ex.getLocalizedMessage());
		}
	}
}
