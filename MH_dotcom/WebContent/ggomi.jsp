<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=100%,user-scalable=yes,initial-scale=1.0; maximum-scale=1.0,minimum-scale=1.0" />
	
	<title>꼼이꼼이</title>
	<script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/popup.css">
	
	<script  type="text/javascript">
		var step="1st";
		
		$(document).ready(function(){
			$('#goClose').click(function(){
				window.close();
			});
		});
		
	    function fn_close(){
	    	window.close();
	    }
	    
		function numberWithCommas(cnt){
	    	var formatNumber = cnt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	    	return formatNumber;
	    }
		
		function fn_next(sel_step){
			var req_url = "/bizInfo_new/bizInfo_new.tp?method=ajaxChatData";
			//var objParam = fn_setParameter();
			var objParam = {
					chat_step : step,
					chat_category_cd1 : $("#selAnswer1").val() == undefined ? "" : $("#selAnswer1").val(),
					chat_category_cd2 : $("#selAnswer2").val() == undefined ? "" : $("#selAnswer2").val(),
					chat_category_cd3 : $("#selAnswer3").val() == undefined ? "" : $("#selAnswer3").val(),
					chat_category_cd4 : $("#selAnswer4").val() == undefined ? "" : $("#selAnswer4").val()
		    	};
			
			fn_resultSetting(sel_step);
			
			/* $.ajax({
			    url : req_url,
			    async: false,
			    type : "POST",
			    data : objParam,
			    success : function(data){
			    	//console.log(data);
			    	var result = eval("("+data+")");
	            	fn_resultSetting(result, sel_step);
	            	//alert("ajax 성공!");
	            	//alert("result_code :"+data.chat_category_cd2+", result_name : "+data.chat_category_nm2);
	            	
			    }, error:function(request, status, error) {
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }
			}); */
		}
		
		function fn_resultSetting(sel_step){
			var innner_html = "";
			var chat_category_nm1 = $("#selAnswer1 option:selected").text();
			var chat_category_nm2 = $("#selAnswer2 option:selected").text();
			var chat_category_nm3 = $("#selAnswer3 option:selected").text();
			var chat_category_nm4 = $("#selAnswer4 option:selected").text();
			
			if(sel_step == "1"){
				innner_html = '1단계로 <strong>'+chat_category_nm1+'</strong> 선택결과,<br/> 현재 신청가능한 사업은 총 <strong class="cRed">'+result.chat_totalCnt+'</strong>건입니다.';
				innner_html += '<a href="#" tabindex="13" onclick="javascript:fn_goDetail(); return false;">☞ 사업목록 보기</a><br/>2단계로 지원분야를 선택해주세요.';
				
				$("#typing02").html(innner_html);
				$("#q02").show();
				$("#a02").show();
				$("#close2").show();
				$("#selAnswer2").focus();
				
			}if(sel_step == "2"){
				innner_html = '2단계로 '+chat_category_nm1+' &gt; <strong>'+chat_category_nm2+'</strong> 선택결과,<br/> 현재 신청가능한 사업은 총 <strong class="cRed">'+result.chat_totalCnt+'</strong>건입니다.' 
				innner_html += '<a href="#" tabindex="13" onclick="javascript:fn_goDetail(); return false;">☞ 사업목록 보기</a><br> 3단계로 소관지자체를 선택해주세요.'
				
				$("#typing03").html(innner_html);
				$("#q03").show();
				$("#a03").show();
				$("#close2").hide();
				$("#close3").show();
				$("#selAnswer3").focus();
				
			}if(sel_step == "3"){
				innner_html = '3단계로 '+chat_category_nm1+' &gt; '+chat_category_nm2+' &gt; <strong>'+chat_category_nm3+'</strong> 선택결과,<br/> 현재 신청가능한 사업은 총 <strong class="cRed">'+result.chat_totalCnt+'</strong>건입니다.' 
				innner_html += '<a href="#" tabindex="13" onclick="javascript:fn_goDetail(); return false;">☞ 사업목록 보기</a><br> 4단계로 업종을 선택해주세요.'
				
				$("#typing04").html(innner_html);
				$("#q04").show();
				$("#a04").show();
				$("#close3").hide();
				$("#close4").show();
				$("#selAnswer4").focus();
			
			}if(sel_step == "4"){
				innner_html = '4단계로 '+chat_category_nm1+' &gt; '+chat_category_nm2+' &gt; '+chat_category_nm3+' &gt; <strong>'+chat_category_nm4+'</strong> 선택결과,<br/> 현재 신청가능한 사업은 총 <strong class="cRed">'+result.chat_totalCnt+'</strong>건입니다.' 
				innner_html += '<a href="#" tabindex="13" onclick="javascript:fn_goDetail(); return false;">☞ 사업목록 보기</a>'
				
				$("#typing05").html(innner_html);
				$("#q05").show();
				$("#close4").hide();
				$("#q05").focus();
			}
		}
		
		function fn_prev(sel_step){
			$("#q0"+sel_step).hide();
			$("#a0"+sel_step).hide();
			
			$("#close"+(sel_step-1)).show();
			
			if (sel_step < 5){
				$("#close"+sel_step).hide();
			}
		}
		
		function fn_goDetail(){
			var d_url = "/bizInfo_new/bizInfo_new.tp?method=bizInfoNewList&topMenuNum=6&subMenuNum=1";
			
			if($("#selAnswer1").val() != ""){
				d_url += "&entrprsstleCode="+$("#selAnswer1").val();
			}
			if($("#selAnswer2").val() != ""){
				d_url += "&sportrearmCode="+$("#selAnswer2").val();
			}
			if($("#selAnswer3").val() != ""){
				d_url += "&locgovCode="+$("#selAnswer3").val();
			}
			if($("#selAnswer4").val() != ""){
				d_url += "&indutyCode="+$("#selAnswer4").val();
			}
			
			opener.window.location.href = d_url;
			opener.window.focus();
		}
	</script>
	
	
	<link rel="stylesheet" href="/css/layout.css" type="text/css"  charset="utf-8">
	<link rel="stylesheet" href="/css/popup.css?param=1" type="text/css">
	<style type="text/css">
		@import url("/css/style.css");
	</style>
