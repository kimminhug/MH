<%@ page import="com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.springframework.web.servlet.i18n.SessionLocaleResolver" %>
<%@ page import="java.util.Locale" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%
	HttpSession sess = request.getSession();
	Map<String, Object> logInfo = (Map<String, Object>)sess.getAttribute("loginInfo");
	//Locale currentLocale = (Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
	//String aaa = SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;
%>
<c:set value="<%=logInfo %>" var="logInfo" />
<c:set value='${pageContext.request.contextPath}' var="contextPath" />

<%-- <c:set value="${sess }" var="sess" />
<c:set value="${currentLocale }" var="locale" />
<c:set value="${aaa }" var="aaa" /> --%>