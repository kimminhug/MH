<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.sql.*, java.util.*, ch10.*" %>
    
<% 
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String ID="", pass="", name = "", phone = "", email = "", gender = "";
	int counter = 0;
	
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_regist", "root", "1111");
		// Connetion 생성! - DB 드라이버를 연결함
		
		stmt = conn.createStatement();
		// Statement 생성! - 연결된 DB에 쿼리를 실행하기 위해 statement 객체를 생성.
		
		rs = stmt.executeQuery("select * from info");
		// 쿼리가 실행된 결과를 ResultSet에 담는다.(테이블의 모든 정보를 담음)
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="login_script.js" ></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 목록</title>
</head>
<body bgcolor="beige">

<h3>회원정보</h3>
<table cellpadding="6" bordercolor="lightblue" border="1">
	<tr>
			<td><strong>ID</strong></td>
			<td><strong>패스워드</strong></td>
			<td><strong>이름</strong></td>
			<td><strong>전화번호</strong></td>
			<td><strong>이메일</strong></td>
			<td><strong>성별</strong></td>
	</tr>
	<% 	if (rs != null) {
				while (rs.next()) {
					ID = rs.getString("ID");
					pass = rs.getString("pass");
					name = rs.getString("name");
					phone = rs.getString("phone");
					email = rs.getString("email");
					gender = rs.getString("gender");
	%>
	<tr>
			<td><%=ID %></td>
			<td><%=pass %></td>
			<td><%=name %></td>
			<td><%=phone %></td>
			<td><%=email %></td>
			<td><%=gender %></td>
	<%		
				counter++;
				}	// end while
			}  // end if
	%>
	</tr>
</table>
<br>
<br> total records : <%=counter %>
<%
	} catch (SQLException ex){
		System.out.println("SQL Exception!" + ex);
	} catch (Exception ex){
		System.out.println("Exception : " + ex);
	} finally {
		if (rs != null)
			try { rs.close(); } catch (SQLException ex) {}
		if (stmt != null)
			try { stmt.close(); } catch (SQLException ex) {}
		if (conn != null)
			try { conn.close(); } catch (Exception ex) {}
	}

%>
<br>
<form name="login" method="post">
<input type="button" value="로그아웃" onClick='login_submit(3)'>  
<input type="button" value="뒤로가기" onClick='history.back()'></form>

</body>
</html>