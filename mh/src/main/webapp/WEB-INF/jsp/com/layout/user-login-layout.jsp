<!DOCTYPE >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>   
<jsp:directive.include file="/WEB-INF/jsp/flash21/include/compage.jsp" />
<html>
<head>
	<title>한중대영공업(주) 대영베어링 TYB</title>
    <base href="./">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="naver-site-verification" content="b3865f6f3233c89abb7497f04df066520a0581a3" />
	<meta name="description" content="경남 합천에 위치한 진인성 회장이 설립한 한중대영공업(주)은 베어링(대영베어링 TYB), 기어, 동력전달장치를 50년동안 생산 공급하고 있습니다">
	<meta property="og:type" content="website"> 
	<meta property="og:title" content="한중대영공업(주) 대영베어링 TYB">
	<meta property="og:description" content="경남 합천에 위치한 진인성 회장이 설립한 한중대영공업(주)은 베어링(대영베어링 TYB), 기어, 동력전달장치를 50년동안 생산 공급하고 있습니다">
	<meta property="og:image" content="http://www.tyb.kr/images/user/main/logo2.png">
	<meta property="og:url" content="http://www.tyb.kr/">
    
    <link href="<c:url value='/css/user/layout/top_banner.css'/>" rel="stylesheet" type="text/css">
	<link href="<c:url value='/css/user/layout/top.css'/>" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/user/layout/tail.css'/>" rel="stylesheet" type="text/css">
	
	<tiles:insertAttribute name="user_common_head"/>
</head>
<body style="margin: 0px;">
	<tiles:insertAttribute name="content"/>
</body>
</html>



