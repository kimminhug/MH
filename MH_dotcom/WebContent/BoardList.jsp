<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Emerald Tahiti 자유게시판</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/board.css"/>
<script type="text/javascript">
	function goPage(num) {
		location.href = "board_list.do?page="+num;
	}

</script>

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
			<td width="250" class="subject" align="center"><b>제   목</b></td>
			<td width="100" class="name" align="center"><b>작  성  자</b></td> 
			<td width="30" class="date" align="center"><b>작  성  일</b></td>
		</tr>
	
	<% 
	try{
		ArrayList<board_VO> n_list = (ArrayList<board_VO>)application.getAttribute("board_Notice");
		
		for(board_VO b_obj : n_list) {
	
	%>
		 <tr id="notice">
	 		<%-- 출력 항목은 해당 클래스의 멤버변수명과 통일! --%>
	 		<td class="notice" align="center">공지</td>
	 		<td class="subject"><a id="bbs_subject" href="board_content.do?num=<%=b_obj.getNum()%>"><%=b_obj.getSubject() %></a></td>
	 		<td class="name" align="center"><%=b_obj.getName() %></td>
	 		<td class="date" align="center"><%=b_obj.getDate() %></td>
	 	</tr>
	<% 
		}
	}catch(Exception ex){}
	%>
	
	<% 
	try{
		ArrayList<board_VO> b_list = (ArrayList<board_VO>)application.getAttribute("board_List");
	
		for(board_VO b_obj : b_list) {
	%>
		 <tr>
	 		<%-- 출력 항목은 해당 클래스의 멤버변수명과 통일! --%>
	 		<td class="num" align="center"><%=b_obj.getNum() %></td>
	 		<td class="subject">
	 			<a id="bbs_subject" href="board_content.do?num=<%=b_obj.getNum()%>">
	 			<% 	for (int i=0; i<b_obj.getStep(); i++){%>
	 					&nbsp;&nbsp;&nbsp;
	 			<% 	} 
	 			   	if (b_obj.getStep() != 0){%>  
	 			   		<img alt="reply" src="/img/board/reply.png">
	 			<%	} %>
	 				<%=b_obj.getSubject() %>
	 			</a>
	 		</td>
	 		<td class="name" align="center"><%=b_obj.getName() %></td>
	 		<td class="date" align="center"><%=b_obj.getDate() %></td>
	 	</tr>
	<% 
		}
	}catch(Exception ex){}
	%>
	
	</table>
		<!--  페이징 기능 추가 -->
	<br>
	<div id="left">
		<input type="button" value="글 작성" onclick="location.href='board_creat.do'"/>
	</div>
	
	<% paging_VO paging = (paging_VO)application.getAttribute("paging"); %>
	
	<div class="center">
    <a href="javascript:goPage(<%=paging.getFirstPageNo() %>)" class="first" id="bbs_pager">|◀</a>&nbsp;
    <a href="javascript:goPage(<%=paging.getPrevPageNo() %>)" class="prev" id="bbs_pager">◀</a>&nbsp;&nbsp;
    <span>
        <%for (int i=paging.getStartPageNo(); i<=paging.getEndPageNo(); i++){
        	if (i == paging.getPageNo()){
       	%>
        		<a href="javascript:goPage(<%=i %>)" class="choice" id="bbs_pager"><%=i %></a>&nbsp;
        <%	}else{ %>
				<a href="javascript:goPage(<%=i %>)" id="bbs_pager"><%=i %></a>&nbsp;
		<%	
        	}
        }
        %>	
    </span>&nbsp;
    <a href="javascript:goPage(<%=paging.getNextPageNo() %>)" class="next" id="bbs_pager">▶</a>&nbsp;
    <a href="javascript:goPage(<%=paging.getFinalPageNo() %>)" class="last" id="bbs_pager">▶|</a>
	</div>
	
	</div>
</div>

</body>
</html>