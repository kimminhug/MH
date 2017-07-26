<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/board.css"/>

<script>
	function Delete(num) {
		if (confirm("정말로 삭제하시겠습니까?")){
			location.href="board_delete.do?num="+num;
		}
	}
</script>
<title>Content</title>
</head>
<body>
	<header>
   		<span><a href="index.jsp"><img src="img/Tahiti_banner.png" width="300px" height="120px"></a></span>
    	<span id=copyright>Copyright © Air Tahiti Nui 2016</span>
	</header>
    
	<%@include file="menu.jsp"%>
	<br>
	<br>
	<br>
	<% 
	board_VO b_obj = (board_VO)request.getAttribute("b_obj");
	
	if (b_obj == null){
		response.sendRedirect("Fail.html");
	}
	%>

	<div id="bbs_content">
	<table bgcolor="beige" width="800" cellpadding="5" align="center" border="1">
		<tr>
			<td width="200" class="num" align="center"><b>번       호</b></td>
			<td width="600"><%=b_obj.getNum() %></td>
		</tr>
		<tr>
			<td width="200" class="subject" align="center"><b>제       목</b></td>
			<td width="600"><%=b_obj.getSubject() %></td>
		</tr>
		<tr>
			<td width="200" class="name" align="center"><b>작  성  자</b></td>
			<td width="600"><%=b_obj.getName() %></td>
		</tr>
		<tr>
			<td width="200" class="date" align="center"><b>작  성  일</b></td>
			<td width="600"><%=b_obj.getDate() %></td>
		</tr>
		
		<tr>
			<td width="50" class="num" align="center"><b>내 용</b></td>
			<td width="750" height="500"><%=b_obj.getContent() %></td>
		</tr>
	</table>
	</div>
	<br>
	<div id="bbs_footer" align="center">
		<input type="reset" value=" 글 수정" class="btn2" onclick="location.href='board_modify.do?num=<%=b_obj.getNum()%>'"/>
		<input type="button" value=" 글 삭제 " class="btn2" onclick="Delete(<%=b_obj.getNum()%>)"/>
		<input type="button" value=" 목록으로 " class="btn2" onclick="location.href='board_list.do'"/>
	</div>

</body>
</html>