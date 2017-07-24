<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/common.css" type="text/css"/>

<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript">
	function sendlt(){
		f = document.myForm;
		
		str = f.subject.value;
		str = str.trim();
		
			if (!str){
				alert("\n제목을 입력하세요!");
				f.subject.focus();
				return;
			}
		f.subject.value = str;
		
		str = f.name.value;
		str = str.trim();
		
			if (!str){
				alert("\n작성자를 입력하세요!");
				f.name.focus();
				return;
			}
		f.name.value = str;
		
		str = f.content.value;
		str = str.trim();
		
			if (!str){
				alert("\n내용을 입력하세요!");
				f.content.focus();
				return;
			}
		f.content.value = str;
	}
</script>

<title>글쓰기</title>
</head>

<body>
	<div id="bbs_title" >글쓰기</div>

	<form name="myForm" method="post" action="">
		<div id="bbs_footer">
			<input type="button" value=" 등록하기 " class="btn2" onclick="sendlt()"/>
			<input type="reset" value=" 다시입력 " class="btn2" onclick="document.myForm.subject.focus();"/>
			<input type="button" value=" 삭제취소 " class="btn2" onclick="location.href='list.do'"/>
		</div>
	</form>
</body>
</html>