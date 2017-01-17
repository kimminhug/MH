<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="login_script.js" ></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>정보 수정</title>
</head>
<body>
<% String ID = (String)session.getAttribute("Login_ID");%>

<h1 align="center">정보 수정</h1>

<form name="login" method="post" >
<table border="1" align="center">
	<tr>
		<td align="center" width="150">ID</td>
		<td width="150" align="center"><%=ID %></td>
	</tr><tr>
		<td align="center">* 패스워드</td>
		<td width="150"><input type="password" name="pass"></td>
	</tr><tr>
		<td align="center">* 이름</td>
		<td width="150"><input type="text" name="name"></td>
	</tr><tr>
		<td align="center">* 전화번호</td>
		<td><input type="text" name="phone"></td>
	</tr><tr>
		<td align="center">이메일</td>
		<td><input type="text" name="email"></td>
	</tr><tr>
		<td align="center">성별</td>
		<td><input type="text" name="gender"></td>
	</tr><tr>
		<td colspan="2" align="center"><input type="button" value="변경" onClick='login_submit(6)'>  
		<input type="button" value="취소" onClick='history.back()'> </td>
	</tr>

</table>
</form>

</body>
</html>