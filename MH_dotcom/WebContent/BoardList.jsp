<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Emerald Tahiti 간단게시판</title>
<link rel="stylesheet" href="css/common.css" type="text/css"/>
<link rel="stylesheet" href="css/board.css" type="text/css"/>

</head>

<body>
<div id="bbsList">
	<div id="center">
		<!-- 주소 지정시 서블릿 주소로 작성할 것. -->
		<input type="button" value="글 작성" class="btn2" onclick="creat.do"/>
	</div>
	<br>
	<div id="bbsList_list">
	<table bgcolor="beige" cellpadding="5" align="center" border="1">
		<tr>
			<td class="num"><b>번호</b></td>
			<td class="subject"><b>제     목</b></td>
			<td class="name"><b>작성자</b></td>
			<td class="created"><b>작성일</b></td>
		</tr>
	
		 <% %>
		 <tr>
	 		<%-- 출력 항목은 해당 클래스의 멤버변수명과 통일! --%>
	 		<td class="num">1</td>
	 		<td class="subject"><a href="content.do?num=1">안녕하세요</a></td>
	 		<td class="name">김민혁</td>
	 		<td class="created">2017-07-24</td>
	 	</tr>
		 	
	</table>
		<!--  페이징 기능 추가 -->
		<br>
		<div id="center">
			<p>
				<a href="#">[1]</a>
				<a href="#">[2]</a>
			</p>
		</div>
	</div>
</div>

</body>
</html>