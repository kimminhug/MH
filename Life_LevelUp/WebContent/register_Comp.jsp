<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 만들어야될 것 : 뒤로가기 시 정보 중복될경우 페이지 거르기 -->

<html>
<head>
	<link href="LevelUp.css?ver=1" media="screen and (min-width: 1px) and (max-width: 1024px)" rel="stylesheet">
	<meta name="viewport" content="width=device-width">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>축하합니다!</title>
</head>
<body>

<form class="login" name="register_Comp" action="register_Comp.kim" method="post">
	<div class="card">
		<div id="con-mid">
			<img id="avarta-my" src="image/avarta.jpg">
			<h2><%= (String)session.getAttribute("name")%>님의 캐릭터가 생성되었습니다!</h2>
			<br>
			<h2>축하합니다!!</h2>
			<div class="submit">
				<input class="button" type="submit" value=" 홈으로  " />
			</div>
		</div>
	</div>
</form>

</body>
</html>