package com;

import java.sql.*;
import java.util.ArrayList;

public class board_DAO {
	private static Connection conn;
	
	public board_DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex){
			System.out.println("JDBC 드라이버 연결 실패!");
		} 
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MH_dotcom", "root", "flash21");
		}catch(SQLException ex){
			System.out.println("SQL 오류! "+ex.getLocalizedMessage());
		}
		
		// DAO의 공통적인 부분(JDBC 드라이버 연결)은 생성자로 해결
	}
	
	public ArrayList<board_VO> select_Board(int limit_start, int limit_end){
		String query = "SELECT * FROM MH_dotcom.board ORDER BY num DESC LIMIT "+limit_start+", "+limit_end ;	// (참고)순번은 내림차순이 최신부터 나온다
		ArrayList<board_VO> b_list = new ArrayList<board_VO>();
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()){
				board_VO b_obj = new board_VO();
				
				b_obj.setNum(rs.getInt("num"));
				b_obj.setSubject(rs.getString("subject"));
				b_obj.setName(rs.getString("name"));
				b_obj.setDate(rs.getString("date").substring(0, 16));
				b_obj.setContent(rs.getString("content"));
				
				b_list.add(b_obj);
			}
			
			stmt.close();
			rs.close();
			
			return b_list;
			// DB로부터 게시판 정보를 완전히 빼낸 리스트를 서블릿으로 반환
			
		}catch(SQLException ex){
			System.out.println("SQL 게시판 리스트 에러 : "+ex.getLocalizedMessage());
		}
		
		return null;
	}
	
	public int select_Board_cnt(){
		String query = "SELECT count(*) as cnt FROM MH_dotcom.board";	// (참고)순번은 내림차순이 최신부터 나온다
		int cnt = 0;
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()){
				cnt = rs.getInt("cnt");
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException ex){
			System.out.println("SQL 게시판 리스트 에러 : "+ex.getLocalizedMessage());
		}
		
		return cnt;
	}
	
	public board_VO view_Board(int num){
		String query = "SELECT * FROM MH_dotcom.board WHERE num = "+num;
		board_VO b_obj = new board_VO();
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()){
				b_obj.setNum(rs.getInt("num"));
				b_obj.setSubject(rs.getString("subject"));
				b_obj.setName(rs.getString("name"));
				b_obj.setDate(rs.getString("date").substring(0, 16));
				b_obj.setContent(rs.getString("content"));
			}
			
			stmt.close();
			rs.close();
			
			return b_obj;
			
		}catch(SQLException ex){
			System.out.println("SQL 게시판 뷰 에러 : "+ex.getLocalizedMessage());
		}
		
		return null;
	}
	
	public boolean insert_Board(board_VO b_obj){
		String query = "INSERT INTO MH_dotcom.board(subject, name, content) values (?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, b_obj.getSubject());
			pstmt.setString(2, b_obj.getName());
			pstmt.setString(3, b_obj.getContent());
			
			int ext = pstmt.executeUpdate();
			pstmt.close();
			
			if (ext < 1){
				System.out.println("DB오류로 인해 정보 전송 실패!");
				return false;
			}
			
		}catch(SQLException ex){
			System.out.println("DB insert error! : "+ex.getLocalizedMessage());
		}
		
		return true;
	}
	
	public boolean modify_Board(board_VO b_obj,int num){
		String query = "UPDATE MH_dotcom.board SET subject=?, name=?, content=? WHERE num="+num;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, b_obj.getSubject());
			pstmt.setString(2, b_obj.getName());
			pstmt.setString(3, b_obj.getContent());
			
			int ext = pstmt.executeUpdate();
			pstmt.close();
			
			if (ext < 1){
				System.out.println("DB오류로 인해 정보 전송 실패!");
				return false;
			}
			pstmt.close();
			
		}catch(SQLException ex){
			System.out.println("DB update error! : "+ex.getLocalizedMessage());
		}
		
		return true;
	}
	
	public boolean delete_Board(int num){
		String query = "DELETE FROM MH_dotcom.board WHERE num="+num;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			int ext = pstmt.executeUpdate();
			pstmt.close();
			
			if (ext < 1){
				System.out.println("DB오류로 인해 정보 전송 실패!");
				return false;
			}
			
		}catch(SQLException ex){
			System.out.println("DB delete error! : "+ex.getLocalizedMessage());
		}
		
		return true;
	}
	
	public void close(){
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
