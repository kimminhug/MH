<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>경험치 업!</title>
</head>
<body>

<% if (session.getAttribute("ID") != null){ %>

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

<% } %>
</body>
</html>