<%@page import="org.springmodules.validation.util.condition.collection.IsEmptyCollectionCondition"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:directive.include file="/WEB-INF/jsp/com/include/compage.jsp" />

<header>
  <nav class="navbar  navbar-fixed-top navbar-default">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle uarr collapsed" data-toggle="collapse" data-target="#navbar-collapse-uarr">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/" title="">
          <!-- <img src="/assets/images/mashuptemplate.svg" class="navbar-logo-img" alt=""> -->
          <text style="font-family: 'Rajdhani', sans-serif; font-size: 60px;">MH</text>
        </a>
      </div>

      <div class="collapse navbar-collapse" id="navbar-collapse-uarr">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="/" title="" class="active">Home</a></li>
          <li><a href="/intro.do" title=""> About</a></li>
          <!-- <li><a href="/components.html" title="">Components</a></li> -->
          <li><a href="/contact.do" title="">Contact</a></li>
          <li>
            <c:choose>
	            <c:when test="${logInfo ne null}">
		            <p><span class="userinfo">${logInfo.orgmem_name }</span> <a href="/logouttry.do" class="btn btn-primary navbar-btn" title="">Log out</a></p>
	            </c:when>
	            <c:otherwise>
		            <p><a href="/login.do" class="btn btn-primary navbar-btn" title="">Log in</a></p>
	            </c:otherwise>
            </c:choose>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</header>