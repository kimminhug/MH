<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>   

<html>
<head>
	<tiles:insertAttribute name="admin_common_head"/>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<tiles:insertAttribute name="top"/>
		<tiles:insertAttribute name="nav"/>
		<tiles:insertAttribute name="admin_content"/>
	</div>
	<tiles:insertAttribute name="tail"/>
</body>
</html>