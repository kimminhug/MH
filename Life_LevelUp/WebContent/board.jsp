<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.util.ArrayList, java.util.Date, java.text.*, com.beans.*" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
    <script type="text/javascript" src="LevelUp.js?ver=1" ></script>
    <script type="text/javascript" src="circle-progress.js?ver=1" ></script>
    <script type="text/javascript" src="FileButton.js?ver=1" ></script>
    <script type="text/javascript" >
    jQuery(document).ready(function () {
    	// 글쓰기 토글키
  		$("#con-write").css('display', 'none');
    
  		$("#flip-write").click(function(){
	  		$("#con-write").slideToggle("500");			
    	});
    });
  	</script>
	<link href="LevelUp.css?ver=1" media="screen and (min-width: 1px) and (max-width: 1024px)" rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<meta name="viewport" content="width=device-width">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Free board</title>
</head>
<body>
<div id="container">
	
	<%@include file="menu.jsp"%>
	
	<div id="contentLayer"></div> <%-- 여기서부터 메뉴바깥 '내용' 부분 --%>
    <div id="content">
    
    <div id="con-write">
    	<div id="left-box">
    		<textarea name="contents" cols=80% rows=5 class="input"></textarea>
    	</div>
    	<div id="right-box">
    		<div id="board-line-left">
    			<input id="file-input" type="file" name="file-input" accept="image/*"/>	
    			<%-- 디폴트 인풋 파일폼 대신, 오버랩된 아이콘으로 동작 --%>
        		<i id="icon-write" class="material-icons">&#xE3B6;</i>
    		</div>
    		<div id="board-line-left">
    			<button class="button-write" type="submit" name="write"><i class="material-icons">&#xE255;</i></button>
    		</div>
    	</div>
    </div>
    <div id="flip-write">
    	<i class="material-icons">&#xE3C9;</i>&nbsp;글 작성
    </div>
        
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
			<div id="board-line-subject"><%= board.getContents() %></div>
			<div id="con-mid">
				<div id="left-box">
           			<div id="board-line-left">Lv.<%= board.getLevel() %> <%= board.getName() %></div>
				</div>
           		<div id="right-box">
           			<div id="board-line-right"><%= board.getDay().substring(2, 16) %></div>
           		</div>			
			</div>
           	<div id="con-right">
           		<i id="icon-board" class="material-icons">&#xE8F4;</i>&nbsp;<%= board.getViews() %>&nbsp;&nbsp;
           		<i id="icon-board" class="material-icons">&#xE0B9;</i>&nbsp;<%= board.getReple() %>&nbsp;&nbsp;
           		<i id="icon-board" class="material-icons">&#xE8DC;</i>&nbsp;<%= board.getGood() %>&nbsp;&nbsp;
           		<i id="icon-board" class="material-icons">&#xE8DB;</i>&nbsp;<%= board.getBad() %>
           	</div>		 
		</div>
<% 
	}
%>
	</div>
</div>
</body>
</html>
	