</head>
	
<body>
	<div class="talkDtail-bg02" style="margin-top: 0px;">
		<p class="talkDtail-title01">
			<span>꼼이의 대화방</span>
		</p>
		<div class="talkDtail-txt01">
			<p class="t01" style="font-size: 15px;">안녕하세요. 공주님! 꼼이의 대화방에 오신것을 환영해요!</p>
			<p class="t02"></p>
		</div>
		<div class="talkCont-bg" id="QAList">
			<ul style="list-style:none;">
				<li class="clearfix">
					<div class="tkCont-all">
						<div class="tk-why01">
							<p class="tk-wIkon01">
								<a href="#" tabindex="3"><img src="img/popup/ggomi.jpg" width="75px" height="75px" alt="답변자"></a>
							</p>
							<div class="tk-why-bg01">
								<div class="tk-why-bg02">
									<p id="typing01">어서오세요 공주님 저는 아련한 꼼이에요<br> 오늘하루도 엄청엄청 고생하셨어요!<br> 오늘은 평소보다 많이힘드셨나요?</p>
								</div>
							</div>
						</div>
					</div>
				</li>

				<li class="clearfix" style="" id="a01">
					<div class="tk-ansAll-area clearfix">
						<div class="tk-ansAll">
							<div class="tk-ans01">
								<p class="tk-aIkon01">
									<a href="#" tabindex="5"><img src="img/popup/tk_aikon01.png" alt="질문자"></a>
									<img src="img/popup/tm_bg04.gif" alt="" class="talk-icon">
								</p>
								<div class="tk-ansUl-area clearfix">
									<ul class="tk-ansUl">
										<li>
											<span>
												<select name="selAnswer1" id="selAnswer1" title="꼼이 선택" style="width:100%;" tabindex="4" onchange="fn_next(1);">
													<option value="">미선택</option>
					                    			<option value="1">괜찮은 날이었어</option>
					                    			<option value="2">평소하고 똑같아</option>
					                    			<option value="3">너무 힘들었어</option>
					                    			<option value="4">살려줘</option>
												</select>
											</span>
										</li>
									</ul>
									
								</div>
							</div>
						</div>
					</div>
				</li>

				<li class="clearfix" style="display:none;" id="q02">
					<div class="tkCont-all">
						<div class="tk-why01">
							<p class="tk-wIkon01">
								<a href="#" tabindex="6"><img src="img/popup/chat_logo.jpg" width="75px" height="75px" alt="답변자"></a>
							</p>
							<div class="tk-why-bg01">
								<div class="tk-why-bg02">
									<p id="typing02"></p>
								</div>
							</div>
						</div>
					</div>
				</li>

				<li class="clearfix" style="display:none;" id="a02">
					<div class="tk-ansAll-area clearfix">
						<div class="tk-ansAll">
							<div class="tk-ans01">
								<p class="tk-aIkon01">
									<a href="#" tabindex="11"><img src="img/popup/tk_aikon01.png" alt="질문자"></a>
									<img src="img/popup/tm_bg04.gif" alt="" class="talk-icon">
								</p>
								<div class="tk-ansUl-area clearfix">
									<ul class="tk-ansUl">
										<li>
											<span>
												<select name="selAnswer2" id="selAnswer2" title="분야 선택" style="width:100%;" tabindex="9" onchange="fn_next(2);">
													<option value="">미선택</option>
													<option value="01">금융</option>
													<option value="02">기술</option>
													<option value="03">인력</option>
													<option value="04">수출</option>
													<option value="05">내수</option>
													<option value="06">창업</option>
													<option value="07">경영</option>
													<option value="08">제도</option>
													<option value="09">동반성장</option>
												</select>
											</span>
										</li>
									</ul>
									<a href="#" tabindex="8" id="close2" class="tkclose" onclick="javascript:fn_prev(2); return false;"><img src="img/popup/tk_aikon_close.gif" alt="닫기"></a>
								</div>
							</div>
						</div>
					</div>
				</li>

				<li class="clearfix" style="display:none;" id="q03">
					<div class="tkCont-all">
						<div class="tk-why01">
							<p class="tk-wIkon01">
								<a href="#" tabindex="12"><img src="img/popup/chat_logo.jpg" width="75px" height="75px" alt="답변자"></a>
							</p>
							<div class="tk-why-bg01">
								<div class="tk-why-bg02">
									<p id="typing03"></p>
								</div>
							</div>
						</div>
					</div>
				</li>

				<li class="clearfix" style="display:none;" id="a03">
					<div class="tk-ansAll-area clearfix">
						<div class="tk-ansAll">
							<div class="tk-ans01">
								<p class="tk-aIkon01">
									<a href="#" tabindex="16"><img src="img/popup/tk_aikon01.png" alt="질문자"></a>
									<img src="img/popup/tm_bg04.gif" alt="" class="talk-icon">
								</p>
								<div class="tk-ansUl-area clearfix">
									<ul class="tk-ansUl">
										<li>
											<span>
												<select name="selAnswer3" id="selAnswer3" title="지역 선택" style="width:100%;" tabindex="15" onchange="fn_next(3);">
													<option value="">미선택</option>
													<option value="all">전체</option>
													<option value="1100000000">서울</option>
													<option value="2600000000">부산</option>
													<option value="2700000000">대구</option>
													<option value="2800000000">인천</option>
													<option value="2900000000">광주</option>
													<option value="3000000000">대전</option>
													<option value="3100000000">울산</option>
													<option value="3611000000">세종</option>
													<option value="4100000000">경기</option>
													<option value="4200000000">강원</option>
													<option value="4300000000">충북</option>
													<option value="4400000000">충남</option>
													<option value="4500000000">전북</option>
													<option value="4600000000">전남</option>
													<option value="4700000000">경북</option>
													<option value="4800000000">경남</option>
													<option value="5000000000">제주</option>
												</select>
											</span>
										</li>
									</ul>
									<a href="#" tabindex="14" id="close3" class="tkclose" onclick="javascript:fn_prev(3); return false;"><img src="img/popup/tk_aikon_close.gif" alt="닫기"></a>
								</div>
							</div>
						</div>
					</div>
				</li>

				<li class="clearfix" style="display:none;" id="q04">
					<div class="tkCont-all">
						<div class="tk-why01">
							<p class="tk-wIkon01">
								<a href="#" tabindex="17"><img src="img/popup/chat_logo.jpg" width="75px" height="75px" alt="답변자"></a>
							</p>
							<div class="tk-why-bg01">
								<div class="tk-why-bg02">
									<p id="typing04"></p>
								</div>
							</div>
						</div>
					</div>
				</li>
				
				<li class="clearfix" style="display:none;" id="a04">
					<div class="tk-ansAll-area clearfix">
						<div class="tk-ansAll">
							<div class="tk-ans01">
								<p class="tk-aIkon01">
									<a href="#" tabindex="22"><img src="img/popup/tk_aikon01.png" alt="질문자"></a>
									<img src="img/popup/tm_bg04.gif" alt="" class="talk-icon">
								</p>
								<div class="tk-ansUl-area clearfix">
									<ul class="tk-ansUl">
										<li>
											<span>
												<select name="selAnswer4" id="selAnswer4" title="업종 선택" style="width:100%;" tabindex="20" onchange="fn_next(4);">
													<option value="">미선택</option>
													<option value="A">농업, 임업 및 어업(01~03)</option>
													<option value="B">광업(05~08)</option>
													<option value="C">제조업(10~34)</option>
													<option value="D">전기, 가스, 증기 및 공기 조절 공급업(35)</option>
													<option value="E">수도, 하수 및 폐기물 처리, 원료 재생업(36~39)</option>
													<option value="F">건설업(41~42)</option>
													<option value="G">도매 및 소매업(45~47)</option>
													<option value="H">운수 및 창고업(49~52)</option>
													<option value="I">숙박 및 음식점업(55~56)</option>
													<option value="J">정보통신업(58~63)</option>
													<option value="K">금융 및 보험업(64~66)</option>
													<option value="L">부동산업(68)</option>
													<option value="M">전문, 과학 및 기술 서비스업(70~73)</option>
													<option value="N">사업시설 관리, 사업 지원 및 임대 서비스업(74~76)</option>
													<option value="O">공공 행정, 국방 및 사회보장 행정(84)</option>
													<option value="P">교육 서비스업(85)</option>
													<option value="Q">보건업 및 사회복지 서비스업(86~87)</option>
													<option value="R">예술, 스포츠 및 여가관련 서비스업(90~91)</option>
													<option value="S">협회 및 단체, 수리 및 기타 개인 서비스업(94~96)</option>
													<option value="T">가구 내 고용활동 및 달리 분류되지 않은 자가 소비 생산활동(97~98)</option>
													<option value="U">국제 및 외국기관(99)</option>
												</select>
											</span>
										</li>
									</ul>
									<a href="#" tabindex="19" id="close4" class="tkclose" onclick="javascript:fn_prev(4); return false;"><img src="img/popup/tk_aikon_close.gif" alt="닫기"></a>
								</div>
							</div>
						</div>
					</div>
				</li>

				<li class="clearfix" style="display:none;" id="q05">
					<div class="tkCont-all" style="padding-bottom: 20px;">
						<div class="tk-why01">
							<p class="tk-wIkon01">
								<a href="#" tabindex="23"><img src="img/popup/chat_logo.jpg" width="75px" height="75px" alt="답변자"></a>
							</p>
							<div class="tk-why-bg01">
								<div class="tk-why-bg02">
									<p id="typing05"></p>
								</div>
							</div>
						</div>
					</div>
				</li>

			</ul>
		</div>
		<div class="tk-aBtn01">
			<a href="#" tabindex="25" id="btn_prev" onclick="javascript:fn_prev(); return false;"><span>이전</span></a>
		</div>
	</div>
</body>
</html>