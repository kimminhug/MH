<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	
	<title>경험치 입력</title>
</head>
<body>
<div id="container">
	<%@include file="menu.jsp"%>

    <div id="contentLayer"></div> <%-- 여기서부터 메뉴바깥 '내용' 부분 --%>
    
    <div id="content">
    	<form name="exp_input" action="exp_input.kim" method="post">
		<div class="card">
			<div class="card-header">
				<h2>신체</h2>
			</div>
				<div class="input-wrap">
					<span class="label">현재 몸무게(kg)</span>
					<input class="input" type="text" name="weight" placeholder="숫자로만 입력" />
				</div>
		</div>
		<div class="card">
			<div class="card-header">
				<h2>운동</h2>
			</div>
			<div class="input-wrap">
				<span class="label">소모 칼로리(cal)</span>
				<input class="input" type="text" name="e_exp" placeholder="숫자로만 입력" />
			</div>
		</div>
		<div class="submit">
			<input class="button" type="submit" value=" 획득하기 " />
		</div>
		</form>
    </div>
</div>
</body>
</html>