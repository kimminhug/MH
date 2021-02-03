<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	/* ********************** < Ag-Grid make Start > ********************* */
	// [1]그리드객체 생성
	var grid1 = new AgGridObj();

	grid1.gridDiv = "grid1"; // 그리드
	grid1.rowCheckboxYn = true; // 그리드 로우 체크박스사용 여부
	grid1.searchForm = "#searchForm";
	grid1.searchUrl = "<c:url value='/admin/board/boardListAjax.do'/>";
	grid1.saveUrl = "<c:url value='/admin/board/boardSaveAjax.do'/>";
	
	grid1.noPaging = true;
	//grid1.pagination = false; // true(그리드 네비게이션), false(무한 스크롤)
	//grid1.pageSize = 10; // 한페이지에 출력될 row수
	
	//grid1.rowModelType = 'serverSide';
	
	// [2] 그리드 컬럼정의등록
	// 컬럼명, 컬럼변수, 정렬, 컬럼넓이, 수정여부, 숨김여부, 데이터타입(rownum,text,number,datePicker,selectBox, button, textarea), 
	// selectBox인경우 data/button인경우버튼html, maxLength(textBox만 처리), filter(text, number, date), filterParams(json), 
	
	//grid1.pushColumnDef("No.", "RNUM", "center", 30, false, false, "", function(params) { return params.node.rowIndex + 1; }); // row number
	grid1.pushColumnDef("No.", "RNUM", "center", 70, false, false, 'rownum');
	grid1.pushColumnDef("상태", "STATUS", "left", 0, false, true);
	
	grid1.pushColumnDef("매출관리"," ","center",70,false,false, 'button', '<button onClick="fnBtSales(#rowId#);" class="btn btn-default btn-sm"><i class="fa fa-fw fa-won-sign"></i></button>');
	grid1.pushColumnDef("앱구분", "app_knd", "center", 85, false, false, 'html', fnAppDiv);
	grid1.pushColumnDef("앱이름", "biz_nm", "left", 180, false, false, null, null, null, null, 'text');
	var tot_mem_cnt_data = "<cmn:selectdata tableNm='vw_comtccmmncode' codeCol='CODE' nameCol='CODE_NM' whereCol1='CODE_ID' whereVal1='COM009' />";
	grid1.pushColumnDef("총회원수", "tot_mem_cnt", "center", 120, false, false, 'selectBox', tot_mem_cnt_data, null, null);
	var app_stus_data = "<cmn:selectdata tableNm='vw_comtccmmncode' codeCol='CODE' nameCol='CODE_NM' whereCol1='CODE_ID' whereVal1='COM004' />";
	grid1.pushColumnDef("앱구축과정", "app_stus", "center", 120, false, false, 'selectBox', app_stus_data, null, null);
	grid1.pushColumnDef("웹노출", "", "center", 80, false, false, 'html', fnWebDp, null, null);
	grid1.pushColumnDef("구축비", "", "center", 80, false, false, 'html', fnCnstDp, null, null);
	grid1.pushColumnDef("유지비", "", "center", 80, false, false, 'html', fnMntnDp, null, null);
	grid1.pushColumnDef("상세"," ","center",70,false,false, 'button', '<button onClick="fnBtMod(#rowId#);" class="btn btn-default btn-sm"><i class="fa fa-fw fa-search"></i></button>');

