<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Emerald-Tahiti</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/intro.css" type="text/css" />
    <script type="text/javascript" src="jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        
        /* << 인트로창 처음 열릴시 js >> */
		window.onload = function main() {
            $("#m2").css("background-color", "cornflowerblue");
		}
    </script>
    
    <style type="text/css">

        
    </style>
</head>

<body>
    <header>
        <span><a href="index.jsp"><img src="img/Tahiti_banner.png" width="300px" height="120px"></a></span>
        <span id=copyright>Copyright © Air Tahiti Nui 2016</span>
    </header>
    
   	<%@include file="menu.jsp"%>
    
    <section>
        <p class="p_title"> Welcome To Emerald Paradise!</p>
        
        <div style="text-align: center;">
        <iframe class="avi" src="https://www.youtube.com/embed/fk0_59dvblQ?rel=0&amp;controls=0&amp;showinfo=0&autoplay=1&loop=1" frameborder="0" allowfullscreen></iframe>
        </div>
    </section>
    
    <article>
        <div><img src="img/where.PNG" width="300px"></div>
        <div>꿈에서만 보았던 에메랄드빛 낙원, 타히티를 사랑하고 여행하기를 꿈꾸는 사람들의 모임입니다!</div>
   </article>
</body>

</html>