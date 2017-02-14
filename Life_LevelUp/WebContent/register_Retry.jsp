<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 만들어야될 것 : 세션 리스너 - 만료되었을 시 다른창 띄우기 -->

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="LevelUp.css?ver=1" media="screen and (min-width: 1px) and (max-width: 1024px)" rel="stylesheet">
	<meta name="viewport" content="width=device-width">
<script src="https://code.jquery.com/jquery-2.1.0.min.js"></script>
<title>가입 정보 확인</title>
</head>
<body>

<form class="login" name="register_Retry" action="register_Retry.kim" method="post">
	<div class="card">
		<div class="card-header">
			<h3>ID : <%= (String)session.getAttribute("ID") %></h3>
			<h3>닉네임 : <%= (String)session.getAttribute("name") %><br></h3>
			<br>
		</div>
		<div id="con-mid">
			<h2>표준체중 : <%= (double)session.getAttribute("average") %><br></h2>
			<h2>BMI : <%= (double)session.getAttribute("BMI") %><br></h2>
			<h2>비만도 : <%= (double)session.getAttribute("obesity") %>%<br></h2>
			<h2>기초대사량 : <%= (double)session.getAttribute("BMR") %>cal<br></h2>
			<br>
			<h2>당신의 신체레벨 : <span id="lv_num_font"><%=(int)session.getAttribute("b_level") %></span><br></h2>
			<br>
			<div class="submit">
			<input class="button" type="button" value=" < 재입력 " onClick='history.back()'>&nbsp;
			<input class="button" type="submit" value=" 계속 > "/>
			</div>
		</div>
	</div>
</form>

</body>
</html>