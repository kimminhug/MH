package ch10;

import java.sql.*;
import java.util.*;

public class BoardDAO {
	// 생성자 -> 데이터베이스 연결 객체 지정
	private Connection conn = null;
	public BoardDAO (Connection conn) {
		this.conn = conn;
	}
	
	// 입력 메소드
	public int insertData(BoardDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			// hitcount는 0, created는 SYSDATE로 입력.
			sql = "INSERT INTO board (num, name, subject, content, pwd, created, ipaddr, hitcount, img, good, bad";
			sql += " VALUES (?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getIpaddr());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 수정 메소드
	public int updateData(BoardDTO dto){
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			sql = "UPDATE Board SET name=?, subject=?, content=?";
			sql += "WHERE num=? AND pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNum());
			pstmt.setString(5, dto.getPwd());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	// 특정 자료 읽기 메소드
	public BoardDTO getReadData(int num) {
		BoardDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			sql = "SELECT num, name, subject, content, DATE_FORMAT(created, '%Y-%m-%d') as created, ipaddr, hitcount";
			sql += " FROM board WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new BoardDTO();
				dto.setNum(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setSubject(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setCreated(rs.getString(5));
				dto.setIpaddr(rs.getString(6));
				dto.setHitcount(rs.getInt(7));
			}
			rs.close();
			pstmt.close();
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return dto;
	}
	
	// 전체 자료 읽기 메소드(오버로딩)
	public List<BoardDTO> getListsData() {
		List<BoardDTO> lists = new ArrayList<BoardDTO>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		
		// 현재는 게시판 전체 데이터를 읽어오는 것으로 작성함.
		sql = "SELECT num, name, subject, DATE_FORMAT(created, '%Y-%m-%d') as created";
		sql += ", hitcount FROM Board ORDER BY num DESC";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// 데이터베이스에서 읽어온 데이터가 여러개인 경우 - 반복문으로 처리
			while (rs.next()){
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setSubject(rs.getString(3));
				dto.setCreated(rs.getString(4));
				dto.setHitcount(rs.getInt(5));
				
				// 데이터베이스에서 읽어온 데이터가 여러개인 경우 - 컬렉션에 추가
				lists.add(dto);
			}
			rs.close();
			stmt.close();
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	// 특정 페이지 자료 읽기 메소드(오버로딩)
	public List<BoardDTO> getListsData(int start, int end){
		List<BoardDTO> lists = new ArrayList<BoardDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			sql = "SELECT * FROM (SELECT LIMIT rnum, DATA.* FROM simple_regist.board";
			sql += "(SELECT num, NAME, subject,";
			sql += "DATE_FORMAT(created, '%Y-%m-%d') as created, hitcount";
			sql += " FROM Board ORDER BY num DESC)";
			sql += "DATA )WHERE rnum >=? AND rnum <=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("created"));
				dto.setHitcount(rs.getInt("hitcount"));
				lists.add(dto);
			}
			
			rs.close();
			pstmt.close();
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		
		return lists;
	}
	
	// 전체 자료 갯수 읽기 메소드 -> 페이징 처리
	public int getDataCount() {
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			sql = "SELECT NVL(COUNT(*), 0) cnt FROM Board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next())
				result = rs.getInt(1);
			
			rs.close();
			stmt.close();
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return result;
	}
	
	// 일반 사용자용 삭제 메소드(오버로딩)
	public int deleteData(int num, String pwd){
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			sql = "DELETE FROM Board WHERE num=? AND pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, pwd);
			result = pstmt.executeUpdate();
			pstmt.close();
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return result;
	}
	
	// 관리자용 삭제 메소드(오버로딩)
	public int deleteData(int num){
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			sql = "DELETE FROM Board WHERE num=? AND pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			pstmt.close();
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return result;
	}
	
	// 글번호 지정을 위한 기존 번호 최대값 얻는 메소드
	public int getMaxNum() {
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			sql = "SELECT NVL(MAX(num), 0) FROM Board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next())
				result = rs.getInt(1);
			
			rs.close();
			stmt.close();
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return result;
	}
	
	// 조회수 업데이트 메소드
	public int updateHitCount(int num) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_Regist", "root", "1111");
			
			sql = "UPDATE Board SET hitcount=hitcount+1";
			sql += "WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			pstmt.close();
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return result;
	}
}
