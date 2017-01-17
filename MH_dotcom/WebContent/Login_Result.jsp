<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script type="text/javascript" src="login_script.js" ></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 결과</title>
</head>
<body>
<form name="login" method="post">
<% 
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	request.setCharacterEncoding("UTF-8");
	String ID = request.getParameter("ID");
	String pass = request.getParameter("pass");
	
	session.setAttribute("Login_ID", ID);
	String name = "", phone = "", email = "", gender = "";
	
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Simple_regist", "root", "1111");
		// Connetion 생성!
		
		stmt = conn.createStatement();
		// Statement 생성!
		
		rs = stmt.executeQuery("select * from info where ID = '"+ID+"' AND pass = '"+pass+"' ");
		// ID, 패스워드가 일치하는 결과값만을 ResultSet에 저장.
		
		if (rs.next()){
			name = rs.getString("name");
			phone = rs.getString("phone");
			email = rs.getString("email");
			gender = rs.getString("gender");
		}
		if (name.equals("")){
%>
			<script> login_alert();</script>		<!-- 아이디/비밀번호를 잘못 입력했을시, 경고후 뒤로가기처리 -->
<%		return;
		}
%>	
<h3><%=name %>님 안녕하세요?</h3>

<table cellpadding="6" bordercolor="lightblue" border="1">
	<tr>
			<td><strong>ID</strong></td>
			<td><strong>이름</strong></td>
			<td><strong>전화번호</strong></td>
			<td><strong>이메일</strong></td>
			<td><strong>성별</strong></td>
	</tr>
	<tr>
			<td><%=ID %></td>
			<td><%=name %></td>
			<td><%=phone %></td>
			<td><%=email %></td>
			<td><%=gender %></td>
	</tr>
</table>
<br>

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
<input type="button" value="로그아웃" onClick='login_submit(3)'>   
<input type="button" value="정보 수정" onClick='login_submit(4)'>  
<input type="button" value="회원목록" onClick='login_submit(5)'>
</form>

</body>
</html>