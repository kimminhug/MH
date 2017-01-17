<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 코어 사용을 위한 taglib 디렉티브 지정 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="commong/common.css" type="text/css"/>
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<link rel="stylesheet" href="css/BoardContent.css" type="text/css"/>

<title>MH.com 자유게시판</title>
</head>

<body>
<div align="center" style="padding-top:100px;">
<form action="adminLogin_ok.do" method="post">
	<p>MH 관리자만 사용할 수 있는 페이지입니다</p><br>
	<p><a href="list.do">[홈으로 돌아가기]</a></p>
		<table class="style4">
			<tr valign="middle">
				<td><img src="https://s23.postimg.org/9g8y2xuwb/MH_logo.png"><br>
					<br>
				<br>
					<table>
						<tr>
							<td><b>ID</b></td>
							<!--  JSP와 연동을 위해 name 속성 추가! -->
							<td><input type="text" class="boxTF" style="width: 150px;"
								name="uid"></td>
						</tr>
						<tr>
							<td><b>PW</b></td>
							<td><input type="password" class="boxTF"
								style="width: 150px;" name="upw"></td>
						</tr>
						<td colspan="2" align="center"><br> <input class="btn2"
							type="submit" value="로그인"><br>
						<br>
						<tr>
							<td colspan="2" align="center"><span style="color: red">${errMsg}</span><br>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</form>
</div>
</body>
</html>