
/* <모바일 메뉴바 열고닫는 J쿼리 >*/
jQuery(document).ready(function () {
	//Open the menu
	jQuery("#user-menu").click(function () {
		var b_exp = jQuery('#left-label').find('strong').text() * 0.01;
		var e_exp = jQuery('#right-label').find('strong').text() * 0.01;
		
		jQuery('#content').css('min-height', jQuery(window).height());
		jQuery('#content').css('overflow', 'hidden');
		jQuery('#content').css('position', 'absolute');
		
		jQuery('#left-nav').css('opacity', 1);
		
		//set the width of primary content container -> content should not scale while animating
		//var contentWidth = jQuery('#content').width();

		//set the content with the width that it has originally
		jQuery('#content').css('width', '100%');

		//display a layer to disable clicking and scrolling on the content while menu is shown
		jQuery('#contentLayer').css('display', 'block');

		//disable all scrolling on mobile devices while menu is shown
		jQuery('#container').bind('touchmove', function (e) {
			e.preventDefault();
		});

		//set margin for the whole container with a jquery UI animation
		jQuery("#container").animate({"marginLeft": ["85%", 'easeOutExpo']}, {
			duration: 700
		});
		
		/* Jquery - 원형 그래프 */
		jQuery('.left-circle').circleProgress({
			value: b_exp,
		    startAngle: 300,
		    size: 60,       
		    fill: {
		    gradient: ["red", "orange"]    
		    }
		  }).on('circle-animation-progress', function(event, progress) {
			  jQuery(this).find('strong').html();
		});
		
		jQuery('.right-circle').circleProgress({
			value: e_exp,
		    startAngle: 300,
		    size: 60,       
		    fill: {
		    gradient: ["blue", "dodgerblue"]    
		    }
		  }).on('circle-animation-progress', function(event, progress) {
			  jQuery(this).find('strong').html();
		});
	});

	//close the menu
	jQuery("#contentLayer").click(function () {
		
		//enable all scrolling on mobile devices when menu is closed
		jQuery('#container').unbind('touchmove');

		//set margin for the whole container back to original state with a jquery UI animation
		jQuery("#container").animate({"marginLeft": ["-1", 'easeOutExpo']}, {
			duration: 700,
			complete: function () {
				jQuery('#content').css('position', 'static');
				jQuery('#content').css('overflow','hidden');
				jQuery('#content').css('width', 'auto');
				jQuery('#contentLayer').css('display', 'none');
				jQuery('#left-nav').css('opacity', 0);
				jQuery('#content').css('min-height', 'auto');
			}
		});
	});
	
	
	
});	

/* < 마이페이지 원형그래프 실행 > */
jQuery("#my-circle").ready(function () {
	var b_exp = jQuery('#my-left-label').find('strong').text() * 0.01;
	var b_rate = Math.round(b_exp * 100);
	var e_exp = jQuery('#my-right-label').find('strong').text() * 0.01;
	
	jQuery('.my-left-circle').circleProgress({
		value: b_exp,
	    startAngle: 300,
	    size: 60,       
	    fill: {
	    gradient: ["red", "orange"]    
	    }
	  }).on('circle-animation-progress', function(event, progress) {
		  jQuery(this).find('strong').html();
	});
	
	jQuery('.my-right-circle').circleProgress({
		value: e_exp,
	    startAngle: 300,
	    size: 60,       
	    fill: {
	    gradient: ["blue", "dodgerblue"]    
	    }
	  }).on('circle-animation-progress', function(event, progress) {
		  jQuery(this).find('strong').html();
	});
	
});

/* ---------- 로그아웃  -----------*/
function logout(){
	var con = confirm("로그아웃 하시겠습니까?");

	if(con == true){
		location.href="logout.jsp";
	}
}

