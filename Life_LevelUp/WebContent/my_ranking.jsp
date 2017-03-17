<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.util.ArrayList, com.beans.*"%>

<% 
	ArrayList<Ranking> list = (ArrayList<Ranking>) application.getAttribute("rank_list");
	int count = 0;
	
	if (session.getAttribute("ID") != null){
		
		for(Ranking rank : list) {
			count++;
			if (rank.getID().equals(session.getAttribute("ID"))){
%>	
<div class="card-rank">
    	<div class="card-header-rank-my">
			<h2>내 랭킹</h2>
		</div>
	<div id="con-mid">
		<div id="left-box">
			<div id="rank-line-avarta"><%=count %>위</div>
			<div id="rank-line-avarta"><img id="avarta-rank" src="image/avarta.jpg"></div>
		</div>
    	<div id="center-box">
           	<div id="rank-line-name">Lv.<%=rank.getLevel()%> <%=rank.getName()%></div>
           	<div id="rank-line-word"><%=rank.getIntro()%></div></div>
        <div id="right-box">
           	<div id="rank-line-score"><%=rank.getLevel()%>↑</div>
        </div>			
	</div>
</div>	
<% 
			}
		}
	} 
%>