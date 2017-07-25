<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Emerald Tahiti 자유게시판</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/board.css"/>

</head>

<body>
	<header>
   		<span><a href="index.jsp"><img src="img/Tahiti_banner.png" width="300px" height="120px"></a></span>
    	<span id=copyright>Copyright © Air Tahiti Nui 2016</span>
	</header>
    
	<%@include file="menu.jsp"%>

<div id="bbs_List" align="center">
<br>
<br>
<br>
	<div id="bbs_title" align="center">Free board</div>
	<br>
	
	<div id="bbs_table">
	<table width="800" cellpadding="5" align="center" border="1">
		<tr>
			<td width="20" class="num" align="center"><b>번호</b></td>
			<td width="250" class="subject" align="center"><b>제            목</b></td>
			<td width="100" class="name" align="center"><b>작  성  자</b></td>
			<td width="30" class="date" align="center"><b>작  성  일</b></td>
		</tr>
	
	<% 
	ArrayList<board_DTO> b_list = (ArrayList<board_DTO>)application.getAttribute("board_List");
	
	for(board_DTO b_obj : b_list) {
	%>
		 <tr>
	 		<%-- 출력 항목은 해당 클래스의 멤버변수명과 통일! --%>
	 		<td class="num" align="center"><%=b_obj.getNum() %></td>
	 		<td class="subject"><a id="bbs_subject" href="board_content.do?num=<%=b_obj.getNum()%>"><%=b_obj.getSubject() %></a></td>
	 		<td class="name" align="center"><%=b_obj.getName() %></td>
	 		<td class="date" align="center"><%=b_obj.getDate() %></td>
	 	</tr>
	<% 
	}
	%>
	
	</table>
		<!--  페이징 기능 추가 -->
	<br>
	<div id="left">
		<input type="button" value="글 작성" onclick="location.href='board_creat.do'"/>
	</div>
	<div id="center">
		<a id="bbs_pager" href="#">[1]</a>
		<a id="bbs_pager" href="#">[2]</a>
	</div>
	</div>
</div>

</body>
</html>