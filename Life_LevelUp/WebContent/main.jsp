<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
    <script type="text/javascript" src="LevelUp.js" ></script>
    
	<link href="LevelUp.css?ver=1" media="screen and (min-width: 1px) and (max-width: 1024px)" rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<meta name="viewport" content="width=device-width">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Life LevelUp</title>
</head>
<body>
<div id="container">

	<header>
	<% if ((String)session.getAttribute("ID") == null){%>
		<div id="left-box">
			<a id="not-login" href="login.html"><img id="logo-img" src="image/logo.png"></a>
		</div>
		<div id="right-box">
			<div id="comu-menu"><i id="logo-img" class="material-icons">&#xE8FE;</i></div>
		</div>
        <div id="center-box">
			<a href="main.jsp"><div id="main-banner">Life LevelUp!</div></a>
		</div>
	<%}else{ %>
        <div id="left-box">
        	<div id="user-menu"><img id="logo-img" src="image/logo.png"></div>
        </div>
        <div id="right-box">
			<div id="comu-menu"><i id="logo-img" class="material-icons">&#xE8FE;</i></div>
		</div>
        <div id="center-box">
			<a href="main.jsp"><div id="main-banner">Life LevelUp!</div></a>
		</div>
    <%} %>
    </header>
    
    <nav>
        <ul>
            <li id="my">
            <div id="my-cha">
            	<a id="left-box" href="#">내 캐릭터</a>
            	<div id="right-box"><input class="button-con-logout" type="button" value="로그아웃" onClick="logout();" /></div>
            </div>
            </li>
            <li id="condition">
            	<h3>Lv. <%=session.getAttribute("level")%> <%=session.getAttribute("name")%></h3>
            	<h4>신체Lv : <%=session.getAttribute("b_level")%></h4>
            	<h4>신체경험치: <%=session.getAttribute("b_exp")%> / 100</h4>
            	<h4>운동레벨: <%=session.getAttribute("e_level")%></h4>
            </li>
            <li><a href="#">친구목록</a></li>
        </ul>
    </nav>

    <div id="contentLayer"></div> <%-- 여기서부터 메뉴바깥 '내용' 부분 --%>
    
    <div id="content">
    	
    </div>
</div>
</body>
</html>