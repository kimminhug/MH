<!DOCTYPE >
<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
//******************************MS excel******************************
    // MS excel로 다운로드/실행, filename에 저장될 파일명을 적어준다.
    response.setHeader("Content-Disposition","attachment;filename=member.xls");
    response.setHeader("Content-Description", "JSP Generated Data");
%>   
<jsp:directive.include file="/WEB-INF/jsp/flash21/include/compage.jsp" />
<html>
<head>
	<title>ICG2021 관리자</title>
    <base href="./">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta name="description" content="CoreUI - Open Source Bootstrap Admin Template">
    <meta name="author" content="Łukasz Holeczek">
    <meta name="keyword" content="Bootstrap,Admin,Template,Open,Source,jQuery,CSS,HTML,RWD,Dashboard">
    <!-- Icons-->
    <link href="node_modules/flag-icon-css/css/flag-icon.min.css" rel="stylesheet">
    <link href="${contextPath}/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${contextPath}/node_modules/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">
    <!-- Main styles for this application-->
    <link href="${contextPath}/css/style.css" rel="stylesheet">
    <link href="${contextPath}/vendors/pace-progress/css/pace.min.css" rel="stylesheet">
    <link href="${contextPath}/vendors/datatables-plugins/dataTables.bootstrap4.min.css" rel="stylesheet"> 
	
	<tiles:insertAttribute name="excel_common_head"/>
</head>
<body class="app flex-row align-items-center">
	<div class="app-body">
		<tiles:insertAttribute name="content"/>
	</div>
</body>
</html>



