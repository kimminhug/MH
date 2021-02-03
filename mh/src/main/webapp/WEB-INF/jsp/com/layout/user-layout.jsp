<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta content="IE=edge" http-equiv="X-UA-Compatible">
		<meta content="width=device-width,initial-scale=1" name="viewport">
		<meta content="description" name="description">
		<meta name="google" content="notranslate" />
		<meta content="Mashup templates have been developped by Orson.io team" name="author">
		
		<!-- Disable tap highlight on IE -->
		<meta name="msapplication-tap-highlight" content="no">
		
		<link href="/assets/apple-touch-icon.png" rel="apple-touch-icon">
		<link href="/assets/favicon.ico" rel="icon">
		<link href="/css/main.css" rel="stylesheet">
		<link href="/css/style.css" rel="stylesheet">
		<link rel="stylesheet" href="/fonts/icomoon/style.css">
    	<link rel="stylesheet" href="/css/owl.carousel.min.css">
		
		<script type="text/javascript" src="/js/main.js"></script>
		<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    	<script type="text/javascript" src="/js/popper.min.js"></script>
		
		<title>MH net</title>  
		
	</head>
	
	<body>
		<tiles:insertAttribute name="top"/>
		<tiles:insertAttribute name="user_content"/>
		<tiles:insertAttribute name="tail"/>
		
		<%-- <div class="wrap">
			<tiles:insertAttribute name="top"/>
			<div class="container">
					<div class="main_frame_inner">
						<div style="clear: right;">
							<tiles:insertAttribute name="user_content"/>
						</div>
					</div>
				<div class="tail_frame">
					<tiles:insertAttribute name="tail"/>
				</div>
			</div>
		</div> --%>
	</body>
</html>