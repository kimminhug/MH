<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cmn" uri="http://www.flash21.com/tags/cmn"%>

<style type="text/css">
<!--
.multiFileArea{border:2px solid #de6da6; min-height:30px; padding:5px;}
.singleFileArea{border:2px solid #6da06d; min-height:30px; padding:5px;}
-->
</style>
<script type="text/javascript">
	var	freeformSearchUrl = "<c:url value='/admin/biz/bizInfoAjax.li' />";
	var	freeformSaveUrl = "<c:url value='/admin/biz/bizInfoSaveAjax.li' />";
	var status;
	var idx = '${req.idx}';

	$(function(){
		//var maxFileNum = 10;
		//var filter = 'jpg|jpeg|gif|bmp|png|txt|doc|docx|hwp|pdf|zip|xls|xlsx|ppt|pptx';
		var filter = '';
		
		//use jQuery MultiFile Plugin 
		$('#freeForm input[type=file].multifile').MultiFile({
			 accept: filter //허용할 확장자(지정하지 않으면 모든 확장자 허용)
			, maxfile: 1024*10 //각 파일 최대 업로드 크기 (Byte)
			, maxsize: 3024*10  //전체 파일 최대 업로드 크기 (Byte)
			, preview : true
			, STRING: { //Multi-lingual support : 메시지 수정 가능
			    remove : "<i class='fa fa-trash' alt='파일제거' title='파일제거'></i>", //추가한 파일 제거 문구, 이미태그를 사용하면 이미지사용가능
			    duplicate : "$file 은 이미 선택된 파일입니다.", 
			    denied : "$ext 는(은) 업로드 할수 없는 파일확장자입니다.",
			    selected:'$file 을 선택했습니다.', 
			    toomuch: "업로드할 수 있는 최대크기를 초과하였습니다.($size)", 
			    toomany: "업로드할 수 있는 최대 갯수는 $max개 입니다.",
			    toobig: "$file 은 크기가 매우 큽니다. (max $size)"
			}
			//list:"#afile-list1" //파일목록을 출력할 요소 지정가능
			//, list:console.log(this)
		});
		
		$("input[kind=phone]").on("keyup", function(){
			autoHypen(this);
		});
		
		if(idx != ''){
			fnFreeformSearch(freeformSearchUrl, "#freeSearchForm", "#freeForm", fnSearchCallBack, "", true);
		}
	});
	
	//프리폼 조회 콜백
	function fnSearchCallBack(){
		status = $("#freeForm #STATUS").val();
		if(status == 'U'){
			$("#freeForm #usid").attr("readonly", "readonly").addClass("ctl_select_disabled");
		}
	}
	
	//저장시 반드시 해당 function 명(fnDialogSave) 사용할것!! 
	function fnDialogSave(frmNm){
		status = $("#freeForm #STATUS").val();
		if(status == 'C'){
			var file1 = $('#freeForm input[name=file_1]').val();
		}
		fnFreeformSave(freeformSaveUrl, '#freeForm', fnSaveCallBack, '저장 완료 되었습니다.', true, '입력하신 내용을 저장하시겠습니까?');
	}
	
	//저장 콜백함수
	function fnSaveCallBack(data){
		dialog.dialog('close');
		fnBtSearch();
	}
</script>

<form name="freeSearchForm" id="freeSearchForm" method="post">
	<input type="hidden" name="idx" value="${req.idx }" />
</form>

<form name="freeForm" id="freeForm" method="post" enctype="multipart/form-data">
	<jsp:directive.include file="/WEB-INF/jsp/include/comFreeformElement.jsp"/>
	<input type="hidden" name="idx" id="idx" value="${req.idx }" />
	<input type="hidden" name="biz_type" id="biz_type" value="COM015.01" />	<!-- 모바일앱 -->
	
	<div>
		<label for="biz_nm">앱이름</label>
		<input type="text" name="biz_nm" id="biz_nm" value="" alt="앱이름" title="앱이름" notnull="true" /> 
	</div>
	<div>
		<label for="biz_cn">앱설명</label>
		<textarea name="biz_cn" id="biz_cn" alt="앱설명" title="앱설명" style="width:95%; height:50px;"></textarea>
	</div>
	<div>
		<label>메인페이지(권장해상도 404*564 / jpg|jpeg|gif|bmp|png 파일만 등록가능)</label>
		
		<jsp:include page="/WEB-INF/jsp/include/singlefileupload.jsp">
		    <jsp:param name="fileid" value="1"/>
		    <jsp:param name="notnull" value="Y"/>
		    <jsp:param name="title" value="메인페이지"/>
		    <jsp:param name="filter" value="jpg|jpeg|gif|bmp|png"/>
		</jsp:include>
	</div>
	<div>
		<label>서브페이지(최대 10개 등록가능 / jpg|jpeg|gif|bmp|png 파일만 등록가능)</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="2"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="서브페이지"/>
		    <jsp:param name="filter" value="jpg|jpeg|gif|bmp|png"/>
		</jsp:include>
	</div>
	<div>
		<label for="andro_reg_dt">Android 등록</label>
		<input type="text" class="calendar" format="date" kind="date" name="andro_reg_dt" id="andro_reg_dt" value="" title="Android 등록" alt="Android 등록" maxlength="10" />
	</div>
	<div>
		<label for="andro_reg_id">Android 등록자</label>
		<input type="text" class="" name="andro_reg_id" id="andro_reg_id" value="" title="Android 등록자" alt="Android 등록자" />
	</div>
	<div>
		<label for="andro_reg_dt">iOS 등록</label>
		<input type="text" class="calendar" format="date" kind="date" name="apple_reg_dt" id="apple_reg_dt" value="" title="iOS 등록" alt="iOS 등록" maxlength="10" />
	</div>
	<div>
		<label for="apple_reg_id">iOS 등록자</label>
		<input type="text" class="" name="apple_reg_id" id="apple_reg_id" value="" title="iOS 등록자" alt="iOS 등록자" />
	</div>
	<div>
		<label for="biz_cn">웹노출</label>
		<label class="radio_input">
			<input type="radio" name="dp_yn" id="dp_ynY" alt="웹노출" title="웹노출" value="Y" checked="checked" />
			<span>웹노출</span>
		</label>
		<label class="radio_input">
			<input type="radio" name="dp_yn" id="dp_ynN" alt="비노출" title="비노출" value="N" />
			<span>비노출</span>
		</label>
	</div>
	<div>
		<label for="app_knd">앱종류</label>
		<cmn:rbox idName="app_knd" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM002" />
	</div>
	<div>
		<label for="inflow_cours">유입경로</label>
		<cmn:rbox idName="inflow_cours" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM003" />
	</div>
	<div>
		<label for="inflow_cours_etc">유입경로 상세</label>
		<input type="text" class="" name="inflow_cours_etc" id="inflow_cours_etc" value="" title="유입경로 상세" alt="유입경로 상세" />
	</div>
	<div>
		<label for="inqry_dt">문의날짜</label>
		<input type="text" class="calendar" format="date" kind="date" name="inqry_dt" id="inqry_dt" value="" title="문의날짜" alt="문의날짜" maxlength="10" />
	</div>
	<div>
		<label for="charger_nm">담당자명</label>
		<input type="text" class="" name="charger_nm" id="charger_nm" value="" title="담당자" alt="담당자" maxlength="50" />
	</div>
	<div>
		<label for="charger_rsp">담당자직책</label>
		<input type="text" class="" name="charger_rsp" id="charger_rsp" value="" title="담당자직책" alt="담당자직책" maxlength="50" />
	</div>
	<div>
		<label for="charger_hp">담당자휴대폰</label>
		<input type="text" kind="phone" class="" name="charger_hp" id="charger_hp" value="" title="담당자휴대폰" alt="담당자휴대폰" maxlength="13" />
	</div>
	<div>
		<label for="charger_email">담당자이메일</label>
		<input type="text" kind="email" class="" name="charger_email" id="charger_email" value="" title="담당자이메일" alt="담당자이메일" />
	</div>
	<div>
		<label for="mail_attc">메일첨부파일</label>
		<cmn:cbox idName="mail_attc" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM008" />
	</div>
	<div>
		<label for="mail_attc_etc">메일첨부파일상세</label>
		<input type="text" class="" name="mail_attc_etc" id="mail_attc_etc" value="" title="메일첨부파일상세" alt="메일첨부파일상세" />
	</div>
	<div>
		<label for="adit_charger">추가담당자</label>
		<input type="text" class="" name="adit_charger" id="adit_charger" value="" title="추가담당자" alt="추가담당자" />
	</div>
	<div>
		<label for="paper_nmst">종이회원명부</label>
		<cmn:cbox idName="paper_nmst" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM016" />
		<input type="text" class="" name="paper_nmst_etc" id="paper_nmst_etc" value="" title="종이회원명부" alt="종이회원명부" />
	</div>
	<div>
		<label for="tot_mem_cnt">총회원수</label>
		<cmn:rbox idName="tot_mem_cnt" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM009" sortCol="order_num" />
	</div>
	<div>
		<label>비고</label>
		<textarea name="remk1" id="remk1" alt="비고" title="비고" style="width:95%; height:50px;"></textarea> 
	</div>
	
	<hr style="border:1px solid #333; margin:7px 0;" />
	
	<div>
		<label for="app_stus">앱구축과정</label>
		<cmn:rbox idName="app_stus" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM004" sortCol="order_num" />
	</div>
	<div>
		<label for="app_from_dt">앱구축기간</label>
		<input type="text" class="from_calendar" format="date" kind="date" name="app_from_dt" id="app_from_dt" value="" title="앱구축 시작일자" alt="앱구축 시작일자" maxlength="10" /> ~ 
		<input type="text" class="to_calendar" format="date" kind="date" name="app_to_dt" id="app_to_dt" value="" title="앱구축 종료일자" alt="앱구축 종료일자" maxlength="10" />
	</div>
	<div>
		<label for="app_devlpr">개발자</label>
		<input type="text" class="" name="app_devlpr" id="app_devlpr" value="" title="개발자" alt="개발자" maxlength="50" />
	</div>
	<div>
		<label for="app_designer">디자이너</label>
		<input type="text" class="" name="app_designer" id="app_designer" value="" title="디자이너" alt="디자이너" maxlength="50" />
	</div>
	<div>
		<label for="app_adm_url">관리자URL</label>
		<input type="text" class="" name="app_adm_url" id="app_adm_url" value="" title="관리자URL" alt="관리자URL" maxlength="200" />
	</div>
	<div>
		<label for="app_adm_pwd">관리자PW</label>
		<input type="text" class="" name="app_adm_pwd" id="app_adm_pwd" value="" title="관리자PW" alt="관리자PW" maxlength="200" />
	</div>
	<div>
		<label for="app_mobile_url">모바일앱URL</label>
		<input type="text" class="" name="app_mobile_url" id="app_mobile_url" value="" title="모바일앱URL" alt="모바일앱URL" maxlength="200" />
	</div>
	<div>
		<label for="relat_homepage">관련홈페이지</label>
		<input type="text" class="" name="relat_homepage" id="relat_homepage" value="" title="관련홈페이지" alt="관련홈페이지" maxlength="200" />
	</div>
	<div>
		<label for="dflt_menu">기본메뉴</label>
		<cmn:cbox idName="dflt_menu" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM010" />
	</div>
	<div>
		<label for="adit_func">추가기능</label>
		<cmn:cbox idName="adit_func" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM011" />
	</div>
	<div>
		<label>비고</label>
		<textarea name="remk2" id="remk2" alt="비고" title="비고" style="width:95%; height:50px;"></textarea> 
	</div>
	<div>
		<label>앱등록 리젝사유</label>
		<textarea name="app_rjct_resn" id="app_rjct_resn" alt="앱등록 리젝사유" title="앱등록 리젝사유" style="width:95%; height:50px;"></textarea> 
	</div>
	
	<div>
		<label>제안서</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="3"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="제안서"/>
		</jsp:include>
	</div>
	<div>
		<label>견적서</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="4"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="견적서"/>
		</jsp:include>
	</div>
	<div>
		<label>타견적서1</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="5"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="타견적서1"/>
		</jsp:include>
	</div>
	<div>
		<label>타견적서2</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="6"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="타견적서2"/>
		</jsp:include>
	</div>
	<div>
		<label>스토리보드</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="7"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="스토리보드"/>
		</jsp:include>
	</div>
	<div>
		<label>계약서</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="8"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="계약서"/>
		</jsp:include>
	</div>
	<div>
		<label>완료내역서</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="9"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="완료내역서"/>
		</jsp:include>
	</div>
	<div>
		<label>완료계청구서</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="10"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="완료계청구서"/>
		</jsp:include>
	</div>
	<div>
		<label>산출내역서</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="11"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="산출내역서"/>
		</jsp:include>
	</div>
	<div>
		<label>사용자매뉴얼</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="12"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="사용자매뉴얼"/>
		</jsp:include>
	</div>
	<div>
		<label>고객로고</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="13"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="고객로고"/>
		</jsp:include>
	</div>
	<div>
		<label>사업자사본</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="14"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="사업자사본"/>
		</jsp:include>
	</div>
	<div>
		<label>통장사본</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="15"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="통장사본"/>
		</jsp:include>
	</div>
	<div>
		<label>세금계산서</label>
		<jsp:include page="/WEB-INF/jsp/include/multifileupload.jsp">
		    <jsp:param name="fileid" value="16"/>
		    <jsp:param name="maxfilenum" value="10"/>
		    <jsp:param name="title" value="세금계산서"/>
		</jsp:include>
	</div>
	<div>
		<label for="cnst_bb">구축비통장</label>
		<cmn:rbox idName="cnst_bb" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM006" addAttr="title='구축비통장' alt='구축비통장'" />
		<input type="text" class="" name="cnst_bb_etc" id="cnst_bb_etc" value="" title="구축비통장상세" alt="구축비통장상세" />
	</div>
	<div>
		<label for="cnst_tax">구축비금액</label>
		<cmn:rbox idName="cnst_tax" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM012" addAttr="title='구축비부가세' alt='구축비부가세'" />
		<input type="text" class="" name="cnst_amt" id="cnst_amt" value="" title="구축비금액상세" alt="구축비금액상세" />
	</div>
	<div>
		<label for="cnst_dt">구축비날짜</label>
		<input type="text" class="calendar" format="date" kind="date" name="cnst_dt" id="cnst_dt" value="" title="구축비날짜" alt="구축비날짜" maxlength="10" />
	</div>
	<div>
		<label for="mntn_bb">유지비통장</label>
		<cmn:rbox idName="mntn_bb" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM006" addAttr="title='유지비통장' alt='유지비통장'" />
		<input type="text" class="" name="mntn_bb_etc" id="mntn_bb_etc" value="" title="유지비통장상세" alt="유지비통장상세" />
	</div>
	<div>
		<label for="mntn_tax">유지비금액</label>
		<cmn:rbox idName="mntn_tax" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM012" addAttr="title='유지비부가세' alt='유지비부가세'" /> <br />
		<cmn:rbox idName="mntn_paymt" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM013" addAttr="title='유지비납부구분' alt='유지비납부구분'" />
		<input type="text" class="" name="mntn_amt" id="mntn_amt" value="" title="구축비금액상세" alt="구축비금액상세" />
	</div>
	<div>
		<label for="mntn_dt">유지비날짜</label>
		<input type="text" class="calendar" format="date" kind="date" name="mntn_dt" id="mntn_dt" value="" title="유지비날짜" alt="유지비날짜" maxlength="10" /> <br />
		<cmn:rbox idName="mntn_dedt" tableNm="vw_comtccmmncode" whereCol1="code_id" codeCol="code" nameCol="code_nm" whereVal1="COM014" addAttr="title='유지비납기' alt='유지비납기'" />
	</div>
	
</form>