/* 	grid1.pushColumnDefGroupStart("사원정보");
		//grid1.pushColumnDef("고객코드", "CCODE", "center", 100, false, false, null, null, null, null, 'text', null, null, true);
	grid1.pushColumnDefGroupEnd();
 */	
	/* grid1.pushColumnDef("설명", "DESCRIPTION", "left", 150, true, false, 'textarea');
	grid1.pushColumnDef("날짜","INPDTTM","center",80,true, true, 'datePicker');
	grid1.pushColumnDef("숫자","TESTAMT","center",80,true, true, 'number', null, 5);
	var test_data = "<cmn:selectdata tableNm='VW_COMTCCMMNCODE' codeCol='CODE' nameCol='CODE_NM' whereCol1='CODE_ID' whereVal1='COM045' />";
	grid1.pushColumnDef("사용여부","USE_YN","left",50,true,false, 'selectBox', test_data);
	grid1.pushColumnDef("사원번호", "EMPNUM", "left", 50, false, true, null, null);
	grid1.pushColumnDef("사원명", "EMPNAME", "left", 50, false, true, null, null);
	grid1.pushColumnDef("사원검색", " ", "center", 30, false, true, 'button', '<button onClick="fnSearchEmpBtn(#rowId#);" class="btn btn-default btn-sm"><i class="fa fa-fw fa-search"></i></button>');
	grid1.pushColumnDef("사업장코드", "CCODE", "left", 50, false, true, null, null);
	grid1.pushColumnDef("상호명", "BUSNAME", "left", 80, false, true, null, null);
	grid1.pushColumnDef("고객검색", " ", "center", 30, false, true, 'button', '<button onClick="fnSearchEntrBtn(#rowId#);" class="btn btn-default btn-sm"><i class="fa fa-fw fa-search"></i></button>');
	grid1.pushColumnDef("장비번호", "EQIDX", "left", 50, false, true, null, null);
	grid1.pushColumnDef("장비명", "EQNAME", "left", 80, false, true, null, null);
	grid1.pushColumnDef("장비검색", " ", "center", 30, false, true, 'button', '<button onClick="fnSearchEquipBtn(#rowId#);" class="btn btn-default btn-sm"><i class="fa fa-fw fa-search"></i></button>'); */
	//grid1.pushColumnDef("사용여부", "USE_YN", "left", 50, true, false, "");


	grid1.gridOpts = grid1.getDefaultGridOpt();
	//grid1.checkMobile(grid1.gridOpts);
	
	//grid1.gridOpts.showToolPanel = true;
	
	//grid1.gridOpts.enableCellChangeFlash = true;
	//grid1.gridOpts.enableCharts = true;
	//grid1.gridOpts.suppressHorizontalScroll = false;
	//grid1.gridOpts.enableCellChangeFlash = true;
	//grid1.gridOpts.rowDragManaged = true;
	grid1.gridOpts.floatingFilter = true;
	//grid1.gridOpts.rowGroupPanelShow = 'always'; // on of ['always','onlyWhenGrouping'], 그리드 상단에 헤더를 드래그로 그룹화
	//grid1.gridOpts.pivotPanelShow = 'always'; // on of ['always','onlyWhenPivoting']
	//grid1.gridOpts.pivotColumnGroupTotals = 'before';
	//grid1.gridOpts.pivotRowTotals = 'before';
	//grid1.gridOpts.enterMovesDownAfterEdit = true;
	//grid1.gridOpts.enterMovesDown = true;
	//grid1.gridOpts.multiSortKey = 'ctrl';
    //grid1.gridOpts.animateRows = true;
    grid1.gridOpts.enableColResize = true; // 컬럼 리사이즈 허용 여부
    grid1.gridOpts.enableSorting = true; // 정렬 옵션 허용 여부
    grid1.gridOpts.enableFilter = true; // 필터 옵션 허용 여부
    grid1.gridOpts.enableStatusBar = true;
    //grid1.gridOpts.enableRangeSelection = true; // 정렬 기능 강제여부, true인 경우 정렬이 고정
    //grid1.gridOpts.rowSelection = "multiple"; // one of ['single','multiple'], leave blank for no selection
    //grid1.gridOpts.rowDeselection = true; // 행 전체선택 해제
    //grid1.gridOpts.quickFilterText = null;
    //grid1.gridOpts.groupSelectsChildren = true; // one of [true, false]
	//grid1.gridOpts.suppressRowClickSelection = true; // 선택 허용여부
	//grid1.gridOpts.showToolPanel = true; //window.innerWidth > 1000, // 패널 활성화 여부
	//
	// [3] 그리드이벤트 등록
	grid1.gridOpts.onRowClicked = function(params) { //row 클릭시 event
		//console.log('grid1.gridOpts.onRowClicked [' + params + ']');
		//var obj = grid1.api;
		//console.clear();
		//console.log(obj);
		
		//$('#searchId', '#searchForm2').val(params.data.ID);
	};

	grid1.gridOpts.onRowDoubleClicked = function(params) { //row 더블클릭시 event
		var rowId = params.data.RNUM;
		/* for(i in params){
			console.log(i+ " params :: "+params[i]);
			
		} */
		fnBtMod(rowId);
	};
	
	grid1.gridOpts.onGridReady = function(params) { //row 클릭시 event
		//console.clear();
		//console.log(grid1.pushColumnDef);
		
		//var obj = grid1.api;
		//for(var a in obj){
		//	console.log(a + " -> " + obj[a]);
		//}
		
		//grid1.api.setColumnDefs(aa); 
	};
	grid1.gridOpts.onGridSizeChanged = function (event) {
	};


	/* ********************** < Ag-Grid make End > **********************/

	$(document).ready(function() {
		
		//조회 버튼
		$('#btnInq').on('click', function(){
			fnBtSearch();
		});
		//신규 버튼
		$('#btnNew').on('click', function(){
			fnBtNew();
		});
		//삭제 버튼
		$('#btnDel').on('click', function(){
			fnBtDelete();
		});
		//저장 버튼
		$('#btnSave').on('click', function(){
			fnBtSave();
		});
		// 그리드 생성
		grid1.agGridInit();

		// 그리드 조회
		//fnBtSearch();
	});
	
	//사원검색버튼
	function fnSearchEmpBtn(rowId) {
		searchEmp('searchForm', rowId);
	}
	
	//그리드1 조회
	function fnBtSearch() {
		fnAgGirdSearch(grid1, '#searchForm', searchCallBack);
	}
	
	function searchCallBack() {
		fnAgGridRowSelected(grid1, grid1.api.getFirstDisplayedRow());
	}
	
	//신규추가 모달
	function fnBtNew(){
		var url = "<c:url value='/admin/biz/bizViewAjax.li'/>";
		var title = "신규추가";
		fnDialogForm(url, "bizModal", title, 1000, 700);
	}
	
	//수정 모달
	function fnBtMod(rowId){
		var rowNode = fnGetRowNode(grid1, rowId);
		var bizNm = rowNode.data.biz_nm
		//alert("usid ::: " + rowNode.data.orgmem_no);
		var data = {"idx":rowNode.data.idx};
		
		var url = "<c:url value='/admin/biz/bizViewAjax.li'/>";
		var title = bizNm;
		fnDialogForm(url, "bizModal", title, 1000, 700, data);
	}
	
	function fnBtSales(rowId){
		
	}
	
	function fnAppDiv(params){
		//var rowNode = fnGetRowNode(grid1, rowId);
		var rowId = params.data.RNUM;
		var andApp = params.data.andro_reg_dt;
		var appleApp = params.data.apple_reg_dt;
		var rtnAnd = '';
		var rtnApp = '';
		
		if(andApp != '' && andApp != undefined){
			rtnAnd = "<img src='${contextPath}/images/ico/android.png' />";
		}else{
			rtnAnd = '';
		}
		if(appleApp != '' && appleApp != undefined){
			rtnApp = "<img src='${contextPath}/images/ico/ios.png' />";
		}else{
			rtnApp = "";
		}
		return rtnAnd + rtnApp;
	}
	
	function fnWebDp(params){
		var dpYn = params.data.dp_yn;
		var rtnVal = '';
		if(dpYn == 'Y' && dpYn != undefined){
			rtnVal = '<i class="fa fa-2x fa-eye" style="color:#142de4;"></i>';
		}else{
			rtnVal = '<i class="fa fa-2x fa-eye-slash" style="color:#9e9e9e;"></i>';
		}
		return rtnVal;
	}
	function fnCnstDp(params){
		var dpYn = params.data.cnst_dt;
		var rtnVal = '';
		if(dpYn != '' && dpYn != undefined){
			rtnVal = '<i class="fa fa-2x fa-smile" style="color:#e414ae;"></i>';
		}else{
			rtnVal = '<i class="fa fa-2x fa-frown" style="color:#9e9e9e;"></i>';
		}
		return rtnVal;
	}
	function fnMntnDp(params){}

	//그리드1 저장
	function fnBtSave() {
		fnAgGirdSave(grid1, grid1SaveAfter);
	}

	//그리드1 삭제
	function fnBtDelete() {
		fnAgGirdDel(grid1, fnBtSearch);
	}

	//그리드1 저장  callBack
	function grid1SaveAfter() {
		fnAgGirdSearch(grid1, '#searchForm');
	}
	/* grid1 function E */

	function onBtAddPopup() {
		var s_url = '<c:url value="/tmx/sampleFreeFormPopup.do" />'; //데이터 URL
		//var status = $("#searchBasiDt").val();
		//var id = $("#searchBasiDt").val();	//grid rowId

		fnOpenPopup("popup_sample", "about:blank", 850, 900);

		$("#popupForm #status").val("C");
		$("#popupForm").attr("target", "popup_sample");
		$("#popupForm").attr("method", "post");
		$("#popupForm").attr("action", s_url);
		$("#popupForm").submit();
	}

	function onBtModPopup(id) {
		var s_url = '<c:url value="/tmx/sampleFreeFormPopup.do" />'; //데이터 URL
		//var status = $("#searchBasiDt").val();
		//var id = $("#searchBasiDt").val();	//grid rowId

		fnOpenPopup("popup_sample", "about:blank", 850, 900);

		$("#popupForm #status").val("U");
		$("#popupForm #id").val(id);
		$("#popupForm").attr("target", "popup_sample");
		$("#popupForm").attr("method", "post");
		$("#popupForm").attr("action", s_url);
		$("#popupForm").submit();
	}
	
	//사용자 검색 콜백
	function searchEmpAfter() {
		var args = arguments;
		var params = args[0];
		
		if(params.rowId == 'noGrid') { //커스텀 태그 검색 버튼에서 호출 콜백
			$('#searchEmpNum').val(params.EMPNUM);
			$('#searchEmpName').val(params.NAME);
		} else { //그리드 검색 버튼에서 호출 콜백
			var rowNode = grid1.api.getRowNode(Number(params.rowId));
			rowNode.setDataValue('EMPNUM', params.EMPNUM);
			rowNode.setDataValue('EMPNAME', params.NAME);
		}
	}
	
