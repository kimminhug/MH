<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
    
<html>
<head>
<title>Emerald-Tahiti</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/jquery-3.1.1.min.js" ></script>
<script type="text/javascript" src="js/index.js" ></script>
</head>

<body>
    <header>
        <span><a href="index.jsp"><img src="img/Tahiti_banner.png" width="300px" height="120px"></a></span>
        <span id=copyright>Copyright © Air Tahiti Nui 2016</span>
    </header>
    
    <%@include file="menu.jsp"%>
    
    <section>
    
    <div class="present">
    <%
    	String upload_Path = request.getSession().getServletContext().getRealPath("/") + "img/banner/";		
		String[] file_List = new File(upload_Path).list();
		// 파일 이름, 갯수에 상관없이 배너폴더 내부의 이미지 리스트를 모두 뿌려줌.
		
		for(int i=0; i<file_List.length; i++){
	%>
		<img class="slide" src="img/banner/<%=file_List[i]%>" alt=" 배너 이미지" width="100%" height="500px">
	<%
		}
	%>
    </div>
    </section>
    
    <article>
    <div class="loginForm">
        <form method="post" name="" action="">
            <div class="box">
                <input type="text" class="iText" value="ID" onFocus="clearText(this)"><br>
                <input type="password" class="iText" value="Pass" onFocus="clearText(this)"><br>
                <p>
                <span class="fleft"><a id="add_button" href="">Resist Emerald</a></span> 
                <span class="fright1"><a id="add_button" href="">Find ID</a></span>
                <span class="fright2"><a id="add_button" href="">Find Pass</a></span>
                </p>
            </div>
            <a href="#" id="" class="loginBtn">Login</a>
        </form>
   </div>
   
   <div class="news">
		<div id="rollText" style="position:relative; top:0;">
			<div><a class="news_a" href="http://www.mt.co.kr/view/mtview.php?type=1&no=2016090617461145780&outlink=1">
                2016/09/07 허니문리조트, 신혼여행박람회 개최…타히티 허니문시대 개막</a></div>
			<div><a class="news_a" href="http://www.travelnbike.com/news/articleView.html?idxno=24895">
                2016/08/25 ‘하와이냐 몰디브냐' 고민되면 ‘타히티’로 </a></div>
			<div><a class="news_a" href="http://www.nocutnews.co.kr/news/4619452">
                2016/07/07 타히티 숙소 제대로 고르는 비법 </a></div>
			<div><a class="news_a" href="http://mbn.mk.co.kr/pages/news/newsView.php?category=mbn00003&news_seq_no=2929825">
                2016/06/27 타히티 관광청, 온라인 캠페인 런칭</a></div>
            <div><a class="news_a" href="http://www.outdoornews.co.kr/news/articleView.html?idxno=21888">
                2016/05/18 프랑스령 폴리네시아의 심장... 타히티</a></div>
            <div><a class="news_a" href="http://www.outdoornews.co.kr/news/articleView.html?idxno=21859">
                2016/05/11 무엇을 상상하든 그 이상... 자연의 선물, 타히티</a></div>
            <div><a class="news_a" href="http://economyplus.chosun.com/special/special_view_past.php?boardName=C22&t_num=9498&img_ho=">
                2016/04/10 고갱의 눈에 비친 원시의 신비 명품 해변 ‘타히티’</a></div>
			<div><a class="news_a" href="http://www.nocutnews.co.kr/news/4561935">
                2016/03/14 신비의 섬 '타히티' 누구보다 빨리 가기</a></div>
		</div>
	</div>
        
    <span class="sponsored-by"><img src="img/sponsored-by.png" width="140px" height="60px"></span>    
    <span class="sponsor"><a href="https://www.airtahitinui.com/kr-ko/%ED%83%80%ED%9E%88%ED%8B%B0-%EC%84%AC" target="contents">
    <img src="img/sponsor.png" width="300px" height="120px"></a></span>
       
   </article>
</body>

</html>