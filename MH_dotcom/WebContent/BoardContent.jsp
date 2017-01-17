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
<link rel="stylesheet" href="css/BoardContent.css" type="text/css"/>

<c:if test=${!empty sesstionScope.login }">
<script>
	function adminDelete(num) {
		if (confirm("정말로 삭제할까요? 잘 생각해보셈.")){
			location.href="adminDelete.do?num="+num;
		}
	}
</script>
</c:if>
<title>MH.com 자유게시판</title>
</head>

<body>
	<div id="bbs">
		<div id="bbs_title">Powerful MH.com Board</div>
		<div id="bbsArticle">
			<div id="bbsArticle_header">
				<%-- 제목 수신 및 출력 코드 지정 --%>
				${dto.subject}
			</div>
			<div class="bbsArticle_bottomLine">
				<dl>
					<dt>작성자</dt>
					<dd>${dto.name}</dd>
					<dt>줄수</dt>
					<dd>10</dd>
				</dl>
			</div>
			<div class="bbsArticle_bottonLine">
				<dl>
					<dt>등록일</dt>
					<%-- 글쓴 날짜 수신 및 출력 --%>
					<dd>${dto.created}</dd>
					<dt>조회수</dt>
					<%-- 조회수 수신 및 출력 --%>
					<dd>${dto.hitcount}</dd>
				</dl>
			</div>
			<div id="bbsArticle_content">
				<table width="600" border="0">
				<tr>
					<td style="padding: 20px 80px 20px 62px;" valign="top" height="150">
					<%-- 내용 수신 및 출력 --%>
					${dto.content}
					</td>
				</tr>
				</table>
			</div>
			<div class="bbsArticle_bottomLine">
				이전글 : 작업중
			</div>
			<div class="bbsArticle_noLine">
				다음글 : 작업중
			</div>
		</div>
		<div class="bbsArticle_noLine" style="text-align:right">
			From : 127.0.0.1
		</div>
		<div id="bbsArticle_footer">
			<div id="leftFooter">
				<input type="button" value=" 수정 " class="btn2" onclick="location.href='update.do?num=?{dto.num}'"/>
				<c:choose>
				<c:when test="${empty sessionScope.login}">
					<input type="button" value=" 수정 " class="btn2" onclick="location.href='delete.do?num=?{dto.num}'"/>
				</c:when><c:otherwise>
					<input type="button" value=" 수정 " class="btn2" onclick="javascript:adminDelete('$dto.num')"/>
				</c:otherwise>
				</c:choose>
			</div>
			<div id="rightFooter">
				<input type="botton" value=" List " class="btn2" onclick="location.href="list.do'"/>
			</div>
		</div>
	</div>
<br>&nbsp;<br>

</body>
</html>