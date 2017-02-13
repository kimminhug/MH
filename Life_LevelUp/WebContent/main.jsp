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
            	<div id="con-mid">
            		<span id="lv_eng_font">Lv. </span>
            		<span id="lv_num_font"><%=session.getAttribute("level")%></span>
            	</div>
            	<div id="con-mid"><h3><%=session.getAttribute("name")%></h3></div>
            	<div id="cir-warp">
            		<div id="left-box">
            			<span id=left-label><strong><%=session.getAttribute("b_exp")%></strong><i>%</i></span>
						<div class="left-circle"></div>
						<div id="con-mid">
							<span id="lv_b_font">신체 
							<span id="lv_eng_font"> Lv.</span>
							<%=session.getAttribute("b_level")%></span>
						</div>
					</div>
            		<div id="right-box">
						<span id=right-label><strong><%=session.getAttribute("e_exp")%></strong><i>%</i></span>
						<div class="right-circle"></div>
						<div id="con-mid">
							<span id="lv_e_font">운동
							<span id="lv_eng_font"> Lv.</span>
							<%=session.getAttribute("e_level")%></span>
						</div>
					</div>
				</div>
				<div id="con-mid">
					<input class="button-con-exp" type="button" value="경험치입력" onClick="input_exp();" />
				</div>
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