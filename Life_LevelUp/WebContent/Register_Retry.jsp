<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="cal" class="com.beans.Cal_bean" scope="page"/>

<!-- 만들어야될 것 : 세션 리스너 - 만료되었을 시 다른창 띄우기 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>가입 정보 확인</title>
</head>
<body>

	ID : <%= (String)session.getAttribute("ID")%><br>
	닉네임 : <%= (String)session.getAttribute("name")%><br>

</body>
</html>