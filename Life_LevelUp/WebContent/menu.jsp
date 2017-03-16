<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header>
	<% if ((String)session.getAttribute("ID") == null){%>
		<div id="left-box">
			<a id="not-login" href="login.html"><img id="logo-img" src="image/logo.png"></a>
		</div>
		<div id="right-box">
			<a id="not-login" href="login.html"><i id="logo-img" class="material-icons" >&#xE8FE;</i></a>
		</div>
        <div id="center-box">
			<a href="main.kim"><div id="main-banner">Life LevelUp!</div></a>
		</div>
	<%}else{ %>
        <div id="left-box">
        	<div id="user-menu"><img id="logo-img" src="image/logo.png"></div>
        </div>
        <div id="right-box">
			<div id="comu-menu"><i id="logo-img" class="material-icons">&#xE8FE;</i></div>
		</div>
        <div id="center-box">
			<a href="main.kim"><div id="main-banner">Life LevelUp!</div></a>
		</div>
    <%} %>
</header>
    
    <nav id="left-nav">
        <ul>
            <li id="my">
            <div id="my-cha">
            	<a id="left-box" href="mypage.jsp">내 캐릭터</a>
            	<div id="right-box"><input class="button-con-logout" type="button" value="로그아웃" onClick="logout();" /></div>
            </div>
            </li>
            <li id="condition">
            	<div id="con-mid">
            		<div id="con-mid"><img id="avarta" src="image/avarta.jpg"></div>
            		<span id="lv_eng_font">Lv. </span>
            		<span id="lv_num_font"><%=session.getAttribute("level")%></span>
            	</div>
            	<div id="con-mid"><h3><%=session.getAttribute("name")%></h3></div>
            	<div id="cir-warp">
            		<div id="left-box">
            			<span id=left-label><strong><%=session.getAttribute("b_exp")%></strong><i>%</i></span>
						<div class="left-circle"></div>
						<div id="con-mid">
							<div id="lv_exp_font"><%=session.getAttribute("b_exp")%> / 100 </div>
							<span id="lv_b_font">신체 
							<span id="lv_eng_font"> Lv.</span>
							<%=session.getAttribute("b_level")%></span>
						</div>
					</div>
            		<div id="right-box">
						<span id=right-label><strong><%=session.getAttribute("e_rate")%></strong><i>%</i></span>
						<div class="right-circle"></div>
						<div id="con-mid">
							<div id="lv_exp_font">
								<%=session.getAttribute("e_exp")%> / <%=session.getAttribute("e_req_exp")%>
							</div>
							<span id="lv_e_font">운동
							<span id="lv_eng_font"> Lv.</span>
							<%=session.getAttribute("e_level")%></span>
						</div>
					</div>
				</div>
				<div id="con-mid">
					<input class="button-con-exp" type="button" value="경험치획득" onClick="location.href='exp_input.jsp'" />
				</div>
            </li>
            <li><a href="#">친구목록</a></li>
        </ul>
    </nav>
<!--     
    <nav id="right-nav">
        <ul>
            <li><a href="#">게시판1</a></li>
            <li><a href="#">게시판2</a></li>
            <li><a href="#">게시판3</a></li>
        </ul>
    </nav>
-->
	