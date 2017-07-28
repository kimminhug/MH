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
	
	if (b_obj.getNotice() == 1){
	%>
	<div id="bbs_title" align="center">Notice</div>
	<%} %>
	<br>
	<div id="bbs_content">
	<table width="800" cellpadding="5" align="center">
		<% if(b_obj.getNotice() == 0){ %>
		<tr>
			<td width="200" class="num" align="center"><b>번       호</b></td>
			<td width="600"><%=b_obj.getNum() %></td>
		</tr>
		<%} %>
		<tr>
			<td width="200" class="subject" align="center"><b>제       목</b></td>
			<td><%=b_obj.getSubject() %></td>
		</tr>
		<tr>
			<td class="name" align="center"><b>작  성  자</b></td>
			<td><%=b_obj.getName() %></td>
		</tr>
		<tr>
			<td class="date" align="center"><b>작  성  일</b></td>
			<td><%=b_obj.getDate() %></td>
		</tr>
		
		<tr>
			<td class="content" align="center"><b>내 용</b></td>
			<td height="300"><%=b_obj.getContent() %></td>
		</tr>
	</table>
	<br>
	<table id="prv_next" width="800" cellpadding="5" align="center">
		<% 	if ((int)request.getAttribute("prevNum") != -1){ %>
		<tr>
			<td width="150" id="bbs_prev">＜ Prev</a></td>	
			<td width="650"><a id="bbs_subject" href="board_content.do?num=<%=(int)request.getAttribute("prevNum")%>">
				&nbsp;<%=request.getAttribute("prevSubject") %>
			</a></td>
		</tr>	
		<% } 
			if ((int)request.getAttribute("nextNum") != -1){%>
		<tr>
			<td width="150" id="bbs_next">Next ＞</a></td>	
			<td width="550"><a id="bbs_subject" href="board_content.do?num=<%=(int)request.getAttribute("nextNum")%>">
				&nbsp;<%=request.getAttribute("nextSubject") %>
			</a></td>
		</tr>
		<% } %>	
	</table>
	</div>
	<br>
	<div id="bbs_footer" align="center">
		<input type="button" value=" 답글쓰기 " class="btn2" onclick="location.href='board_reply.do?num=<%=b_obj.getNum()%>'"/>
		<input type="reset" value=" 글 수정" class="btn2" onclick="location.href='board_modify.do?num=<%=b_obj.getNum()%>'"/>
		<input type="button" value=" 글 삭제 " class="btn2" onclick="Delete(<%=b_obj.getNum()%>)"/>
		<input type="button" value=" 목록으로 " class="btn2" onclick="location.href='board_list.do'"/>
	</div>

</body>
</html>