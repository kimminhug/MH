<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<% if ((String)session.getAttribute("ID") == null){%>
<div class="card-rank">
    	<div class="card-header-rank">
			<h2>내 순위</h2>
		</div>
	<div id="con-mid">
		<div id="left-box">
			<div id="rank-line-avarta">??위</div>
			<div id="rank-line-avarta"><img id="avarta-rank" src="image/avarta.jpg"></div>
		</div>
    	<div id="center-box">
           	<div id="rank-line-name">Lv.?? 내 닉네임</div>
           	<div id="rank-line-word">등록한 내 한마디</div></div>
        <div id="right-box">
           	<div id="rank-line-score">??↑</div>
        </div>			
	</div>
</div>	
<%}else{ %>
<div class="card-rank">
    	<div class="card-header-rank">
			<h2>내 순위</h2>
		</div>
	<div id="con-mid">
		<div id="left-box">
			<div id="rank-line-avarta">1위</div>
			<div id="rank-line-avarta"><img id="avarta-rank" src="image/avarta.jpg"></div>
		</div>
    	<div id="center-box">
           	<div id="rank-line-name">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("name")%></div>
           	<div id="rank-line-word"><%=session.getAttribute("intro")%></div></div>
        <div id="right-box">
           	<div id="rank-line-score"><%=session.getAttribute("level")%>↑</div>
        </div>			
	</div>
</div>	
<%} %>