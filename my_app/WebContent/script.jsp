<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 스크립트</title>
</head>
<body>

<h1>Script Example</h1>

<%! 
	String declaration = "Declaration"; 
%>
<%! 
	public String decMethod(){
		return declaration;
	}
%>
<%
	String scriptlet = "Scriptlet";
	String comment = "Comment";
	out.println("내장객체를 이용한 출력 : "+declaration+"<p>");
%>

선언문의 출력1 : <%=declaration %><br >
선언문의 출력2 : <%=decMethod() %><br>
스크립트릿의 출력 : <%=scriptlet %>

<!-- JSP 주석부분 -->
<!-- JSP 주석1 : <%=comment%> --><p>
<%-- JSP 주석2 : <%=comment%> --%>
<% /* 주석
		(여러줄 주석)
		*/
%>
<%// 주석(한줄 주석) %>

</body>
</html>