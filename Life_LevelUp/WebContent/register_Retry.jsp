<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 만들어야될 것 : 세션 리스너 - 만료되었을 시 다른창 띄우기 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.1.0.min.js"></script>
<title>가입 정보 확인</title>
</head>
<body>

<form name="register_Retry" action="register_Retry.kim" method="post">
	ID : <%= (String)session.getAttribute("ID") %><br>
	닉네임 : <%= (String)session.getAttribute("name") %><br>
	<br>
	BMI : <%= (double)session.getAttribute("BMI") %><br>
	비만도 : <%= (double)session.getAttribute("obesity") %>%<br>
	BMR : <%= (double)session.getAttribute("BMR") %>cal<br>
	<br>
	당신의 레벨 : <%=(int)session.getAttribute("level") %><br>
	<br>
	<input type="button" value=" < 재입력 " onClick='history.back()'>&nbsp;
	<input type="submit" value=" 계속 > "/>
</form>

</body>
</html>