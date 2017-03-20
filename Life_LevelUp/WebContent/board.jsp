<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.util.ArrayList, java.util.Date, java.text.*, com.beans.*" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
    <script type="text/javascript" src="LevelUp.js?ver=1" ></script>
    <script type="text/javascript" src="circle-progress.js?ver=1" ></script>
    
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
    
<% 
	ArrayList<Board> b_list = (ArrayList<Board>) application.getAttribute("board_list");
	int count = 0;
	
	for(Board board : b_list) {
		count++;
		
		if (b_list == null){
			return;
		}
%>	
    	<div class="card-board">
    		<div class="card-header-rank">
    			<%-- 이미지 들어갈 칸 --%>
			</div>
			<div id="board-line-subject"><%= board.getSubject() %></div>
			<div id="con-mid">
				<div id="left-box">
           			<div id="board-line-left">Lv.<%= board.getLevel() %> <%= board.getName() %></div>
				</div>
           		<div id="right-box">
           			<div id="board-line-right"><%= board.getDay().substring(2, 16) %></div>
           		</div>			
			</div>
           	<div id="con-right">
           		<i id="board-icon" class="material-icons">&#xE0B9;</i>&nbsp;<%= board.getReple() %>&nbsp;&nbsp;
           		<i id="board-icon" class="material-icons">&#xE815;</i>&nbsp;<%= board.getGood() %>&nbsp;&nbsp;
           		<i id="board-icon" class="material-icons">&#xE814;</i>&nbsp;<%= board.getBad() %>
           	</div>		 
		</div>
<% 
	}
%>
	</div>
</div>
</body>
</html>
	