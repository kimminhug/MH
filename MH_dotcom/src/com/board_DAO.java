package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;

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
			System.out.println("DB 테이블 메인 진입 실패! "+ex.getLocalizedMessage());
		}
		
		// DAO의 공통적인 부분(JDBC 드라이버 연결)은 생성자로 해결
	}
	
	public ArrayList<board_VO> select_Board(int limit_start, int pageSize){
		String query = "SELECT * FROM MH_dotcom.board ORDER BY ref DESC, ref_order ASC LIMIT "+limit_start+", "+pageSize ;	
		
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
				b_obj.setNotice(rs.getInt("notice"));
				b_obj.setRef(rs.getInt("ref"));
				b_obj.setStep(rs.getInt("step"));
				b_obj.setRef_order(rs.getInt("ref_order"));
				
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
	
	public ArrayList<board_VO> select_Notice(){
		String query = "SELECT * FROM MH_dotcom.board WHERE notice = 1 ORDER BY num DESC";	// (참고)순번은 내림차순이 최신부터 나온다
		ArrayList<board_VO> n_list = new ArrayList<board_VO>();
		
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
				b_obj.setNotice(rs.getInt("notice"));
				
				n_list.add(b_obj);
			}
			
			stmt.close();
			rs.close();
			
			return n_list;
			// DB로부터 게시판 정보를 완전히 빼낸 리스트를 서블릿으로 반환
			
		}catch(SQLException ex){
			System.out.println("SQL 게시판 리스트 에러 : "+ex.getLocalizedMessage());
		}
		
		return null;
	}
	
	public int select_Board_cnt(){
		String query = "SELECT count(*) as cnt FROM MH_dotcom.board ";	// (참고)순번은 내림차순이 최신부터 나온다
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
				b_obj.setNotice(rs.getInt("notice"));
				b_obj.setRef(rs.getInt("ref"));
				b_obj.setStep(rs.getInt("step"));
				b_obj.setRef_order(rs.getInt("ref_order"));
			}
			
			stmt.close();
			rs.close();
			
			return b_obj;
			
		}catch(SQLException ex){
			System.out.println("SQL 게시판 뷰 에러 : "+ex.getLocalizedMessage());
		}
		
		return null;
	}
	
	public boolean insert_Board(board_VO b_obj, int ref){
		String query = "INSERT INTO MH_dotcom.board(subject, name, content, notice, ref, step, ref_order) values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, b_obj.getSubject());
			pstmt.setString(2, b_obj.getName());
			pstmt.setString(3, b_obj.getContent());
			pstmt.setInt(4, b_obj.getNotice());
			if (ref == -1){
				pstmt.setInt(5, cal_Max_Num());		// 새글 등록의 경우, 이전 제일 큰 번호 + 1
			}else{
				pstmt.setInt(5, ref);				// 답글 등록의 경우, 부모글 ref와 같은 번호
			}
			pstmt.setInt(6, b_obj.getStep());
			pstmt.setInt(7, b_obj.getRef_order());
			
			int ext = pstmt.executeUpdate();
			pstmt.close();
			
			if (ext < 1){
				System.out.println("DB오류로 인해 정보 전송 실패!");
				return false;
			}
			
			return true;
			
		}catch(SQLException ex){
			System.out.println("DB insert error! : "+ex.getLocalizedMessage());
		}
		
		return false;
	}
	
	public boolean modify_Board(board_VO b_obj,int num){
		String query = "UPDATE MH_dotcom.board SET subject=?, name=?, content=?, notice=? WHERE num="+num;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, b_obj.getSubject());
			pstmt.setString(2, b_obj.getName());
			pstmt.setString(3, b_obj.getContent());
			pstmt.setInt(4, b_obj.getNotice());
			
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
	
	public int cal_Max_Ref_order(int ref){
		String query = "SELECT MAX(ref_order) as max FROM MH_dotcom.board WHERE ref="+ref;
		int max = 0;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()){
				max = rs.getInt("max");
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException ex){
			System.out.println("DB cal_Max_ref error! : "+ex.getLocalizedMessage());
		}
		
		return max+1;	// 답글그룹 중, 제일 높은 순서보다 +1하여 다음 답글순서로 반환
	}
	
	public int cal_Max_Num(){
		String query = "SELECT MAX(num) as max FROM MH_dotcom.board";
		int max = 0;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()){
				max = rs.getInt("max");
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException ex){
			System.out.println("DB cal_Max_num error! : "+ex.getLocalizedMessage());
		}
		
		return max+1;	// 답글그룹 중, 제일 높은 번호보다 +1하여 다음 번호로 반환
	}
	
	public int get_RowNum(int num){
		String query = "SELECT num FROM board ORDER BY ref DESC, ref_order";
		int rowNum = 0;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()){
				rowNum++;
				
				if (rs.getInt("num") == num){
					break;
				}
			}
			
			stmt.close();
			rs.close();
			
			return rowNum;
			
		}catch(SQLException ex){
			System.out.println("SQL rowNum error! : "+ex.getLocalizedMessage());
		}
		
		return -1;
	}
	
	public int[] get_Prev_Next(int rowNum){
		String query = "SELECT num FROM board ORDER BY ref DESC, ref_order";
		int[] numSet = {-1, -1};
		int cnt = 0;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()){
				cnt++;
				
				if (cnt == (rowNum-1)){
					numSet[0] = rs.getInt("num");
				}
				if (cnt == (rowNum+1)){
					numSet[1] = rs.getInt("num");
				}
			}
			
			stmt.close();
			rs.close();
			
			return numSet;
			
		}catch(SQLException ex){
			System.out.println("SQL rowNum error! : "+ex.getLocalizedMessage());
		}
		
		return null;
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
