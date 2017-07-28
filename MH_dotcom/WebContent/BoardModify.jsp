<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/board.css"/>

<script type="text/javascript">

function sendlt(){
	f = document.write;
	
	str = f.subject.value;
	str = str.trim();
	
		if (!str){
			alert("\n제목을 입력하세요!");
			f.subject.focus();
			return;
		}
	
	str = f.name.value;
	str = str.trim();
	
		if (!str){
			alert("\n작성자를 입력하세요!");
			f.name.focus();
			return;
		}
	
	str = f.content.value;
	str = str.trim();
	
		if (!str){
			alert("\n내용을 입력하세요!");
			f.content.focus();
			return;
		}
		
	if (confirm("정말로 등록할까요?")){
		f.submit();
	}
}
</script>

<title>Modifing..</title>
</head>

<% 
	board_VO b_obj = (board_VO)request.getAttribute("b_obj");
	
	if (b_obj == null){
		response.sendRedirect("Fail.html");
	}
%>

<body>
	<header>
   		<span><a href="index.jsp"><img src="img/Tahiti_banner.png" width="300px" height="120px"></a></span>
    	<span id=copyright>Copyright © Air Tahiti Nui 2016</span>
	</header>
    
	<%@include file="menu.jsp"%>
	<br>
	<br>
	<br>
	
	<form name="write" method="post" action="board_modify_comp.do?num=<%=b_obj.getNum() %>">
	<table width="800" cellpadding="5" align="center" border="1">
		<tr>
			<td width="200" class="num" align="center"><b>번       호</b></td>
			<td width="600"><%=b_obj.getNum() %></td>
		</tr>
		<tr>
			<td width="200" class="subject" align="center"><b>제       목</b></td>
			<td width="600"><input type="text" size="60" name="subject" value='<%=b_obj.getSubject() %>'>
			&nbsp;&nbsp;
			<span>공지여부 <input type="checkbox" name="notice" value=1 <%if(b_obj.getNotice() == 1){%>checked="checked"<%} %>></span>
			</td>
		</tr>
		<tr>
			<td width="200" class="name" align="center"><b>작  성  자</b></td>
			<td width="600"><input type="text" size="20" name="name" value='<%=b_obj.getName() %>'></td>
		</tr>
		<tr>
			<td width="200" class="date" align="center"><b>작  성  일</b></td>
			<td width="600"><%=b_obj.getDate() %></td>
		</tr>
		<tr>
			<td width="50" class="num" align="center"><b>내 용</b></td>
			<td><textarea rows="25" cols="75" name="content"><%=b_obj.getContent() %></textarea></td>
		</tr>
	</table>
		<br>
		<div id="bbs_footer" align="center">
			<input type="button" value=" 수정하기 " class="btn2" onclick="sendlt()"/>
			<input type="reset" value=" 다시입력 " class="btn2" onclick="document.write.subject.focus();"/>
			<input type="reset" value=" 뒤로가기 " class="btn2" onclick="history.back();"/>
			<input type="button" value=" 목록으로 " class="btn2" onclick="location.href='board_list.do'"/>
		</div>
	</form>
</body>
</html>