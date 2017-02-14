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
		<div id="my-menu"><h1>안녕하세요, <%= (String)session.getAttribute("name") %>님!</h1></div>
            
        <div id="my-menu">
           	<div id="my-left-box">
           		<img id="avarta-my" src="image/avarta.jpg">
           		<div id="con-mid">
            		<span id="lv_eng_font">Lv. </span>
           			<span id="lv_num_font"><%=session.getAttribute("level")%></span>
       			</div>
           	</div>
            
            <div id="my-right-box">
            	<div id="con-mid"><input class="button-my-detail" type="button" value="상세정보" onClick="location.href='#'" /></div>
            	<div id="con-mid"><input class="button-my-detail" type="button" value="친구목록" onClick="location.href='#'" /></div>
            	<div id="con-mid"><input class="button-my-detail" type="button" value=" 쪽지함  " onClick="location.href='#'" /></div>
            	<div id="con-mid"><input class="button-my-detail" type="button" value="로그아웃" onClick="logout();" /></div>
            </div>
       </div>
       
       <div id="my-circle">     
       <div id="cir-warp">
            <div id="left-box">
            	<span id=my-left-label><strong><%=session.getAttribute("b_exp")%></strong><i>%</i></span>
				<div class="my-left-circle"></div>
				<div id="con-mid">
					<div id="lv_exp_font"><%=session.getAttribute("b_exp")%> / 100 </div>
					<span id="lv_b_font">신체 
					<span id="lv_eng_font"> Lv.</span>
					<%=session.getAttribute("b_level")%></span>
				</div>
			</div>
            <div id="right-box">
				<span id=my-right-label><strong><%=session.getAttribute("e_rate")%></strong><i>%</i></span>
				<div class="my-right-circle"></div>
				<div id="con-mid">
					<div id="lv_exp_font">
						<%=session.getAttribute("e_exp")%> / <%=session.getAttribute("e_req_exp")%>
					</div>
					<span id="lv_e_font">운동
					<span id="lv_eng_font"> Lv.</span>
					<%=session.getAttribute("e_level")%></span>
				</div>
			</div>
		</div>
		</div>
		
		<div id="con-mid">
			<h2>　</h2>
			<h2>표준체중 : <span class=”progress-label”><%= (double)session.getAttribute("average") %>kg</span></h2>
			<div id=”progressbar”></div>
			<h2>BMI : <span class=”progress-label”><%= (double)session.getAttribute("BMI") %></span></h2>
			<div id=”progressbar”></div>
			<h2>비만도 : <span class=”progress-label”><%= (double)session.getAttribute("obesity") %>%</span></h2>
			<div id=”progressbar”></div>
			<h2>기초대사량 : <span class=”progress-label”><%= (double)session.getAttribute("BMR") %>cal</span></h2>
			<div id=”progressbar”></div>
		</div>
		
		<div id="my-menu">
			<input class="button-con-exp" type="button" value="경험치입력" onClick="input_exp();" />
			<input class="button-con-exp" type="button" value="  통계정보  " onClick="location.href='#'"  />
		</div>
		
		
		
	</div>
</div>
</body>
</html>