<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="<c:url value='/css/user/layout/top_banner.css'/>" rel="stylesheet" type="text/css">
<jsp:directive.include file="/WEB-INF/jsp/flash21/include/compage.jsp" />
<script type="text/javascript">
</script>
<div class="top2_banner_container">
	<!--이달의우수업체 -->
	<c:if test="${'0' eq num}">
		<p id="mainP">이달의 <span style="font-weight: bold;">우수</span>업체</p>
		<p id="subP">청소업체정보</p>
		<div class="btnP">
			<button class="bnbtn" onclick="partnerSub(0)">지역별 업체목록</button>
			<button class="bnbtn" onclick="partnerSub(1)">지역별 우수/일반</button>
		</div>
	</c:if>
	<!--청소대교리뷰 -->
		<c:if test="${'1' eq num}">
		<p id="mainP">청소대교 <span style="font-weight: bold;">리뷰</span></p>
		<p id="subP">청소대교리뷰</p>
		<div class="btnP">
			<button class="bnbtn" onclick="reviewSub(0)">지역별 추천업체 리뷰</button>
			<button class="bnbtn" onclick="reviewSub(1)">고객베스트 리뷰</button>
			<button class="bnbtn" onclick="reviewSub(2)">청소대교 리뷰</button>
		</div>
	</c:if>
	<!--청소대교 영상 -->
		<c:if test="${'2' eq num}">
		<p id="mainP">청소대교 <span style="font-weight: bold;">영상</span></p>
		<p id="subP">청소영상</p>
		<div class="btnP">
			<button class="bnbtn" onclick="videoSub()">영상목록</button>
		</div>
	</c:if>
	<!--공동구매 -->
		<c:if test="${'3' eq num}">
		<p id="mainP">청소대교 <span style="font-weight: bold;">공동구매</span></p>
		<p id="subP">공동구매</p>
		<div class="btnP">
			<button class="bnbtn" onclick="groupSub()">지역별 공구목록</button>
		</div>
	</c:if>
	<!--커뮤니티 -->
		<c:if test="${'4' eq num}">
		<p id="mainP">이달의 <span style="font-weight: bold;">커뮤니티</span></p>
		<p id="subP">커뮤니티</p>
		<div class="btnP">
			<button class="bnbtn" onclick="communitySub()">커뮤니티 메인</button>
		</div>
	</c:if>
	<!--고객센터 -->
		<c:if test="${'5' eq num}">
		<p id="mainP">청소대교 <span style="font-weight: bold;">고객센터</span></p>
		<p id="subP">고객센터</p>
		<div class="btnP">
		</div>
	</c:if>
</div>