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
<link rel="stylesheet" href="commong/common.css" type="text/css"/>
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<link rel="stylesheet" href="css/BoardDelete.css" type="text/css"/>

<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript">
	function sendlt(){
		f = document.myForm;
		str = f.pwd.value;
		str = str.trim();
		
			if (!str){
				alert("\n패스워드를 입력하셈!");
				f.pwd.focus();
				return;
			}
		f.pwd.value = str;
		f.action = "delete_ok.do";
		f.submit();
	}
</script>

<title>MH.com 자유게시판</title>
</head>

<body>
	<div id="bbs">
		<div id="bbs_title">Powerful MH.com Board</div>
		
		<form name="myForm" method="post" action="">
		
		<!--  글번호 재전송을 위한 hidden 태그 작성 -->
		<input type="hidden" name="num" value="${num}">
		
		<div id="bbsCreated">
			<input type="button" value=" 삭제하기 " class="btn2" onclick="sendlt();"/>
			<input type="reset" value=" 다시입력 " class="btn2" onclick="document.myForm.pwd.focus();"/>
			<input type="button" value=" 삭제취소 " class="btn2" onclick="location.href='content.do?num=${num}'"/>

		</div>
		</form>
</body>
</html>