</script>
<form name="popupform" id="popupForm">
		<input type="hidden" name="status" id="status" /> <input type="hidden" name="id" id="id" />
</form>


<section class="content">
	<form id="searchForm" name="searchForm" method="post">
		<input type="hidden" name="startNum" value="0"/>
		<input type="hidden" name="pageSize" />
		<input type="hidden" name="sortName" id ="sortName" value="" />
		<input type="hidden" name="sortOrder"  id ="sortOrder" value="" />
		
		<div class="search-wrapper">
			<ul class="primary">
				<li class="title"><i class="fa fa-fw fa-star"></i> 조회조건</li>
				<li class="search-bt-wrapper">
					<button type="button" id="btnMore" class="btn btn-default btn-sm">+더보기</button>
					<button type="button" id="btnInq" class="btn btn-default btn-sm">조회</button>
				</li>
			</ul>
			
			<div class="search-area">
				<div class="col-md-3">
						<div class="form-group">
								<label>이름</label> <input type="text" id="searchName" name="searchName">
						</div>
				</div>
			</div>
		</div>
	</form>
	
	<div class="grid-wrapper">
		<ul class="primary">
			<li class="title"><i class="fa fa-fw fa-heart"></i> 목록</li>
			<li class="active">
				<!-- <button type="button" id="btnExcel" class="btn btn-default btn-sm"><i class="fa fa-lg fa-file-excel"></i> 엑셀</button> -->
				<button type="button" id="btnNew" class="btn btn-default btn-sm">신규</button>
				<!-- <button type="button" id="btnSave" class="btn btn-default btn-sm">저장</button> -->
				<button type="button" id="btnDel" class="btn btn-default btn-sm">삭제</button>
			</li>
		</ul>
		<div id="grid1" style="height: 70vh;" class="ag-theme-balham"></div>
	</div>
</section>


                    
