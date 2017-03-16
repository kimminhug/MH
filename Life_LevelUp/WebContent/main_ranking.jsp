<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.util.ArrayList, com.beans.*"%>

<% 
	ArrayList<Ranking> list = (ArrayList<Ranking>) application.getAttribute("rank_list");
		
	if (list != null){
		Ranking rank = list.get(0);
%>
<a href="ranking.kim">
<div class="card-rank">
    <div class="card-header-rank">
		<h2>이달의 우수레벨</h2>
	</div>
	<div id="con-mid">
		<div id="left-box">
			<div id="rank-line-avarta">1위</div>
			<div id="rank-line-avarta"><img id="avarta-rank" src="image/avarta.jpg"></div>
		</div>
    	<div id="center-box">
           	<div id="rank-line-name">Lv.<%= rank.getLevel() %> <%= rank.getName() %></div>
           	<div id="rank-line-word"><%= rank.getIntro() %></div></div>
        <div id="right-box">
            <div id="rank-line-score"><%= rank.getLevel() %>↑</div>
        </div>			
	</div>
</div>
</a>
<% 
	}else{
		System.out.println("jsp : 리스트가 널입니다!!!");
	}	
%>
