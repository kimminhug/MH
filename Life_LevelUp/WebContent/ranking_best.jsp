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
	
	<title>Ranking</title>
</head>
<body>
<div id="container">
	
	<%@include file="menu.jsp"%>
	
	<div id="contentLayer"></div> <%-- 여기서부터 메뉴바깥 '내용' 부분 --%>
    <div id="content">
    
	<%@include file="rank_menu.jsp"%>

    	<div class="card-rank">
    		<div class="card-header-rank">
				<h2>내 순위</h2>
			</div>
			<div id="con-mid">
				<div id="left-box">
					<div id="rank-line-avarta">??위</div>
					<div id="rank-line-avarta"><img id="avarta-rank" src="image/avarta.jpg"></div>
				</div>
           		<div id="center-box">
           			<div id="rank-line-name">Lv.?? 내 닉네임</div>
           			<div id="rank-line-word">등록한 내 한마디</div></div>           	
           		<div id="right-box">
           			<div id="rank-line-score">??↑</div>
           		</div>			
			</div>
		</div>
	</div>
</div>
</body>
</html>
	