/* ****************************************
* < ag-grid utils >
* @author cjh / flash21
**************************************** */
/**
 * currency 표현. 세자리 마다 콤마
 */
Number.prototype.format = function(n, x) {
    var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
    return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
};

/**
 * NameValue Object
 */
var NameValue = function (pname, pvalue){
	this.name = pname;
	this.value = pvalue;
}
/**
 * cellRenderer 등에서 사용하기 위한 것으로 
 * 배열로 코드를 받아서 node의 값과 일치하는 value를 반환한다. 
 */
var getCodeValue = function(codes, nodeData){
	if(!codes) return " - ";
	for(var i=0;i<codes.length;i++){
		if(nodeData.value == codes[i].name){
			return codes[i].value;
		}
	}
	return " - ";
}

/**
 * 콤보박스 처리 
 * 객체에서 key값 추출하는 함수
 * var data = {
 * 	headerName : "월",
 * 	field : "mon",
 * 	width : 100,
 * 	editable : true,
 * 	cellEditor : 'agSelectCellEditor',
 * 	cellStyle : {
 * 		"textAlign" : "center"
 * 	},
 * 	cellRenderer : CommonGrid.convertLeadTime,
 * 	cellEditorParams : {
 * 		values : extractValues(CommonCode.leadTimeCodes())
 * 	},
 * 	valueFormatter : function(params) {
 * 		return lookupValue(CommonCode.leadTimeCodes(), params.value);
 * 	},
 * 	valueParser : function(params) {
 * 		return lookupKey(CommonCode.leadTimeCodes(), params.newValue);
 * 	}
 * }
 */
function extractValues(mappings) {
	var params = new Array();
	for (var i = 0; i< mappings.length;i++) {
		params.push(mappings[i].name);
    }
    return params;
}

/**
 * 객체에서 key로 value을 반환하는 함수, 콤보박스에서 사용
 */
function lookupValue(mappings, key) {
	for (var i = 0; i< mappings.length;i++) {
        if (mappings[i].name == key) {
			return mappings[i].value;
        }	
    }
	return "";
}

/**
 * 객체에서 vaue로 key를 반환하는 함수, 콤보박스에서 사용
 */
function lookupKey(mappings, name) {
	for (var i = 0; i< mappings.length;i++) {
        if (mappings[i].value == name) {
			return mappings[i].name;
        }	
    }
	return "";
}

function fnGridSelect(obj) {
	var objSplit = obj.split(';');
	var codes = [];
	
	if(objSplit.length > 0) {
		for ( var i=0; i<objSplit.length; i++ ) {
			var jbSplit = objSplit[i].split(':');
			
			if(objSplit.length > 0) {
				for ( var j=0; j < jbSplit.length; j++ ) {
					codes[i] = new NameValue(jbSplit[0], jbSplit[1]);
				}
			}
		}
	}
	//console.log(obj + " ::::::::::::::: " + codes);
	return codes;
}

/**
 * 숫자를 입력받아 3자리마다 콤마 출력 
 */
function formatNumber(number) {
	if(number == null || number == '') return '';
    // this puts commas into the number eg 1000 goes to 1,000,
    // i pulled this from stack overflow, i have no idea how it works
	
	return Math.floor(number).toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
	
}

function getCharCodeFromEvent(event) {
    event = event || window.event;
    return (typeof event.which == "undefined") ? event.keyCode : event.which;
}

function isCharNumeric(charStr) {
    return !!/\d/.test(charStr);
}

function isKeyPressedNumeric(event) {
    var charCode = getCharCodeFromEvent(event);
    var charStr = String.fromCharCode(charCode);
    return isCharNumeric(charStr);

}

function agTextCustomCellEditor() {
	function TextCustomCellEditor() {
	}
	
	TextCustomCellEditor.prototype.init = function(params) {
		/*console.log("★★★★★★★★★ params :: " + params);
		for(var a in params){
			console.log(a + " -> " + params[a]);
		}*/
		
	    // create the cell
	    this.eInput = document.createElement('input');
	    $(this.eInput).attr("type","text");
	    $(this.eInput).attr("class","ag-cell-edit-input");
	    
	    if(typeof params.maxLength != "undefined" && params.maxLength > 0){
	    	$(this.eInput).attr("maxLength", params.maxLength);
		} else {
			$(this.eInput).attr("maxLength", "50");
		}
	    
	    this.eInput.value = params.value;
	};

	// gets called once when grid ready to insert the element
	TextCustomCellEditor.prototype.getGui = function() {
	    return this.eInput;
	};

	// focus and select can be done after the gui is attached
	TextCustomCellEditor.prototype.afterGuiAttached = function() {
		//console.log('TextCustomCellEditor.afterGuiAttached()');
	    this.eInput.focus();
	    this.eInput.select();
	};

	// returns the new value after editing
	TextCustomCellEditor.prototype.getValue = function() {
	    return this.eInput.value;
	};

	// any cleanup we need to be done here
	TextCustomCellEditor.prototype.destroy = function() {
	    // but this example is simple, no cleanup, we could
	    // even leave this method out as it's optional
	};

	// if true, then this editor will appear in a popup
	TextCustomCellEditor.prototype.isPopup = function() {
	    // and we could leave this method out also, false is the default
	    return false;
	};
	
	TextCustomCellEditor.prototype.focusIn = function() {
		console.log('TextCustomCellEditor.prototype.focusIn');
	};

	TextCustomCellEditor.prototype.focusOut = function() {
		console.log('TextCustomCellEditor.prototype.focusOut');
	};
	
	return TextCustomCellEditor;
}

function agNumericCellEditor() {
	function NumericCellEditor() {
	}

	NumericCellEditor.prototype.init = function (params) {
	    // create the cell
	    this.eInput = document.createElement('input');
	    $(this.eInput).attr("class","ag-cell-edit-input");
	    this.eInput.value = isCharNumeric(params.charPress) ? params.charPress : ((typeof params.value == "undefined") ? 0 : params.value);
	    if(typeof params.maxLength != "undefined" && params.maxLength > 0){
	    	$(this.eInput).attr("maxLength", params.maxLength);
		} else {
			$(this.eInput).attr("maxLength", "10");
		}
	    
	    var that = this;
	    this.eInput.addEventListener('keypress', function (event) {
	        if (!isKeyPressedNumeric(event)) {
	            that.eInput.focus();
	            if (event.preventDefault) event.preventDefault();
	        }
	    })
	    this.eInput.addEventListener('keyup', function (event) {
	        agNumricKeyUp(event, this);
	    })
	    this.eInput.addEventListener('keydown', function (event) {
	    	agNumricKeyDown(event, this);
	    });

	    // only start edit if key pressed is a number, not a letter
	    var charPressIsNotANumber = params.charPress && ('1234567890'.indexOf(params.charPress) < 0);
	    this.cancelBeforeStart = charPressIsNotANumber;
	};

	// gets called once when grid ready to insert the element
	NumericCellEditor.prototype.getGui = function () {
	    return this.eInput;
	};

	// focus and select can be done after the gui is attached
	NumericCellEditor.prototype.afterGuiAttached = function () {
	    this.eInput.focus();
	};

	// returns the new value after editing
	NumericCellEditor.prototype.isCancelBeforeStart = function () {
	    return this.cancelBeforeStart;
	};

	// example - will reject the number if it contains the value 007
	// - not very practical, but demonstrates the method.
	NumericCellEditor.prototype.isCancelAfterEnd = function () {
	    var value = this.getValue();
	    return value.indexOf('007') >= 0;
	};

	// returns the new value after editing
	NumericCellEditor.prototype.getValue = function () {
	    return this.eInput.value;
	};

	// any cleanup we need to be done here
	NumericCellEditor.prototype.destroy = function () {
	    // but this example is simple, no cleanup, we could  even leave this method out as it's optional
	};

	// if true, then this editor will appear in a popup 
	NumericCellEditor.prototype.isPopup = function () {
	    // and we could leave this method out also, false is the default
	    return false;
	};
	
	return NumericCellEditor;
}

