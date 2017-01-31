<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 만들어야될 것 : 뒤로가기 시 정복중복될경우 페이지 거르기 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.1.0.min.js"></script>
<title>축하합니다!</title>
</head>
<body>

<form name="register_comp" action="mypage.jsp" method="post">
	<%= (String)session.getAttribute("name")%>님만의 캐릭터가 생성되었습니다!<br>
	축하합니다!!<br>
	<br>
	<br>
	Lv : <%= (int)session.getAttribute("level")%>
	<br>
	<input type="submit" value=" 마이페이지 "/>
</form>

</body>
</html>