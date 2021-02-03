<!DOCTYPE >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>   
<jsp:directive.include file="/WEB-INF/jsp/flash21/include/compage.jsp" />
<html>
<head>
	<title>ICG2021</title>
    <base href="./">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="CoreUI - Open Source Bootstrap Admin Template">
    <meta name="author" content="Łukasz Holeczek">
    <meta name="keyword" content="Bootstrap,Admin,Template,Open,Source,jQuery,CSS,HTML,RWD,Dashboard">
    <link href="<c:url value='${contextPath}/css/user/basic.css'/>" rel="stylesheet" type="text/css" >
	<tiles:insertAttribute name="user_common_head"/>
</head>

<body class="app flex-row align-items-center">
	<tiles:insertAttribute name="content"/>
</body>
</html>