/**
 * Date Picker 사용법 
 * 1. 칼럼 정의 시 editable : true, cellEditor : 'datePicker' 추가 
 * 2. 칼럼옵션에 컴포넌트 추가 
 * gridOpt.components = {
 *  datePicker: getDatePicker('yy-mm-dd') // date format 지정시 포맷 전달 
 *  datePicker: getDatePicker()  // 기본은 yymmdd 8자리로 됨 
 * };
 */
function getDatePicker(paramFmt) {
	var _this = this;
	_this.fmt = "yymmdd";
	if(paramFmt){
		_this.fmt = paramFmt;
	}
    // function to act as a class
    function Datepicker() {}

    // gets called once before the renderer is used
    Datepicker.prototype.init = function(params) {
        // create the cell
        this.eInput = document.createElement('input');
        $(this.eInput).attr("class","ag-cell-edit-input");
        this.eInput.value = (typeof params.value == "undefined") ? '' : params.value;

        // https://jqueryui.com/datepicker/
        $(this.eInput).datepicker({
            dateFormat: _this.fmt
        });
    };
    
    Datepicker.prototype.onSelect = function(dateText) {
    	//console.log("Selected date: " + dateText + "; input's current value: " + this.value);
    };
    
    // gets called once when grid ready to insert the element
    Datepicker.prototype.getGui = function() {
        return this.eInput;
    };

    // focus and select can be done after the gui is attached
    Datepicker.prototype.afterGuiAttached = function() {
        this.eInput.focus();
        this.eInput.select();
    };

    // returns the new value after editing
    Datepicker.prototype.getValue = function() {
        return this.eInput.value;
    };

    // any cleanup we need to be done here
    Datepicker.prototype.destroy = function() {
        // but this example is simple, no cleanup, we could
        // even leave this method out as it's optional
    };

    // if true, then this editor will appear in a popup
    Datepicker.prototype.isPopup = function() {
        // and we could leave this method out also, false is the default
        return false;
    };

    return Datepicker;
}

function getFileCellRenderer() {
	  function FileCellRenderer() {}

	  FileCellRenderer.prototype.init = function(params) {
	    var tempDiv = document.createElement('div');
	    var value = params.value;
	    tempDiv.innerHTML = value;
	    this.eGui = tempDiv.firstChild;
	  };
	  FileCellRenderer.prototype.getGui = function() {
	    return this.eGui;
	  };

	  return FileCellRenderer;
	}

//grid selectbox change function event init
function agSelectBox(obj){
	
}
function agInputKeyDown(obj){
	
}
function agInputKeyUp(obj){
	
}
function agInputKeyPress(obj){
	
}
function agNumricKeyUp(e, obj){
	
}
function agNumricKeyDown(e, obj){
	/*for(var i in obj){
		console.log(i +" :: "+ obj[i]);
	}*/
	//alert(obj.value);
}

/* ********************
 * ag-grid 초기 설정 시작
 ******************** */ 

