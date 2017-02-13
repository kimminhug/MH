<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
    <script type="text/javascript" src="LevelUp.js" ></script>
    <script type="text/javascript" src="circle-progress.js" ></script>
    
	<link href="LevelUp.css?ver=1" media="screen and (min-width: 1px) and (max-width: 1024px)" rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<meta name="viewport" content="width=device-width">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Life LevelUp</title>
</head>
<body>
<div id="container">
	<%@include file="menu.jsp"%>

    <div id="contentLayer"></div> <%-- 여기서부터 메뉴바깥 '내용' 부분 --%>
    <div id="content">
    	<div class="card">
			<h3>안녕하세요, <%= (String)session.getAttribute("name") %>님!</h3>
			<h3>당신의 신체레벨 : <%=(int)session.getAttribute("b_level") %></h3>
			
			<h3>표준체중 : <%= (double)session.getAttribute("average") %></h3>
			<h3>BMI : <%= (double)session.getAttribute("BMI") %></h3>
			<h3>비만도 : <%= (double)session.getAttribute("obesity") %>%</h3>
			<h3>기초대사량 : <%= (double)session.getAttribute("BMR") %>cal</h3>
			
			<input class="button-white" type="button" value=" < 재입력 " onClick='history.back()'>
		</div>
	</div>
</div>
</body>
</html>