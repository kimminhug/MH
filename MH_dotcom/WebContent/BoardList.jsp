<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 코어 사용을 위한 taglib 디렉티브 지정 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MH.com 자유게시판</title>
<link rel="stylesheet" href="common/common.css" type="text/css"/>
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<link rel="stylesheet" href="css/BoardList.css" type="text/css"/>
</head>

<body>
<div id="bbsList">
	<div align="right">
		<from name="searchForm" method="post" action="">
		<select name="searchKey" class="selectFiled">
			<option value="subject">제목</option>
			<option value="name">작성자</option>
			<option value="content">내용</option>
		</select>
		
		<input type="text" name="searchValue" class="textFiled"/>
		<input type="button" value="검색" class="btn2" onclick=""/>
		</from>
	</div>
	<div id="rightHeader">
		<!-- 주소 지정시 서블릿 주소로 작성할 것. -->
		<input type="button" value="글 작성" class="btn2" onclick=""/>
	</div>
	<div id="bbsList_list">
		<div id="title">
			<dl>
				<dt class="num"><b>번호</b></dt>
				<dt class="subject"><b>제     목</b></dt>
				<dt class="name"><b>작성자</b></dt>
				<dt class="created"><b>작성일</b></dt>
				<dt class="hitCount"><b>조회수</b></dt>
			</dl>
		</div>
		<div id="lists">
			<!-- [ 출력의 예 ]
			<dl>
				<dt class="num"><b>1</b></dt>
				<dt class="subject"><b>(19)(후방주의)직촬! 엄마 몰래 보세욤</b></dt>
				<dt class="name"><b>파워민혁</b></dt>
				<dt class="created"><b>2016-12-27</b></dt>
				<dt class="hitCount"><b>6437</b></dt>
			</dl>
			 -->
			<%-- 
				<< 데이터 베이스의 자료를 출력 >>
				Core - forEach구문을 이용하여 출력.
				BoardServlet.java에서 넘겨준 데이터(List<BoardDTO>를 수신하고 출력!!
			 --%>
			 <c:forEach var="dto" items="${lists}">
			 	<dl>
			 		<%-- 출력 항목은 해당 클래스의 멤버변수명과 통일! --%>
			 		<dd class="num">${dto.num}</dd>
			 		<dd class="subject"><a href="content.do?num=${dto.num}">${dto.subject}</a>
			 		<dd class="name">${dto.name}</dd>
			 		<dd class="created">${dto.created}</dd>
			 		<dd class="hitCount">${dto.hitcount}</dd>
			 	</dl>
			 </c:forEach>
		</div>
		
		<!--  페이징 기능 추가 -->
		<div id="footer">
			<p>
				<!--  
					<a href="#">[1]</a>
					<a href="#">[2]</a>
				 -->
				${pageIndexList}
			</p>
		</div>
	</div>
</div>

</body>
</html>