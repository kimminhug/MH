<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script type="text/javascript" src="login_script.js" ></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정 결과</title>
</head>
<body>
<form name="login" method="post">
<% 
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	int up = 0;			// 업데이트 되는 여부 (1 : 됨, 0 : 안됨)
	
	request.setCharacterEncoding("UTF-8");
	String ID = (String)session.getAttribute("Login_ID");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	String gender = request.getParameter("gender");
	
	if (pass.equals("") || name.equals("") || phone.equals("")){
%>
		<script> modify_alert();</script>		<!-- 필수정보를 미입력시, 경고후 뒤로가기처리 -->
<%		
		return;			// 빈칸입력 방지를 위한 종료문 
	}
	
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MH_dotcom", "root", "flash21");
		// Connetion 생성!
		
		stmt = conn.createStatement();
		// Statement 생성!
		
		up = stmt.executeUpdate("UPDATE mh_dotcom.member "+
		"SET pass = '"+pass+"', name = '"+name+"', phone = '"+phone+"', email = '"+email+"', gender = '"+gender+
		"' WHERE ID = '"+ID+"' ");
		// 해당하는 ID의 DB값을 넘어온 값으로 수정함.
		
		rs = stmt.executeQuery("select * from mh_dotcom.member where ID = '"+ID+"' ");
		// 해당 ID의 변경된 값을 확인하기 위해 결과를 저장.
		
		if (rs.next()){
			name = rs.getString("name");
			phone = rs.getString("phone");
			email = rs.getString("email");
			gender = rs.getString("gender");
		}

%>	
<h3>변경 완료되었습니다!</h3>

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
%>
		<script> modify_alert();</script>		<!-- 정보를 잘못 입력했을시, 경고후 뒤로가기처리 -->
<%	
	} catch (Exception ex){
		System.out.println("Exception : " + ex);
%>
		<script> modify_alert();</script>		<!-- 정보를 잘못 입력했을시, 경고후 뒤로가기처리 -->
<%
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
<input type="button" value="다시수정" onClick='history.back()'>  
<input type="button" value="메인으로" onClick='login_submit(2)'>
</form>

</body>
</html>