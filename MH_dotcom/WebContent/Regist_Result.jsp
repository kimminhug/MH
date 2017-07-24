<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="login_script.js" ></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>환영합니다!</title>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");
	String ID = request.getParameter("ID");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	String gender = request.getParameter("gender");
	
	Connection conn = null;
	Statement stmt = null;
	
	if (ID.equals("") || pass.equals("") || name.equals("") || phone.equals("")){
%>
		<script> modify_alert();</script>		<!-- 필수정보를 미입력시, 경고후 뒤로가기처리 -->
<%
		return;		// 빈칸입력 방지를 위한 종료문 
	}
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MH_dotcom", "root", "flash21");
		stmt = conn.createStatement();
		
		String query = 
			"insert into mh_dotcom.member(ID, pass, name, phone, email, gender) "+	// <- 모든 값을 입력할때엔 멤버변수는 생략 가능!
			"values('"+ID+"', '"+pass+"', '"+name+"', '"+phone+"', '"+email+"', '"+gender+"')";
		// INSERT INTO info(ID, pass, name, phone, email, gender) 
		// VALUES('ID', 'pass', 'name', 'phone', 'email', 'gender')
	
		stmt.executeUpdate(query);
%>

<br>
<h1 align="center"><%=name %>님, 환영합니다!</h1>

<table border="1" align="center">
<form name="info" method="post" action="/admin" >
	<tr>
		<td align="center" width="100" >ID</td>
		<td width="150"><%=ID %></td>
	</tr><tr>
		<td align="center">이름</td>
		<td width="150"><%=name %></td>
	</tr><tr>
		<td align="center">전화번호</td>
		<td><%=phone %></td>
	</tr><tr>
		<td align="center">이메일</td>
		<td><%=email %></td>
	</tr><tr>
		<td align="center">성별</td>
		<td><%=gender %></td>
	</tr><tr>
		<td colspan="2" align="center">
		<input type="submit" value="메인으로"></td>
	</tr>
</form> 
</table>
<br>
<%
	} catch (SQLException ex){
		System.out.println("SQL Exception! " + ex);
%>
		<script> regist_alert();</script>		<!-- 정보를 잘못 입력했을시, 경고후 뒤로가기처리 -->
<%	
	} catch (Exception ex){
		System.out.println("Exception : " + ex);
%>
		<script> regist_alert();</script>		<!-- 정보를 잘못 입력했을시, 경고후 뒤로가기처리 -->
<%
	} finally {
		if (stmt != null)
			try { stmt.close(); } catch (SQLException ex) {}
		if (conn != null)
			try { conn.close(); } catch (Exception ex) {}
	}
%>

</body>
</html>