function AgGridObj(){
	var _this = this;
	this.api = null;
	this.gridDiv = null;
	this.gridCurrent = 1;
	this.rowCheckboxYn = null;
	this.searchUrl = null;
	this.saveUrl = null;
	this.gridOpts = null;
	this.columnDefs = new Array();
	
	var treeData = false;
	var noPaging = false;
	var pagination = null;
	var pageSize = null;
	var idSequence = null;
	var isSort = null;
	
	var groupName = null;
	var colGroupObj = new Object();
	var colChildrenObj = new Array();
	
	//this.rowModelType = null;
	
	// 그리드 로우 체크박스사용 여부
	//if(this.pRowCheckboxYn = true) this.columnDefs[0] = {headerCheckboxSelection: true, headerCheckboxSelectionFilteredOnly: true, checkboxSelection: true, width: 25};
	
	 this.getDefaultGridOpt = function() {
		 return {
			 singleClickEdit : true,
			 stopEditingWhenGridLosesFocus : true,
			 columnDefs: this.columnDefs,
			 rowSelection: 'multiple', /* 'single' or 'multiple',*/
			 //singleClickEdit: true,	// 클릭 시 수정
			 editType : 'fullRow',	// 로우전체 셀 수정
			 enableRangeSelection: true,
			 suppressRowClickSelection: false,
			 animateRows: true,
			 //deltaRowDataMode: true,
			 //suppressCellSelection: true, // 키보드 포커스 허용여부
			 //suppressCellSelection : true, // 키보드 탐색 허용여부
			 //suppressCellFlash: true, 
			 //suppressHorizontalScroll: false, // true로 설정시 가로스크롤바 비활성화
			 defaultColDef: {
			     sortable: true
			     , resizable: true
			     //, editable: true
			 },
			 components : {
				 //datePicker : getDatePicker('yy-mm-dd')
				 datePicker : getDatePicker()
				 , agNumericCellEditor : agNumericCellEditor()
				 , fileCellRenderer: getFileCellRenderer()
				 //, agTextCustomCellEditor : agTextCustomCellEditor()
			 },
			 localeText: {noRowsToShow: '조회 결과가 없습니다.'},
			    getRowStyle: function (param) {
			        if (param.node.rowPinned) {
			            return {'font-weight': 'bold', background: '#dddddd'};
			        }
			        return {'text-align': 'center'};
			    },
			    getRowHeight: function(param) {
			        if (param.node.rowPinned) {
			            return 30;
			        }
			        return 30;
			    },
			    // getRowNode의 기준 필드 지정
			    /*getRowNodeId: function (data) {
			        return data.id;
			    },*/
			    
			    // 리사이즈 
			    onGridReady: function(params) {
		    		params.api.sizeColumnsToFit();
			    },
			    // 창 크기 변경 되었을 때 이벤트 
			    onGridSizeChanged: function(params) {
		    		params.api.sizeColumnsToFit();
			    },
			    // 셀 내용 수정했을 때 이벤트
			    onCellValueChanged: function(params) {
			    	if(params.oldValue != params.newValue) { // 내용 바뀌었는지 체크
			    		params.data.edit = true;
			    		
			    		if(params.data.STATUS != 'C') {
			    			var rowNode = _this.api.getDisplayedRowAtIndex(params.rowIndex); //index로 rowNode 선언
				    		rowNode.setDataValue('STATUS', 'U'); // 내용 변경시 상태값 U
				    		
				    		
				    		/*var selectedRows = _this.api.getSelectedRows();
				    		var rowNode = _this.api.getRowNode(selectedRows[0].id); //getRowNodeId에서 지정한 필드의 값으로 row를 찾아 rowNode 선언
				    		rowNode.setDataValue('STATUS', 'U');*/
			    		}
			    		
			    		fnAgGirdStatusChg(_this, params.rowIndex);
			    	}
			    },
			    onRowEditingStarted: function (event) {
			        console.log('never called - not doing row editing');
			    },
			    onRowEditingStopped: function (event) {
			        console.log('never called - not doing row editing');
			    },
			    onCellEditingStarted: function (event) {
			        console.log('cellEditingStarted');
			    },
			    onCellEditingStopped: function (event) {
			        console.log('cellEditingStopped');
			    },
			    // 셀 내용 수정 시작 이벤트
			    onCellKeyPress: function(params) {
			    	if(params.data.STATUS != 'C') { // 내용이바뀌었고 신규가 아니면
			    		fnAgGirdStatusChg(_this, params.rowIndex);
			    	}
			    },
			    // 그리드 스크롤 이벤트
			    onBodyScroll: function(params) {
			    	fnAgGirdStatusChg(_this, params.rowIndex);
			    },
			    onRowClicked: function(params) {
			    	//console.log('onRowClicked..');
			    },
			    // 그리드 정렬 이벤트
			    onSortChanged : function(params) { 
			    	//console.log('=========== onSortChanged Start ===========');
			    	
			    	//console.log('pagination :: ' + _this.pagination);
			    	//console.log("그리드ID :: " + _this.gridDiv);
			    	/*console.log('aa :: ' + JSON.stringify(params.api.sortController.getSortModel()));
			    	console.log('length :: ' + params.api.sortController.getSortModel().length);
			    	console.log('gridDiv :: ' + _this.gridDiv);
			    	console.log('searchForm :: ' + _this.searchForm);*/
			    	
			    	//_this.isSort = false;
			    	var sortingArray = params.api.sortController.getSortModel();  
			    	/*console.log("_this.searchForm :: " + _this.searchForm);
			    	console.log("sortingArray.length :: " + sortingArray.length);
			    	console.log("_this.pageSize :: " + _this.pageSize);*/			    	
			    	
			    	if(_this.searchForm != null && sortingArray.length == 1) {
			    		_this.isSort = true;
			    		
			    		$('input[name="sortName"]', _this.searchForm).val( sortingArray[0].colId );
			    		$('input[name="sortOrder"]', _this.searchForm).val( sortingArray[0].sort );
			    	} else if(_this.searchForm != null && sortingArray.length == 0) {
			    		_this.isSort = true;
			    		
			    		$('input[name="sortName"]', _this.searchForm).val( '' );
			    		$('input[name="sortOrder"]', _this.searchForm).val( '' );
			    	}
			    	
			    	$('input[name="startNum"]', _this.searchForm).val( 0 );
			    	$('input[name="pageSize"]', _this.searchForm).val( _this.pageSize );
			    		
			    	//console.log('sortName :: ' + $('input[name="sortName"]', _this.searchForm).val());
			    	//console.log('sortOrder :: ' + $('input[name="sortOrder"]', _this.searchForm).val());
			    	
			    	//_this.api.purgeVirtualPageCache();
			    	//_this.api.purgeInfinitePageCache();
			    	
			    	var obj = _this.api;
			    	for(var a in obj){
						//console.log(a + " -> " + obj[a]);
					}
			    	
			    	//console.log('=========== onSortChanged End ===========');
			    },
			    onPaginationChanged : function(params) { 
			    	//console.log('onPaginationChanged ...');
			    	//console.log('nextPage :: ' + params.newPage);
			    	/*var obj = params;
			    	for(var a in obj){
						console.log(a + " -> " + obj[a]);
					}*/
			    	
			    },
			    // 셀 내용 수정 끝났을 때 이벤트
			    /*onCellEditingStopped: function(params) {
	                _this.api.updateRowData({update: [params.data]}); //수정 row 구분하기 위함
	                
	                fnAgGirdStatusChg(_this, params.rowIndex);
	            },*/
			    debug: false
			 };
		};
			
			this.pushColumnDefGroupStart= function () {
				var args = arguments; // Parameters
				_this.colGroupObj = new Object();
				
				_this.groupName = args[0];
				_this.colGroupObj.headerName = args[0];
				_this.colGroupObj.headerClass = 'grid-group-header';
				_this.colChildrenObj = new Array();
			}
			
			this.pushColumnDefGroupEnd= function () {
				_this.groupName = null;
				_this.colGroupObj.children = _this.colChildrenObj;
				_this.columnDefs.push(_this.colGroupObj);
				
				
				_this.colGroupObj = new Object();
				_this.colChildrenObj = new Array();
			}
			// 그리드 컬럼정의등록(headerName, field, align, width, editable, hide, convertStyle, valueGetter)
			/* 컬럼 주요 속성
		    headerName			Title 				그리드 헤더에 보여주는 title 
		    field 				data field 			json data에 선언이 되어 있는 field와 바인딩된다.  
		    width 				칼럼 길이 				칼럼 가로 사이즈, 자동 맞춤으로 하는 경우 무시된다.  
		    minWidth 			최소 칼럼 길이  			최소 칼럼 사이즈로 자동 맞춤을 하더라도 유지되는 최소 값이다.  
		    pinned 				left | right 		칼럼 고정시 이용, 칼럼 고정이 되면 왼쪽 또는 오른쪽에 고정이 되고 스크롤에서 제외된다.  칼럼을 자동 맞춤으로 하는 경우는 의미가 없다. 
		    suppressSizeToFit 	true | false  		자동 맞춤 강제여부 
		    suppressMenu  		true | false  		헤더에 메뉴 노출 여부, true인 경우 칼럼 메뉴가 사라진다.  
		    suppressSorting 	true | false  		정렬 기능 강제여부, true인 경우 정렬이 고정이 된다.  
		    sort 				asc | desc 			정렬 순서 정의
		    hide 				true | false  		hide가 true인 경우 감춰진다. 
		    editable 			true | false  		수정 여부, true인 경우 데이터를 수정할 수 있다. 
		    cellEditor 			true | false  		편집 컴포넌트 지정. agSelectCellEditor, datePicker, agLargeTextCellEditor
		    cellStyle 			스타일지정   				cell 스타일 지정 ex){"textAlign":"center", "backgroundColor":"#f4f4f4"}
		    cellRenderer 		renderer  			값을 M | F로 받고 보여 주는 것은 남성 | 여성 또는 그래프나 아이콘으로 보여 주고 싶을 때 cellRenderer를 이용해서 구현하면 쉽게 표현 할 수 있습니다. 
		    cellClass 			클래스명				cell의 클래스명 지정 
		    */
			this.pushColumnDef= function () {
				// 컬럼명, 컬럼변수, 정렬, 컬럼넓이, 수정여부, 숨김여부, 셀 렌들러타입, 셀 데이터, cellStyle 
				var args = arguments; // Parameters
				var colObj = new Object();
				
				
				// 플랫폼 구분
				var filter = "win16|win32|win64|mac";
				if(navigator.platform){
					
					// 모바일의 경우
					if(0 > filter.indexOf(navigator.platform.toLowerCase())){ // 모바일
//						colObj.lockPosition= true;
//						colObj.cellClass='locked-col';
						colObj.suppressMovable=true;
					}else{ // PC
					}

				}
				if(_this.rowCheckboxYn) {
					this.columnDefs[0] = {
						headerCheckboxSelection : true
						, headerCheckboxSelectionFilteredOnly : true
						, checkboxSelection : true
						, width : 45
					};
				}
				colObj.headerName = args[0];	// 헤더명
				colObj.headerClass = "headerCell";	// 헤더클래스
				colObj.field = args[1];	// 매핑컬럼
				
				if(args[2] != null) colObj.cellStyle = {"text-align": args[2]};	// 'left'/'center'/'right'
				if(args[3] != null) colObj.width = args[3];
				if(args[4] != null) {
					colObj.editable = args[4];	// 수정가능여부 : true/false
					
					if(colObj.field.toUpperCase() == 'RNUM') {
						colObj.sortable = false;
					}
					//console.log("colObj.field :: " + colObj.field);
				}
				if(args[5] != null) colObj.hide = args[5];	// 숨김여부 : true/false
				
				if(args[6] != null) {
					if(args[6] == 'button' && args[7] != null) {
						colObj.cellRenderer = function (params) {
							//var rowId = params.rowIndex;
							var rowId = params.data.RNUM;
							
							return '<div>' + args[7].replace(/#rowId#/g,rowId) + '</div>';
				    	}
					} else if(args[6] == 'selectBox' && args[7] != null) {
						colObj.cellRenderer = function(params) {
							return getCodeValue(fnGridSelect(args[7]), params);
						}
						
						colObj.cellEditor = "agSelectCellEditor";
						
						colObj.cellEditorParams = {
							values: extractValues( fnGridSelect(args[7]) )
						}
						
						colObj.valueFormatter = function (params) {  return lookupValue(fnGridSelect(args[7]), params.value); }
						
						colObj.valueParser = function (params) { return lookupKey(fnGridSelect(args[7]), params.newValue); }
					} else if(args[6] == 'datePicker') {
						colObj.cellEditor = 'datePicker';
						
						colObj.valueFormatter = function (params) { return _this.convertDate(params); }
						
						//colObj.valueParser = function (params) { console.log(params); }
					} else if(args[6] == 'number') {
						colObj.cellEditor = "agNumericCellEditor";
						if(colObj.editable && args[8] != null && args[8] > 0) {
							colObj.cellEditorParams = {
									maxLength : args[8]
							}
						}
						
						//colObj.type = "numericColumn";
							
						colObj.cellStyle = {'text-align': 'right'};
						
						colObj.valueFormatter = function (params) {
							return formatNumber(params.value);
				    	}
					} else if(args[6] == 'rownum') {
						
						//colObj.valueGetter = function(params) { return params.node.rowIndex + 1; }
						
					} else if(args[6] == 'textarea') {
						colObj.cellEditor = 'agLargeTextCellEditor';
						
						colObj.cellStyle = {'text-align': 'left', 'white-space': 'normal'};
					} else if(args[6] == 'checkbox') {
						colObj.headerName = "<input type='checkbox' />";
					} else if(args[6] == 'file') {
					} else if(args[6] == 'html') {
						colObj.cellRenderer = args[7];
							//var rowId = params.rowIndex;
							//var rowId = params.data.RNUM;
							
							//return  args[7];
				    	
					}
				} else {
					/*colObj.cellEditor = 'agTextCustomCellEditor';
					if(colObj.editable && args[8] != null && args[8] > 0) {
						colObj.cellEditorParams = {
								maxLength : args[8]
						}
					}*/
				}
				
				// cellStyle
				if(args[9] != null) {
					colObj.cellStyle = args[9];
				}
				
				// filter 설정
				if(args[10] != null) {
					// Provided Filters
					// agNumberColumnFilter	숫자 비교를위한 숫자 필터.
					// agTextColumnFilter	문자열 비교를위한 텍스트 필터.
					// agDateColumnFilter	날짜 비교를위한 날짜 필터.
					// agSetColumnFilter	Microsoft Excel에서 필터가 작동하는 방식에 영향을받는 집합 필터. 이것은 ag-Grid-Enterprise 기능

					if(args[10] == 'text') {
						colObj.filter = "agTextColumnFilter";
					} else if(args[10] == 'number') {
						colObj.filter = "agNumberColumnFilter";
					} else if(args[10] == 'date') {
						colObj.filter = "agDateColumnFilter";
					} else if(args[10] == 'code') {
						colObj.component = "agRichSelect";
						colObj.filter = "agTextColumnFilter";
					} 
				}
				
				// filterParams 설정
				if(colObj.filter != null && args[11] == null) {
					colObj.filterParams = {
						defaultOption:'contains'
					}
				} else if(colObj.filter != null && args[11] != null) {
					colObj.filterParams = args[11];
				}
				
				// 열을 기준으로 집계처리 여부
				if(args[12] != null) colObj.enableValue = args[12];
				
				// 열을 기준으로 그룹화 여부
				if(args[13] != null) colObj.enableRowGroup = args[13];
				
				// 차트 데이터 타입
				// chartDataType = 'category' | 'series' | 'excluded' | undefined
				if(args[14] != null) colObj.chartDataType = args[14];
				
				colObj.chartDataType = "series";

				// 툴팁사용
				if(args[15] != null) colObj.tooltipField = args[15];
				
				var colHObj = new Object();	// 헤더
				
				if(_this.groupName != null){	// 헤더가 있을 경우
					_this.colChildrenObj.push(colObj);
				}else{	// 헤더가 없을 경우
					this.columnDefs.push(colObj);
				}
				
			};
			
			this.getRowStyle= function (param) {
				if (param.node.rowPinned) {
					return {'font-weight': 'bold', background: '#dddddd'};
				}
				return {'text-align': 'center'};
			};
			
			this.getRowHeight= function(param) {
				if (param.node.rowPinned) {
					return 30;
				}
				return 30;
			};
			// 리사이즈 
			this.onGridReady= function(params) {
				params.api.sizeColumnsToFit();
			};
			// 창 크기 변경 되었을 때 이벤트 
			this.onGridSizeChanged= function(params) {
				params.api.sizeColumnsToFit();
			};
			this.debug= false;
			this.convertTime = function (param){
				if(!param.value) {
					return this.defaultBlank;
				}else {
					return moment(param.value, "YYYYMMDDHHmmss").format("HH:mm");
				}
			};
			this.convertDate = function(param) { // 8자리 문자열을 날짜로 변환
				if(!param.value) {
					return this.defaultBlank;
				}else{
					if(param.value.length ==8) {
						return moment(param.value, "YYYYMMDD").format("YYYY-MM-DD");
					}else{
						return param.value;
					}
				}		
			};
			this.convertTimestamp = function(param) { // 8자리 문자열을 날짜로 변환
				if(!param.value) {
					return this.defaultBlank;
				}else {
					return moment(param.value, "YYYYMMDDHHmmss").format("YYYY-MM-DD HH:mm:ss");
				}		
			};
			this.formatDate = function(param) { // long 값을 날짜로 변환
				if(!param.value) {
					return this.defaultBlank;
				}else {
					return moment(parseInt(param.value)).format("YYYY-MM-DD");
				}		
			};
			this.formatTimestamp = function(param) { // long 값을 날짜로 변환
				if(!param.value) {
					return this.defaultBlank;
				}else {
					return moment(parseInt(param.value)).format("YYYY-MM-DD HH:mm:ss");
				}		
			};
			this.numberOnly = function(param){ 
				if(!param.value || param.value =='') return '';
				if (typeof param.value === 'string') {
					return param.value.replace(/[^0-9\.]+/g, '');
				}
				return CommonGrid.formatCurrency(param);
			};
			this.formatCurrency = function (param){
				if(!param.value && param.value != "0") {
					return this.defaultBlank;
				}	
				return parseInt(param.value).format();
			};
			this.formatNumber = function(param){
				if(!param.value && param.value != "0") {
					return this.defaultBlank;
				}	
				return numberFormatPoint2Digits(param.value);
			};
			this.convertCommon = function(codes, param){
				if(!codes) return " - ";
				for(var i=0;i<codes.length;i++){
					if(param.value == codes[i].name){
						return codes[i].value;
					}
				}
				return this.defaultBlank;
			};
			this.getCode = function(codes, value){
				for(var i=0;i<codes.length;i++){
					if(value == codes[i].value){
						return codes[i].name;
					}
				}
				return "";
			};
			this.checkMobile = function(gridOpts){
				// 모바일 브라우저 가로크기 체크
				if (document.body.clientWidth < 800) {
					if(gridOpts){
						gridOpts.columnDefs.forEach(function(el) {
							el.pinned = null;
							if(el.children){
								el.children.forEach(function(subEl) {
									subEl.pinned = null;
								});
							}
						});
					}
				}
			};
			this.makeTopGrid = function(gridMainOpts, rowData, params){
				var colsSum = [];
				gridMainOpts.columnDefs.forEach(function(coldefs, index) {
					if(coldefs.children){
						coldefs.children.forEach(function(child, index) {
							if(child.isSum && child.isSum == true){
								var temp = {};
								temp[child.field] = 0;
								temp["field"] = child.field;
								colsSum.push(temp);
							}
						});
					}else{
						if(coldefs.isSum && coldefs.isSum == true){
							var temp = {};
							temp[coldefs.field] = 0;
							temp["field"] = coldefs.field;
							colsSum.push(temp);
						}
					}
				});
				rowData.forEach(function(rowvalue, index) {
					colsSum.forEach(function(value, index) {
						if(rowvalue[value.field] && !rowvalue[value.field].isNaN)
							value[value.field] += rowvalue[value.field];
					});
				});
				var resultSum = {}
				colsSum.forEach(function(cValue, index) {
					resultSum[cValue.field] = cValue[cValue.field];
					
				});
				if(params && params.length>0){
					params.forEach(function(value, index) {
						resultSum[value.field] = value.value;
					});
				}
				resultSum['top'] = true;
				var resultArray = [];
				resultArray[0] = resultSum;
				gridMainOpts.api.setPinnedTopRowData(resultArray);
			};
			
			// 그리드생성
			this.agGridInit = function(){
				
				var gridDiv = document.querySelector('#'+_this.gridDiv);
				var gridPageSize = (_this.pageSize == null) ? 20 : _this.pageSize;
				
				// clientSide, infinite, viewport, serverSide
				
				if(_this.treeData){ 
					_this.gridOpts.treeData = true;
					_this.gridOpts.groupDefaultExpanded = -1;	//tree
					//tree사용시 기준열
					_this.gridOpts.getDataPath = function(data) {
				        return data.TREEDATA.split('/');
				    }
					_this.gridOpts.autoGroupColumnDef = {
						headerName : "그룹명"
						, width : 300
					    , cellRendererParams: {
					      innerRenderer: 'fileCellRenderer',
					    }
					}
				}
				
				if(!_this.noPaging) {
					// 서버 방식
					_this.gridOpts.rowModelType = 'infinite';
					
					//_this.gridOpts.infiniteInitialRowCount = 0;
					_this.gridOpts.cacheBlockSize = gridPageSize;
					_this.gridOpts.maxBlocksInCache = 0;
					
					if(_this.pagination) {
						_this.gridOpts.pagination = true;
						_this.gridOpts.paginationPageSize = gridPageSize;
					}
				} else {
					// 클라이언트 방식
					_this.gridOpts.rowModelType = 'clientSide';
					
					if(_this.pagination) {
						_this.gridOpts.pagination = true;
						_this.gridOpts.cacheBlockSize = gridPageSize; // you can have your custom page size
						_this.gridOpts.paginationPageSize = gridPageSize; //pagesize
					}
				}
				
				var agObj = new agGrid.Grid(gridDiv, _this.gridOpts);
				_this.api = _this.gridOpts.api;
				
				//if(_this.noPaging) _this.gridOpts.api.hideOverlay();
				
				//_this.api.setRowData(this.searchUrl);
				
				//fnAgGirdSearch(_this, this.searchForm);
			}
}

function fnGetRowNode(gridObj, rowId) {
	var objNode = null;
	
	//console.log('fnGetRowNode gridObj 1 :: ' + rowId);
	rowId = rowId - 1;
	//console.log('fnGetRowNode gridObj 2 :: ' + rowId);
	gridObj.api.forEachNode(function(rowNode, idx){
		if(rowId == idx) {
			//console.log("aaa :: " + rowNode);
			objNode = rowNode;
		}
		//console.log("rowId["+rowId+"], idx[" + idx + "], data.ID :: " + rowNode.data.ID);
	});
	
	return objNode;
}

/* ********************
 * 그리드 조회
 ******************** */ 
function fnAgGirdSearch(){
	var args = arguments;

	var gridObj = args[0];
	var frm = args[1];
	var callBackFunction = args[2];
	
	gridObj.api.deselectAll();
	
	if(!gridObj.noPaging) {
		gridObj.idSequence = 0;
		
		$('input[name="startNum"]', frm).val( 0 );
		$('input[name="pageSize"]', frm).val( (gridObj.pageSize==null) ? 20 : gridObj.pageSize );
		
		var dataSource = {
				rowCount: null,
				//infiniteInitialRowCount: 0,
				maxPagesInPaginationCache: 0,
		        getRows: function (params) {
		        	$(frm).ajaxSubmit({
		        		type: 'post'
		        		, url: gridObj.searchUrl
		        		, beforeSend : function(xhr, opts) {
		        			gridObj.gridOpts.api.hideOverlay();
		        			
		        			gridObj.gridOpts.api.refreshCells();
		        			
		        			//if(gridObj.isSort) $('input[name="startNum"]', frm).val(0);
		        			
		        			/*console.log('gridDiv :: ' + gridObj.gridDiv);
					    	console.log('정렬이벤트 발생 여부 :: ' + gridObj.isSort);
					    	console.log('시작번호 :: ' + $('input[name="startNum"]', frm).val());
					    	console.log('pageSize :: ' + $('input[name="pageSize"]', frm).val());
					    	console.log('정렬필드명 :: ' + $('input[name="sortName"]', frm).val());*/
		        		} , success: function(obj) {
		        			var lastRow = -1;
		        			
		        			//console.log("1__gridObj.isSort [" + gridObj.isSort + "], startNum[" + $('input[name="startNum"]', frm).val() + "]");
		        			
		        			if(obj!=null && Number(obj.endRowNum) > 0) {
		        				$('input[name="startNum"]', frm).val(Number(obj.endRowNum));
		        			}
		        			
		        			if(obj!=null && obj.list.length > 0) {
		        				var data = obj.list;
		        				
		        				data.forEach( function(item) {
		        					item.id = gridObj.idSequence++;
		    			        });
		        				
		        				var dataRows = data;
		        				//window.rowDataServerSide = data;
		        				
		        				//console.log("rowModelType :: " + gridObj.gridOpts.rowModelType);
		            			//console.log("data.length [" + data.length + "], startRow[" + params.startRow + "] endRow[" + params.endRow + "]");
		            			
		        				//console.log("Number(obj.endRowNum) [" + Number(obj.endRowNum) + "], Number(obj.total) [" + Number(obj.total) + "]");
		        				
			                    if (Number(obj.endRowNum) >= Number(obj.total)) {
			                    	if(!gridObj.gridOpts.pagination) {
			                    		$('input[name="startNum"]', frm).val( 0 );
			                    		$('input[name="pageSize"]', frm).val( Number(obj.endRowNum) );
			                    	}
			                    	
			                        lastRow = Number(obj.total);
			                    }
			                    
			                    if(Number(obj.total) <= Number(gridObj.pageSize)) {
			                    	//gridObj.gridOpts.cacheBlockSize = Number(obj.total) - 1;
			                    }
			                    
			                    if(gridObj.isSort) {
			                    	gridObj.isSort = false;
			                    }
			                    
			                    params.successCallback(dataRows, lastRow);
			                    
			                    if (callBackFunction != null && callBackFunction != undefined) {
									callBackFunction();
								}
		        			} else {
		        				params.successCallback([], 0);
		        				//console.log("데이터가 없습니다.");
		        				gridObj.gridOpts.api.showNoRowsOverlay();
		        			}
		        		}, error: function(xhr, status, err) {
		        			params.failCallback();
		        			
		        			console.log(xhr);
		        			
		        			alert(xhr);
		        			alert(status);
		        			alert(err);
		        		}
		        	});
		        	
		        }
		    };
			
			if(gridObj.gridOpts.rowModelType == 'infinite') {
				gridObj.api.setDatasource(dataSource);
			} else if(gridObj.gridOpts.rowModelType == 'serverSide') {
				gridObj.api.setServerSideDatasource(dataSource);
			} else if(gridObj.gridOpts.rowModelType == 'enterprise') {
				gridObj.api.setEnterpriseDatasource(dataSource);
			}
	} else {
		// 클라이언트 방식일 경우
		
		$(frm).find('input[name=pageSize]').remove();
		
		$(frm).ajaxSubmit({
			type: 'post'
			, url: gridObj.searchUrl
			, success: function(obj) {
				var idSequence = 0;

				if(obj!=null && obj.list.length > 0) {
    				var data = obj.list;
    				
    				data.forEach( function(item) {
    					item.id = idSequence++;
			        });
				}
    				
				gridObj.api.setRowData(obj.list);
				
				if (callBackFunction != null && callBackFunction != undefined) {
					callBackFunction();
				}
			}, error: function(xhr, status, err) {
				console.log(xhr);
				alert(xhr);
				alert(status);
				alert(err);
			}
		});
	}
	
}

/* ********************
 * 그리드 신규 row 추가
 ******************** */ 
function fnAgGirdNew(){	
	console.clear();
	console.log("=========== fnAgGirdNew Start ===========");
	var args = arguments;
	
	var gridObj = args[0];
	var newRowData = args[1];
	
	gridObj.api.deselectAll();
	
	//newRowData.RNUM=999;
	
	gridObj.api.updateRowData({add: [newRowData], addIndex:0, id:0});
	
	//gridObj.gridOpts.suppressRowClickSelection = true;
	
	
	/*gridObj.api.startEditingCell({
	    rowIndex : 0
	    , colKey : 'RNUM'
	});
	
	gridObj.api.forEachNode(function(node) {
		node.id = node.rowIndex;
		node.data.id = node.rowIndex;
		
		node.setId(node.rowIndex);
		//node.setDataValue('id', node.rowIndex-1);
		
		console.log("node.id :: " + node.id + ", data.id :: " + node.data.id);
		
		if(node.rowIndex == 0) {
			//node.setSelected(node.rowIndex == 0);
			
			gridObj.gridOpts.onRowClicked(node);
			
			
		}
		
		if(node.rowIndex == 1) {
			var obj = node;
			for(var a in obj){
				console.log(a + " -> " + obj[a]);
			}
		}
    });*/
	
	//gridObj.api.selectIndex(0);	//신규행 상단으로 표시
	
	fnAgGirdStatusChg(gridObj, 0);
	
	console.log("=========== fnAgGirdNew End ===========");
}

// 로우상태변경
function fnAgGirdStatusChg(gridObj, idx){	
	/*gridObj.api.forEachNode( function(rowNode, index) {
		//var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+index+"]").find("div[col-id=RNUM]");
		var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]").find("div[col-id=RNUM]");
		
		$("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]").find("div[col-id=RNUM]").find(".grid_row_status_alert").remove();
		
		if(typeof rowNode.data != "undefined") {
			
			if(rowNode.data.STATUS == 'C'){
				rowNode.data.edit = true;
				
				rnum.prepend('<div class="grid_row_status_alert"><small class="label pull-left bg-green">N</small></div>');	// 신규
			} else if(rowNode.data.STATUS == 'U' || idx == index ) {
				rowNode.data.edit = true;
				
				rnum.prepend('<div class="grid_row_status_alert"><small class="label pull-right bg-yellow">U</small></div>');	// 수정
			} else if(rowNode.data.STATUS == 'D'){
				rowNode.data.edit = true;
				
				rnum.prepend('<div class="grid_row_status_alert"><small class="label pull-left bg-green">D</small></div>');	// 삭제
			}
		}
	});*/
			
}

/* ********************
 * 그리드 수정된 row 저장
 ******************** */ 
function fnAgGirdSave(){
	var args = arguments;
	
	var gridObj = args[0];
	var callBackFunction = args[1];
	var successMsg = args[2];
	var async = args[3] != null ? args[3] : true;
	
	gridObj.api.stopEditing(); //수정중인 데이터까지 저장하기 위해 필요.
    
	var updateRows = [];
    setTimeout(function () { //stopEditing이 바로 적용되지 않아 추가.
    	gridObj.api.forEachNode( function(rowNode, index) {
    		if(rowNode.data.edit){
    			updateRows.push(rowNode.data);
    		}
    	});
    
    	var jsonData = JSON.stringify(updateRows);
    	
    	$.ajax({
    		type: 'post'
    		, url: gridObj.saveUrl
    		, dataType: 'json'
    		//, contentType : "application/json; charset=utf-8"
    		, async: async
    		, data: {reqData : jsonData}
    		, success: function(data) {
    			if(successMsg == null){
    				alert('저장되었습니다.');
    			}	else if(successMsg == 'x'){
    				//alert(successMsg);
    			}	else{
    				alert(successMsg);
    			}
    			
    			if (callBackFunction != null && callBackFunction != undefined) {
    				callBackFunction();
    			}
    		}, error: function(xhr, status, err) {
    			//alert('서버 에러');
    			var responseText = xhr.responseText;
    			alert(responseText);
    		}
    	});
    }, 10);
}

/* ********************
 * 그리드 선택된 row 삭제
 ******************** */ 
function fnAgGirdDel(){ 
	var args = arguments;
	
	var gridObj = args[0];
	var callBackFunction = args[1];
	var alertMsg = (args[2] == null) ? "선택된 행을 삭제 하시겠습니까?" : args[2];
	
	//var selectedRows = gridObj.api.getSelectedRows();
	var isSave = false;
	
    
	var isDelInNew = false;
	//var alertMsg = "선택된 행을 바로삭제 하시겠습니까?";
	
	//console.clear();
	//console.log("=========== fnAgGirdDel Start ===========");
	//console.log("gridObj.noPaging :: " + gridObj.noPaging);
	
	
	if(confirm( alertMsg )) {
		if (gridObj.noPaging == null && gridObj.noPaging == undefined) {
			// 서버 방식
			var selectedRows = gridObj.gridOpts.api.getSelectedNodes();
			//console.log("selectedRows.length :: " + selectedRows.length);
			
			selectedRows.forEach( function(selectedRow, index) {
				
				//console.log("서버 방식 index ["+index+"], STATUS [" + selectedRow.data.STATUS + "], selected [" + selectedRow.selected + "]");
				
		    	if(selectedRow.data.STATUS == 'C') {
		    		//selectedRow.data.STATUS = 'DC';
		    		selectedRow.data.STATUS = 'D';
		    	} else if(selectedRow.data.STATUS != 'C' && selectedRow.selected) {
		    		selectedRow.data.STATUS = 'D';
		    	}
		    });
		} else {
			// 클라이언트 방식
			//console.log('클라이언트 방식');
			var selectedRows = gridObj.api.getSelectedRows();
			var removedRows = [];
			
			gridObj.api.forEachNode(function(selectedRow, index) {
				//console.log("클.. index ["+index+"], id [" + selectedRow.id + "], data.id [ " + selectedRow.data.id + " ], data.MENU_NM [ " + selectedRow.data.MENU_NM + "], selected [" + selectedRow.selected + "]");
				
		    	if(selectedRow.selected) {
		    		selectedRow.data.STATUS = 'D'; //삭제상태
		    	}
		    	
		    	//gridObj.api.updateRowData({update: [selectedRow]}); //삭제상태 업데이트
		    	//gridObj.api.updateRowData({remove: [selectedRow]}); //row 삭제
		    	
		    });
			
			//gridObj.api.updateRowData({remove: selectedRows});
		}
		
		fnAgGirdStatusChg(gridObj, null);
	
		isSave = true;
	} else {
		return;
	}
	
	// 예전코드 주석..(클라이언트 방식)
    /*selectedRows.forEach( function(selectedRow, index) {
    	if(selectedRows[0].STATUS != 'C') {
    		isSave = true;
    	}
    	
    	selectedRow.STATUS = 'D'; //삭제상태
    	
    	gridObj.api.updateRowData({update: [selectedRow]}); //삭제상태 업데이트
    	gridObj.api.updateRowData({remove: [selectedRow]}); //row 삭제
    });*/
    
	//return false;
	
    if(isSave) { 
    	var jsonData = JSON.stringify(gridObj.api.getSelectedRows());
    	
    	$.ajax({
    		type: 'post'
    		, url: gridObj.saveUrl
    		, dataType: 'json'
    		//, contentType : "application/json; charset=utf-8"
    		, data: {reqData : jsonData}
    		, success: function(data) {
    			alert('삭제되었습니다.');
    			agGridDelCallBack(gridObj);
    			if (callBackFunction != null && callBackFunction != undefined) {
    				callBackFunction();
    			}
    		}, error: function(xhr, status, err) {
    			alert('서버 에러');
    		}
    	});
    }
    
    //console.log("=========== fnAgGirdDel End ===========");
}

function agGridDelCallBack(gridObj){
	var selectedRows = gridObj.api.getSelectedRows();
	gridObj.api.updateRowData({remove: selectedRows}); //row 삭제
	/*gridObj.api.forEachNode(function(selectedRow, index) {
		if(selectedRow.data.STATUS == "D"){
			gridObj.api.updateRowData({remove: [selectedRow]}); //row 삭제
		}
	});*/
	//console.log("삭제후 처리");
}

/* ********************
 * 그리드 선택된 row 삭제
 ******************** */ 
function fnAgGird_Contract_Del(){
	var args = arguments;
	
	var gridObj = args[0];
	var callBackFunction = args[1];
	var alertMsg = (args[2] == null) ? "선택된 행을 삭제 하시겠습니까?" : args[2];
	
	var isSave = false;
	var isDelInNew = false;
	
	if(confirm( alertMsg )) {
		if (gridObj.noPaging == true) {
			// 서버 방식
			var selectedRows = gridObj.gridOpts.api.getSelectedNodes();
			//console.log("selectedRows.length :: " + selectedRows.length);
			
			selectedRows.forEach( function(selectedRow, index) {
				
				//console.log("서버 방식 index ["+index+"], STATUS [" + selectedRow.data.STATUS + "], selected [" + selectedRow.selected + "]");
		    	if(selectedRow.data.STATUS == 'C') {
		    		//selectedRow.data.STATUS = 'DC';
		    		selectedRow.data.STATUS = 'D';
		    		selectedRow.data.grid_STATUS = 'X';
		    	} else if(selectedRow.data.STATUS != 'C' && selectedRow.selected) {
		    		selectedRow.data.STATUS = 'D';
		    		selectedRow.data.grid_STATUS = 'O';
		    	}
		    });
		} else {
			// 클라이언트 방식
			//console.log('클라이언트 방식');
			var selectedRows = gridObj.api.getSelectedRows();
			var removedRows = [];
			
			gridObj.api.forEachNode(function(selectedRow, index) {
				//console.log("클.. index ["+index+"], id [" + selectedRow.id + "], data.id [ " + selectedRow.data.id + " ], data.MENU_NM [ " + selectedRow.data.MENU_NM + "], selected [" + selectedRow.selected + "]");
				
		    	if(selectedRow.selected) {
		    		selectedRow.data.STATUS = 'D'; //삭제상태
		    	}
		    	
		    	//gridObj.api.updateRowData({update: [selectedRow]}); //삭제상태 업데이트
		    	//gridObj.api.updateRowData({remove: [selectedRow]}); //row 삭제
		    	
		    });
			
			//gridObj.api.updateRowData({remove: selectedRows});
		}
		
		fnAgGirdStatusChg(gridObj, null);
	
		isSave = true;
	} else {
		return;
	}
	
    if(isSave) { 
    	var jsonData = JSON.stringify(gridObj.api.getSelectedRows());
    	
    	$.ajax({
    		type: 'post'
    		, url: gridObj.saveUrl
    		, dataType: 'json'
    		//, contentType : "application/json; charset=utf-8"
    		, data: {reqData : jsonData}
    		, success: function(data) {
    			alert('삭제되었습니다.');
    			
    			if (callBackFunction != null && callBackFunction != undefined) {
    				callBackFunction();
    			}
    		}, error: function(xhr, status, err) {
    			alert('서버 에러');
    		}
    	});
    }
    
    //console.log("=========== fnAgGird_Contract_Del End ===========");
}

function fnAgGirdTotCnt() {
	var args = arguments;	
	
	var gridObj = args[0];
	
	var aj = 0;
	gridObj.api.forEachNode(function(node) {				
		aj = aj + 1;
    });
	return aj;
	
}

/* ********************
 * 그리드 선택 이벤트
 * param1 : grid Object
 * param2 : 선택될 row Index
 ******************** */ 
function fnAgGridRowSelected() {
	var args = arguments;
	
	var gridObj = args[0];
	var rowIndex = args[1];
	
	gridObj.api.selectIndex(rowIndex);
	
	gridObj.api.forEachNode( function(rowNode, index) {
		if(index == rowIndex) gridObj.gridOpts.onRowClicked(rowNode);
	});
}

/* ********************
 * 그리드 행 style 조정
 * param1 : grid Object
 * param2 : 사용화면 고유ID
 ******************** */
function rowSetClass(){
	var args = arguments;
	var gridObj = args[0];
	var menuId = args[1];
	
	gridObj.api.forEachNode(function(rowNode, index) {
		switch(menuId){
			case "entrprsMber" :
	
				if(typeof rowNode.data != "undefined"){
					var expiDays = rowNode.data.EXPI_DAY;
					var oneMerly = (expiDays >= 0 && expiDays <= 30) ? index : -1;
					var twoMerly = (expiDays >= 0 && expiDays <= 60) ? index : -1;
					var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]");
					
					
					/*if(oneMerly == rowNode.rowIndex){
						rnum.addClass("sick-days-breach");
					}
					else if(twoMerly == rowNode.rowIndex){
						rnum.addClass("sick-days-warning");
					}*/
				}
				break;
			case "inbound" :
				if(typeof rowNode.data != "undefined" && rowNode.data.INSTATE_ORG=="TMX011.03"){
					var expiDays = rowNode.data.EXPI_DAY;
					var oneMerly = (expiDays >= 48) ? index : -1;					
					var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]");
					
					if(oneMerly == rowNode.rowIndex){
						rnum.addClass("state-days-inbound");
					}
				}
				break;
			
			
			case "s_grid" :
				if(typeof rowNode.data != "undefined"){
					var EQCK = rowNode.data.EQCK;
					var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]");
					
					if( EQCK != undefined){
						rnum.addClass("eq_check");
						rnum.removeClass("ag-row-even");
						rnum.removeClass("ag-row-odd");
					}
				}
			break;
			
			case "s1_grid" :
				if(typeof rowNode.data != "undefined"){
					var EQCK = rowNode.data.EQCK;
					var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]");
					
					if( EQCK != undefined){
						rnum.addClass("eq_check");
						rnum.removeClass("ag-row-even");
						rnum.removeClass("ag-row-odd");
					}
				}
				break;
			
			case "out_grid" :
				if(typeof rowNode.data != "undefined"){
					var EQCK = rowNode.data.EQCK;
					var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]");
					
					if( EQCK != undefined){
						rnum.addClass("eq_check");
						rnum.removeClass("ag-row-even");
						rnum.removeClass("ag-row-odd");
					}
				}
				break;
			
			case "in_grid" :
				if(typeof rowNode.data != "undefined"){
					var EQCK = rowNode.data.EQCK;
					var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]");
					
					if( EQCK != undefined){
						rnum.addClass("eq_check");
						rnum.removeClass("ag-row-even");
						rnum.removeClass("ag-row-odd");
					}
				}
				break;
				
			case "plan_start_end" :
				if(typeof rowNode.data != "undefined"){
					var start = rowNode.data.ACT_SERSTDT;
					var end = rowNode.data.ACT_SEREDDT;
					var start_yn = (start != '' && start != undefined) ? index : -1;
					var end_yn = (end != '' && end != undefined) ? index : -1;
					var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]");
					
					if(end_yn == rowNode.rowIndex){
						rnum.css("background-color", "#b3f9c3 !important");
					}
					else if(start_yn == rowNode.rowIndex){
						rnum.css("background-color", "#FFC107 !important");
					}
				}
				break;	
			case "misu_grid" :
				if(typeof rowNode.data != "undefined"){
					var rnum = $("div#"+gridObj.gridDiv).find(".ag-row[row-index="+rowNode.rowIndex+"]");
					if(rowNode.data.BAD_PAY_ORD2 == '악성미수'){
						rnum.css("background-color", "#b3f9c3 !important");
					}
					
				}
				break;
		}
    });
}