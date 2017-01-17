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
<link rel="stylesheet" href="css/BoardInsert.css" type="text/css"/>

<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript">
	function sendlt(){
f = document.myForm;
		
		str = f.subject.value;
		str = str.trim();
		
			if (!str){
				alert("\n제목을 입력하셈!");
				f.subject.focus();
				return;
			}
		f.subject.value = str;
		
		str = f.name.value;
		str = str.trim();
		
			if (!str){
				alert("\n작성자를 입력하셈!");
				f.name.focus();
				return;
			}
		f.name.value = str;
		
		str = f.content.value;
		str = str.trim();
		
			if (!str){
				alert("\n내용을 입력하셈!");
				f.content.focus();
				return;
			}
		f.content.value = str;
		
		str = f.pwd.value;
		str = str.trim();
		
			if (!str){
				alert("\n패스워드를 입력하셈!");
				f.pwd.focus();
				return;
			}
		f.pwd.value = str;
		
		f.action = "update_ok.do";
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
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt>제&nbsp;&nbsp;&nbsp;&nbsp;목</dt>
					<dd>
						<input type="text" name="subject" size="74" maxlength="100"
								class="boxTF" value="${dto.subject}" />
						</dd>
					</dl>
				</div>
				<div class="bbsCreated_bottomLine">
					<dl>
						<dt>작성자</dt>
						<dd>
							<input type="text" name="name" size="35" maxlength="20"
								class="boxTF" value="${dto.name}"/>
						</dd>
					</dl>
				</div>
				<div class="bbsCreated_content">
					<dl>
						<dt>내&nbsp;&nbsp;&nbsp;&nbsp;용</dt>
						<dd>
							<textarea name="content" cols="63" rows="12" class="boxTF" />
							${dto.content}</textarea>
						</dd>
					</dl>
				</div>
				<div class="bbsCreated_noLine">
					<dl>
						<dt>패스워드</dt>
						<dd>
							<input type="password" name="pwd" size="35" maxlength="7"
								class="boxTF" />&nbsp;(게시물 수정 및 삭제시 필요!)
						</dd>
					</dl>
				</div>
		
		<div id="bbsCreated_footer">
			<input type="button" value=" 수정하기 " class="btn2" onclick="sendlt();"/>
			<input type="reset" value=" 다시입력 " class="btn2" onclick="document.myForm.subject.focus();"/>
			<input type="button" value=" 수정취소 " class="btn2" onclick="location.href='content.do?num=${dto.num}'"/>

		</div>
		</form>
</body>
</html>