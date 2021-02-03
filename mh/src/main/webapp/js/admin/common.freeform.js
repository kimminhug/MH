
/******************** 프리폼 CRUD 함수 시작 ********************/

/**
 * 프리폼 조회; 프리폼만 사용 가능....................................................................................................
 */
function fnFreeformSearch() {
	var mv = mobile_check();
	var args = arguments; //Parameters
	var url = args[0];
	var frm = args[1];
	var freefrm = args[2];
	var callbackFunction = args[3];
	var msg = args[4];
	var mask = args[5]!=null?args[5]:false;
	var fileReadOnly = args[6]!=null?args[6]:false;
	var isExFile = args[7]!=null?args[7]:false;
	var fileDiv = args[8];
	var isCallbackDelFile = args[9]!=null?args[9]:"callDeleteFile";

	$("#windowByMask").remove();
	/*
	if (mask && $("#windowByMask").size()==0) {
		$("body").append('<div id="windowByMask"><img src="'+contextPath+'/images/viewLoading.gif" /></div>');
	}*/

	//auto validate check
	//if (AutoValidate($(frm).get(0)) == false) return;
	//if (AutoValidate($(frm).find('input, select, textarea')) == false) return;

	$(frm).ajaxSubmit({
		dataType: 'json'
		, url: url
		, success: function(data) {
			if(mv = "false"){
			  if(mask) wrapWindowByUnMask();
			}
			fnFreeformSetdata(data, freefrm, msg); //Set Data

			fnSetFileList(data, freefrm, fileDiv, fileReadOnly, isExFile, isCallbackDelFile); //Set File
			if (data == null) {
				$(freefrm).find("#STATUS").val('C');
				$(freefrm).find("#IS_ELE_MOD").val('');
			} else {
				$(freefrm).find("#STATUS").val('U');
				$(freefrm).find("#IS_ELE_MOD").val('');
			}
			
			if (callbackFunction != null && callbackFunction != undefined) {
				callbackFunction(data); //User Function Call
			}
		}
		, error: function(xhr, status, err) {
			if(mv = "false"){
			  if(mask) wrapWindowByUnMask();
			}

			//var domain = location.host.split('.')[0];
			var responseText = xhr.responseText;

			if(xhr.status == 500 || xhr.status == 404) {
				responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
				var alertDoc = new DOMParser().parseFromString(responseText, "text/html");
				alert(alertDoc.body.innerHTML);
			} else {
				responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
				responseText = responseText.substring(responseText.indexOf(':') + 1).trim();
				alert(responseText);
			}
		}
		, beforeSubmit: function(formData, jqForm, options) {
			if(mask) wrapWindowByMask();
		}
	});
}
//pc, 모바일구분
function mobile_check() {
		
    // 디바이스 종류 설정
    var pc_device = "win16|win32|win64|mac|macintel";
 
    // 접속한 디바이스 환경
    var this_device = navigator.platform;
 
    if ( this_device ) {
 
        if ( pc_device.indexOf(navigator.platform.toLowerCase()) < 0 ) {	// 모바일
            return "true";
        } else {		// pc
            return "false";
        }
        //return "true";
 
    }
}

/**
 * 프리폼 데이터 셋팅; 프리폼만 사용 가능; Form Element 명과 컬럼 명이 동일한 데이터는 자동 매핑
 */
function fnFreeformSetdata() {
	var args = arguments; //Parameters
	var data = args[0];
	var freefrm = args[1];
	var msg = args[2];

	var elems = fnFreeformClean(freefrm);

	if (data == null) {
		if (msg == null) {
			alert("조회된 데이터가 없습니다.");
		} else if (msg != "") {
			alert(msg); //사용자 메시지 처리
		}
		return;
	}

	$.each(elems, function() {
		var name = this.name;
		for(key in data) {
			//체크박스와 라디오는 별도 처리
			if (this.type == "checkbox") {
				var chkNmSpl = name.split("___");
				if(chkNmSpl[0] == key){
					var chkArr = data[chkNmSpl[0]].split(",");
					
					for(var i = 0; i < chkArr.length; i++) {
						$("."+chkNmSpl[0]).each(function() {
							//console.log($(this).attr("etcVal"));
							if($(this).val() == chkArr[i]){
								$(this).prop("checked",true);
							}
							//etc값이 체크 되어있으면 etc text view
							if($(this).attr("etcVal") == chkArr[i]){
								$('#'+chkNmSpl[0]+'_etc').show();
							}
						});
					}
				}
			}
			
			if (name == key) {
				if (this.type == "checkbox") {
					/*if (data[key] == "Y") {
						this.checked = true;
						this.value = data[key];
					} else {
						this.checked = false;
						this.value = 'N';
					}*/
				} else if (this.type == "radio") {
					//console.log(this.type + ' !!! ' + key + ' @@ ' + this.value);
					//console.log($(this).attr("etcVal"));
					if (this.value == data[key]) {
						this.checked = true;
					} else {
						this.checked = false;
					}
					//etc값이 체크 되어있으면 etc text view
					if($(this).attr("etcVal") == data[key]){
						$('#'+key+'_etc').show();
					}
				} else if ($(this).attr("format") == "number") {
					this.value = setComma(data[key]);
				} else if ($(this).attr("format") == "date") {
					this.value = setDateFormat(data[key], '-');
				} else {
					this.value = data[key];
				}
			}
		}
	});
}

/**
 * 프리폼, 조회조건 초기화
 */
function fnReset() {
	var args = arguments; //Parameters
	var frm = args[0]!=null?args[0]:'#searchForm';
	var msg = args[1];

	var pageSize = "10";

	if ($(frm).find("#pageSize").val() != undefined) {
		pageSize = $(frm).find("#pageSize").val();
	}

	$(frm).get(0).reset();

	$(frm).find(".select2-hidden-accessible").val('').trigger('change');

	if ($(frm).find("#pageSize").val() != undefined) {
		$(frm).find("#pageSize").val(pageSize);
	}

	//fnFreeformClean(frm);
	if (msg == null) {
		alert("초기화 되었습니다.");
	} else if (msg != "") {
		alert(msg); //사용자 메시지 처리
	}
}

/**
 * element 초기화
 * @param freefrm
 */
function fnFreeformClean(freefrm) {
	var elems = $(freefrm).find('input, select, textarea, hidden');
	//console.log($(freefrm));
	$(freefrm).get(0).reset();

	$.each(elems, function() {
		/*
		if (this.type != "radio" && this.type != "button" && this.type != "image" && this.type != "submit") {
			this.value = "";
		}*/
		if (this.type == "hidden") {
			this.value = "";
		}
	});
	var sourceElems = $(freefrm).find('select');
	$.each(sourceElems, function() {
		$(freefrm).find("#"+this.name+" option:eq(0)").attr("selected", "selected");
	});
	return elems;
}

/**
 * 폼의 모든 엘리먼트 Disabled 처리
 * @param frm
 */
function fnFormDisabled(frm) {
	var input = $(frm).find('input');
	$.each(input, function() {
		$(this).attr("disabled", "disabled");
		$(this).addClass("ctl_input_disabled");
	});
	var textarea = $(frm).find('textarea');
	$.each(textarea, function() {
		$(this).attr("disabled", "disabled");
		$(this).addClass("ctl_textarea_disabled");
	});
	var select = $(frm).find('select');
	$.each(select, function() {
		$(this).attr("disabled", "disabled");
		$(this).addClass("ctl_select_disabled");
	});
}
/**
 * 폼의 모든 엘리먼트 Disabled 해제
 * @param frm
 */
function fnFormUnDisabled(frm) {
	var input = $(frm).find('input');
	$.each(input, function() {
		$(this).prop("disabled", false);
		$(this).removeClass("ctl_input_disabled");
	});
	var textarea = $(frm).find('textarea');
	$.each(textarea, function() {
		$(this).prop("disabled", false);
		$(this).removeClass("ctl_textarea_disabled");
	});
	var select = $(frm).find('select');
	$.each(select, function() {
		$(this).prop("disabled", false);
		$(this).removeClass("ctl_select_disabled");
	});
}

/**
 * 폼의 특정 엘리먼트 Disabled 처리
 * @param frm
 */
function fnFormElementsDisabled(frm, elements) {
	var input = $(frm).find('input');
	$.each(input, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).attr("disabled", "disabled");
			$(this).addClass("ctl_input_disabled");
		}
	});
	var textarea = $(frm).find('textarea');
	$.each(textarea, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).attr("disabled", "disabled");
			$(this).addClass("ctl_textarea_disabled");
		}
	});
	var select = $(frm).find('select');
	$.each(select, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).attr("disabled", "disabled");
			$(this).addClass("ctl_select_disabled");
		}
	});
}

/**
 * 폼의 특정 엘리먼트 Disabled 해제
 * @param frm
 */
function fnFormElementsEnabled(frm, elements) {
	var input = $(frm).find('input');
	$.each(input, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).removeAttr("disabled");
			$(this).removeClass("ctl_input_disabled");
		}
	});
	var textarea = $(frm).find('textarea');
	$.each(textarea, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).removeAttr("disabled");
			$(this).removeClass("ctl_textarea_disabled");
		}
	});
	var select = $(frm).find('select');
	$.each(select, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).removeAttr("disabled");
			$(this).removeClass("ctl_select_disabled");
		}
	});
}

/**
 * 폼의 모든 엘리먼트 Readonly 처리
 * @param frm
 */
function fnFormReadonly(frm) {
	var input = $(frm).find('input');
	$.each(input, function() {
		if(this.type == "radio" || this.type == "checkbox") {
			/*$(this).attr("disabled", "disabled");
			$(this).addClass("ctl_input_disabled");*/
			
			$(this).attr("readonly", "readonly");
			$(this).addClass("ctl_select_disabled");
			$(this).not(":checked").attr("disabled", "disabled");
			//$(this).unbind("click");
			$(this).on("click", function(e){
				e.preventDefault();
			});
		} else {
			$(this).attr("readonly", "readonly");
			$(this).addClass("ctl_input_disabled");
			$(this).datepicker("disable").removeAttr("disabled");
		}
	});
	var textarea = $(frm).find('textarea');
	$.each(textarea, function() {
		$(this).attr("readonly", "readonly");
		$(this).addClass("ctl_textarea_disabled");
	});
	var select = $(frm).find('select');
	$.each(select, function() {
		$(this).attr("readonly", "readonly");
		$(this).addClass("ctl_select_disabled");
		$("option", this).not(":selected").attr("disabled", "disabled");
	});
}
/**
 * 폼의 모든 엘리먼트 Readonly 해제
 * @param frm
 */
function fnFormUnReadonly(frm) {
	var input = $(frm).find('input');
	$.each(input, function() {
		if(this.type == "radio" || this.type == "checkbox") {
			/*$(this).prop("disabled", false);
			$(this).removeClass("ctl_input_disabled");*/
			$(this).removeClass("ctl_select_disabled");
			$(this).removeAttr("readonly", "readonly");
			$(this).removeAttr("disabled", "disabled");
			$(this).unbind("click");
		} else {
			$(this).prop("readonly", false);
			$(this).removeClass("ctl_input_disabled");
			$(this).datepicker("enable").removeAttr("disabled");
		}
	});
	var textarea = $(frm).find('textarea');
	$.each(textarea, function() {
		$(this).prop("readonly", false);
		$(this).removeClass("ctl_textarea_disabled");
	});
	var select = $(frm).find('select');
	$.each(select, function() {
		$(this).removeAttr("readonly");
		$(this).removeClass("ctl_select_disabled");
		$("option", this).removeAttr("disabled");
	});
}
/**
 * 폼의 input, textarea 엘리먼트 Readonly 처리
 * @param frm
 */
function fnFormElementsReadonly(frm, elements) {
	var input = $(frm).find('input');
	$.each(input, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			if(this.type == "radio" || this.type == "checkbox") {
				/*$(this).attr("disabled", "disabled");
				$(this).addClass("ctl_input_disabled");*/
				$(this).attr("readonly", "readonly");
				$(this).addClass("ctl_select_disabled");
				$(this).not(":checked").attr("disabled", "disabled");

				$(this).on("click", function(e){
					e.preventDefault();
				});
				
			} else {
				$(this).attr("readonly", "readonly");
				$(this).addClass("ctl_input_disabled");
				$(this).datepicker("disable").removeAttr("disabled");
			}
		}
	});
	var textarea = $(frm).find('textarea');
	$.each(textarea, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).attr("readonly", "readonly");
			$(this).addClass("ctl_textarea_disabled");
		}
	});
	var select = $(frm).find('select');
	
	$.each(select, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).attr("readonly", "readonly");
			$(this).addClass("ctl_select_disabled");
			$("option", this).not(":selected").attr("disabled", "disabled");
		}
	});
}
/**
 * 폼의 input, textarea 엘리먼트 Readonly 해제
 * @param frm
 */
function fnFormElementsNormal(frm, elements) {
	var input = $(frm).find('input');
	$.each(input, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			if(this.type == "radio" || this.type == "checkbox") {
				/*$(this).prop("disabled", false);
				$(this).removeClass("ctl_input_disabled");*/
				$(this).removeClass("ctl_select_disabled");
				$(this).removeAttr("readonly", "readonly");
				$(this).removeAttr("disabled", "disabled");
				$(this).unbind("click");
			} else {
				$(this).prop("readonly", false);
				$(this).removeClass("ctl_input_disabled");
				$(this).datepicker("enable").removeAttr("disabled");
			}
		}
	});
	var textarea = $(frm).find('textarea');
	$.each(textarea, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).removeAttr("readonly");
			$(this).removeClass("ctl_textarea_disabled");
		}
	});
	var select = $(frm).find('select');
	$.each(select, function() {
		if(jQuery.inArray(this.name, elements) >= 0) {
			$(this).removeAttr("readonly");
			$(this).removeClass("ctl_select_disabled");
			$("option", this).removeAttr("disabled");
		}
	});
}

/**
 * 프리폼 신규; 프리폼만 사용 가능
 */
function fnFreeformNew() {
	var args = arguments; //Parameters
	var freefrm = args[0];
	var callbackFunction = args[1];
	var msg = args[2];

	fnFreeformClean(freefrm);

	if (msg == null) {

	} else if (msg != "") {
		alert(msg); //사용자 메시지 처리
	}

	$(freefrm).find("#STATUS").val('C');
	$(freefrm).find("#IS_ELE_MOD").val('');
	$(freefrm).find("#comFileList").text('');

	if (callbackFunction != null && callbackFunction != undefined) {
		callbackFunction(); //User Function Call
	}
}

/**
 * 프리폼 삭제; 프리폼만 사용 가능
 */
function fnFreeformDel() {
	var mv = mobile_check();
	var args = arguments; //Parameters
	var url = args[0];
	var freefrm = args[1];
	var callbackFunction = args[2];
	var msg = args[3];
	var confirmMsg = args[4];
	var mask = args[5]!=null?args[5]:false;

	/*if (mask && $("#windowByMask").size()==0) {
		$("body").append('<div id="windowByMask"><img src="'+contextPath+'/images/viewLoading.gif" /></div>');
	}*/

	//신규상태는 삭제할 데이터가 없으므로 이벤트 중지.
	if($(freefrm).find("#STATUS") != null && $(freefrm).find("#STATUS").val() == "C"){
		//fnFreeformClean(freefrm);
		return;
	}

	//사용자 정의 메시지가 없을 때
	if(confirmMsg == null || confirmMsg == ""){
		confirmMsg = "정말로 삭제하시겠습니까?";
	}

	if(confirm(confirmMsg)) {
		$(freefrm).find("#STATUS").val('D');
		$(freefrm).find("#IS_ELE_MOD").val('');

		//fnFormatUnmask($(freefrm));

		$(freefrm).ajaxSubmit({
			dataType: 'json'
			, url: url
			, success: function(data){
				if(mv = "false"){
				  if(mask) wrapWindowByUnMask();
				}
				if(data.result == 'success') {
					if (msg == null) {
						alert("삭제되었습니다");
					} else if (msg != "") {
						alert(msg.trim()); //사용자 메시지 처리
					}
					fnFreeformClean(freefrm);
					$(freefrm).find("#comFileList").text('');

					if (callbackFunction != null && callbackFunction != undefined) {
						callbackFunction(data); //User Function Call
					}
				} else {
					alert(data.message.trim());
				}
			}
			, error: function(xhr, status, err) {
				if(mv = "false"){
				  if(mask) wrapWindowByUnMask();
				}

				//var domain = location.host.split('.')[0];
				var responseText = xhr.responseText;

				if(xhr.status == 500 || xhr.status == 404) {
					responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
					var alertDoc = new DOMParser().parseFromString(responseText, "text/html");
					alert(alertDoc.body.innerHTML);
				} else {
					responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
					responseText = responseText.substring(responseText.indexOf(':') + 1).trim();
					alert(responseText);
				}
			}
		});
	}
}

/**
 * 프리폼 저장; 프리폼만 사용 가능
 * 저장URL, #프리폼아이디, 콜백함수, 메세지, 처리중마스크
 */
function fnFreeformSave() {
	
	var mv = mobile_check();
	var args = arguments; //Parameters
	var url = args[0];
	var freefrm = args[1];
	var callbackFunction = args[2];
	var msg = args[3];
	var mask = args[4]!=null?args[4]:false;
	var confrmMsg = args[5]!=null?args[5]:false;
	/*if (mask && $("#windowByMask").length==0) {
		wrapWindowByMask();
	}*/

	//auto validate check
	//if (AutoValidate($(freefrm).get(0)) == false) return;
	if (AutoValidate($(freefrm).find('input, select, textarea')) == false){ wrapWindowByUnMask(); return;}
	if(confrmMsg == 'x'){

	}else if(confrmMsg){
		if(!confirm(confrmMsg)){ wrapWindowByUnMask(); return false;}
	}else{
		if(!confirm("저장 하시겠습니까?")){ wrapWindowByUnMask(); return false;}
	}
	fnFormatUnmask($(freefrm));

	/*
	var elems = $(freefrm).find('input, select, textarea');
	$.each(elems, function() {
		if ((this.tagName == "input" && this.type == "text" || this.type == "hidden") || this.tagName == "textarea") {
			this.value = this.value.replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/\n/g, '<br />');
		}
	});*/
	$(freefrm).ajaxSubmit({
		dataType: 'json'
		, url: url
		, success: function(data){
			//$(freefrm).find("#comFileList").children().remove();
			if(mv == "false"){
			  if(mask) wrapWindowByUnMask();
			}
			if(data.result == 'success') {
				if (msg == null) {
					alert(data.message.trim());
				} else if (msg != "") {
					if(msg != "empty"){
						alert(msg.trim()); //사용자 메시지 처리
					}
				}

					/*fileUpFlag는 프리폼을 저장호출을 성공적으로 하고 난 뒤 파일저장양식이 2개라 이를 구분하기 위해 사용한다. 2014-06-18 추가*/
					/*if(fileUpFlag == 'Y')
					{
						$(freefrm).find("#comFileList").parent().children(".ctl_input_file").remove();

						 기존 comfileupload1,2 에서 파일 내용이 통합되면서 분기되던 부분이 통합됨 2014-06-18 
						if($("#btnMultiFileDn").val() == undefined)	{
							$(freefrm).find("#comFileList").before('<input type="file" id="comFileSearch" name="file_0" title="파일찾기" alt="파일찾기" class="ctl_input" size="34"  multiple="multiple"/>');
						}
						else if($("#btnMultiFileDn").val() != undefined){
						}
						 기존 comfileupload1,2 에서 파일 내용이 통합되면서 분기되던 부분이 통합됨 2014-06-18 

						$(freefrm).find("#btnMultiFileDn").before('<input type="file" id="comFileSearch" name="file_0" title="파일찾기" alt="파일찾기" class="ctl_input" size="34"  multiple="true"/>');

					}
					else if(fileUpFlag != 'Y' && $(freefrm).find("input[type=file]").length != 0)
					{
						$(freefrm).find("input[type=file]").remove();
						if($(freefrm).find("#btnMultiFileDn").val() !=  null)
						{
							$(freefrm).find("#btnMultiFileDn").before('<input type="file" id="comFileSearch" name="file_0" title="파일찾기" alt="파일찾기" class="ctl_input" size="34"  multiple="true"/>');
						}
						else
						{
							//$(freefrm).find("td:eq(2)").html('<input type="file" id="comFileSearch" name="file_1" title="파일찾기" alt="파일찾기" class="ctl_input_file" size="34" />');
							$(freefrm).find("#comFileList").before('<input type="file" id="comFileSearch" name="file_0" title="파일찾기" alt="파일찾기" class="ctl_input" size="34"  multiple="true"/>');
						}
					}
					fnDrowfileUpload2();
					$(freefrm).find("#comFileList").text('');
*/
				if (callbackFunction != null && callbackFunction != undefined) {
					callbackFunction(data); //User Function Call
				}
			} else {
				alert(data.message.trim());
			}
			$(freefrm).find("#IS_ELE_MOD").val('');
		}
		, error: function(xhr, status, err) {
			if(mv = "false"){
			  if(mask) wrapWindowByUnMask();
			}

			//var domain = location.host.split('.')[0];
			var responseText = xhr.responseText;

			if(xhr.status == 500 || xhr.status == 404) {
				responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
				var alertDoc = new DOMParser().parseFromString(responseText, "text/html");
				alert(alertDoc.body.innerHTML);
			} else {
				responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
				responseText = responseText.substring(responseText.indexOf(':') + 1).trim();
				alert(responseText);
			}
		}
		, beforeSubmit: function(formData, jqForm, options) {
			if(mv=="false") {
				if(mask) wrapWindowByMask();
			}
			
		}
	});
}

/**
 * 프리폼 항목 수정시 상태값 변경 keydown 이벤트 처리
 */
$(document).keydown(function(e) {
	var element = $(e.target);

	if (element.is("input[type=text]") || element.is("input[type=password]") || element.is("select") || element.is("textarea")) {
		// 2017.7.28 조현준
		// 속성이 readonly 혹은 disabled에서는 수정이 일어날 수 없기에 IS_ELE_MOD 변경을 방지
		if( (element.prop('readonly') == false && element.prop('disabled') == false)
				|| (element.prop('readonly') == undefined && element.prop('disabled') == false) ) {
			setFreeformIsEleMod(element);
		}
	}
	
});
$(document).keyup(function(e) {
	var element = $(e.target);
	//숫자 타입 입력시 세자리 콤마
	if (element.is("input[format=number]")){
		var eleVal = remove_comma(element.val());

		if(isNumric(eleVal)==false){
			alert("숫자만 입력 해주세요.");
			eleVal = eleVal.replace(/[^0-9]/g,''); 
			//return;
		}
		element.val(setComma(eleVal));
	}
});

/**
 * 프리폼 항목 수정시 상태값 변경 change 이벤트 처리
 */
$(document).change(function(e) {
	var element = $(e.target);
	if (element.is("input[type=text]") || element.is("input[type=password]") || element.is("input[type=file]") ||
			element.is("input[type=checkbox]") || element.is("input[type=radio]") || element.is("select") || element.is("textarea")) {

		setFreeformIsEleMod(element);
	}
});

/**
 * 프리폼 항목 수정시 상태값 변경
 * @param e
 */
function setFreeformIsEleMod(element) {
	var freefrm = element.closest("form");
	if (freefrm.length == 0) return false;
	if ($(freefrm).find("#IS_ELE_MOD").length == 0) return false;
	if ($(freefrm).find("#IS_ELE_MOD").val() == "") {
		$(freefrm).find("#IS_ELE_MOD").val('Y');
	}
}

/**
 * 프리폼 항목 수정 여부(Y|빈값) 얻기
 * @param freefrm
 * @returns String
 */
function getFreeformIsEleMod(freefrm) {
	return $(freefrm).find("#IS_ELE_MOD").val();
}

/**
 * 프리폼 상태 값(C|U|D) 얻기
 * @param freefrm
 * @returns String
 */
function getFreeformStatus(freefrm) {
	return $(freefrm).find("#STATUS").val();
}

/**
 * 처리중 윈도우 마스크 처리
 */
function wrapWindowByMask() {
	var args = arguments; //Parameters
	var windowByMask = args[0] == null ? '#windowByMask' : args[0];
	
	if ($(windowByMask).length==0) {
		/*$("body").append('<div id="windowByMask"><img src="/images/viewLoading.gif" /></div>');*/
		$("body").append('<div id="windowByMask"><button type="button" class="btn btn-refresh btn-lg" onClick="window.location.reload()" style="font-size: 1.5rem; color: #fff;"><i class="fa fa-redo-alt" style="font-size: 5rem"></i><br>새로고침</button></div>');
	}
	//화면의 높이와 너비를 구한다.
	var maskHeight = $(document).height();
	var maskWidth = $(window).width();

	//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
	$(windowByMask).css({'width':maskWidth, 'height':maskHeight , 'z-index':99999});

	//애니메이션 효과 - 일단 1초동안 까맣게 됐다가 80% 불투명도로 간다.
	$(windowByMask).show();
	//$(windowByMask).fadeIn(1);
	//$(windowByMask).fadeTo("slow", 0.9);
}

function wrapWindowByMaskStart() {
	$("body").css({'overflow':'hidden'});
	var args = arguments; //Parameters
	var windowByMask = args[0] == null ? '#windowByMask' : args[0];
	
	if ($(windowByMask).length==0) {
		$("body").append('<div id="windowByMask" ></div>');
		// <button id="workstart" style="margin:20% auto; width:auto; height:6%; font-size:3rem; background: #01B2D8; padding: 1rem 3rem; border: none; border-radius: 2rem; color: #fff;" onclick="workStartAfter()">업무시작</button>
	}
	//화면의 높이와 너비를 구한다.
	var maskHeight = $(document).height();
	var maskWidth = $(window).width();

	//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
	$(windowByMask).css({'width':maskWidth, 'height':maskHeight});

	//애니메이션 효과 - 일단 1초동안 까맣게 됐다가 80% 불투명도로 간다.
	$(windowByMask).show();
	//$(windowByMask).fadeIn(1);
	//$(windowByMask).fadeTo("slow", 0.9);
}

/**
 * 처리중 윈도우 마스크 해제
 */
function wrapWindowByUnMask() {
	var args = arguments; //Parameters
	var windowByMask = args[0] == null ? '#windowByMask' : args[0];
	
	$(windowByMask).hide();
	
	if ($(windowByMask).length>=1) {
		$(windowByMask).remove();
	}
}

/******************** 프리폼 CRUD 함수 종료 ********************/

/**
 * Ajax통신(json); 프리폼만 사용 가능
 * 저장URL, #프리폼아이디, 콜백함수, 메세지, 처리중마스크
 */
function fnAjaxSubmit() {
	var mv = mobile_check();
	var args = arguments; //Parameters
	var url = args[0];
	var frm = args[1];
	var callbackFunction = args[2];
	var msg = args[3];
	var mask = args[4]!=null?args[4]:false;

	/*if (mask && $("#windowByMask").size()==0) {
		$("body").append('<div id="windowByMask"><img src="'+contextPath+'/images/viewLoading.gif" /></div>');
	}*/

	//if (AutoValidate($(freefrm).find('input, select, textarea')) == false) return;

	//fnFormatUnmask($(frm));

	$(frm).ajaxSubmit({
		dataType: 'json'
		, url: url
		, success: function(data){
			if(mv = "false"){
			  if(mask) wrapWindowByUnMask();
			}
			if (msg == null) {
				if (data != null && data.message != null && data.message != undefined) {
					alert(data.message.trim()); //성공 메시지 처리
				}
			} else if (msg != "") {
				// 자바에서 올라오는 BusinessException을 먼저 체크한 후 사용자메세지를 체크한다.
				if (data != null && data.message != null && data.message != undefined) {
					alert(data.message.trim());
				} else	alert(msg.trim()); //사용자 메시지 처리
			}
			if (callbackFunction != null && callbackFunction != undefined) {
				callbackFunction(data); //User Function Call
			}
		}
		, error: function(xhr, status, err) {
			if(mv = "false"){
			  if(mask) wrapWindowByUnMask();
			}

			//var domain = location.host.split('.')[0];
			var responseText = xhr.responseText;

			if(xhr.status == 500 || xhr.status == 404) {
				responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
				var alertDoc = new DOMParser().parseFromString(responseText, "text/html");
				alert(alertDoc.body.innerHTML);
			} else {
				responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
				responseText = responseText.substring(responseText.indexOf(':') + 1).trim();
				alert(responseText);
			}
		}
		, beforeSubmit: function(formData, jqForm, options) {
			if(mask) wrapWindowByMask();
		}
	});
}

/**
 * Ajax배치(json); 배치 progress bar 이미지 처리
 */
function fnAjaxBatch() {
	var args = arguments; //Parameters
	var url = args[0];
	var frm = args[1];
	var callbackFunction = args[2];
	var msg = args[3];

	$(frm).ajaxSubmit({
		dataType: 'json'
		, url: url
		, success: function(data){
			if (msg == null) {
				if (data.message != null && data.message != undefined) {
					alert(data.message.trim()); //성공 메시지 처리
				}
			} else if (msg != "") {
				alert(msg.trim()); //사용자 메시지 처리
			}
			if (callbackFunction != null && callbackFunction != undefined) {
				callbackFunction(data); //User Function Call
			}
			//$('#viewLoading').fadeOut(500);
		}
		, error: function(xhr, status, err) {
			//var domain = location.host.split('.')[0];
			var responseText = xhr.responseText;

			if(xhr.status == 500 || xhr.status == 404) {
				responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
				var alertDoc = new DOMParser().parseFromString(responseText, "text/html");
				alert(alertDoc.body.innerHTML);
			} else {
				responseText = responseText.replace('{', '').replace('}', '').replace(/[\"]/g,'');
				responseText = responseText.substring(responseText.indexOf(':') + 1).trim();
				alert(responseText);
			}
		}
		, beforeSubmit: function(formData, jqForm, options) {
			/*
			var leftpos = $(window).width() / 2;
			var toppos = $(window).height() / 2;
			$('#viewLoading').css('position', 'absolute');
			$('#viewLoading').css('left', leftpos);
			$('#viewLoading').css('top', toppos);
			$('#viewLoading').css('z-index', 100);
			$('#viewLoading').show().fadeIn(500);*/
		}
	});
}

/******************** 프리폼 마스크 함수 시작 ********************/

//숫자 타입 포멧터 정의 (기본:천단위 콤마); 프리폼만 사용 가능
function fnMaskNumber() {
	var args = arguments;

	var idSelector = args[0]!=null?args[0]:'';
	var prefix = args[1]!=null?args[1]:'';
	var sufix = args[2]!=null?args[2]:'';
	var thousandsSeparator = args[3]!=null?args[3]:',';
	var limit = args[4]!=null?args[4]:0;
	var centsSeparator = args[5]!=null?args[5]:'';
	var centsLimit = args[6]!=null?args[6]:0;

	var maxlength = $(idSelector).attr("maxlength");
	if (maxlength != null){
		$(idSelector).attr("maxlength","");
		limit = maxlength;
	}

	//(ID Selector, prefix 문자, sufix 문자, 천단위 문자, 숫자 총 길이 제한, 소수 문자, 소수 자리수)
	$(idSelector).priceFormat({
	    prefix: prefix, //prefix 문자
	    sufix: sufix, //sufix 문자
	    thousandsSeparator: thousandsSeparator, //천단위 문자
	    limit: limit, //숫자 총 길이 제한 (0일 경우 무제한)
	    centsSeparator: centsSeparator, //소수 문자
	    centsLimit: centsLimit, //소수 자리수
	    allowNegative: false,
	    clearPrefix: true
	});
	$(idSelector).attr("format", "number");
}

//숫자 타입 포멧터 제거; 프리폼만 사용 가능
function fnUnMask() {
	var args = arguments;
	var obj = $(args[0]);

	var result = "";
	for(var i=1; i<args.length; i++) {
		var str = eval("/"+args[i]+"/g");
		result = obj.val().replace(str,"");
	}

	return result;
}

/******************** 프리폼 마스크 함수 종료 ********************/

/**
 * 브라우저별 input[type=file] 초기화
 * @param selector
 */
function initFileTypeVal(selector){
	var browserInfo = getBrowser();
	//IE 일때 input[type=file] 초기화
	
	if (browserInfo.msie) {
		$(selector).replaceWith( $(selector).clone(true) );

		//Other Browser 일때 input[type=file] 초기화
	} else {
		$(selector).val("");
	}
	
	/*if ($.browser.msie) {
		//IE 9,10 버전 문제로 초기화 방법 변경
		if($.browser.version > 8.0) {
			selector.type = 'text';
			selector.type = 'file';
		} else {
			$(selector).replaceWith( $(selector).clone(true) );
		}
	//Other Browser 일때 input[type=file] 초기화
	} else {
		$(selector).val("");
	}*/
}

function getBrowser(){
	var agent = navigator.userAgent.toLowerCase();
	var obj = {};
	var version = -1;
	if ( (navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1)) {
	     // ie일 경우
		obj.msie = true;
	}else{
	     // ie가 아닐 경우
		obj.msie = false;
	}
	return obj;
}

function getMobileDevice() {
    var mobileKeyWords = new Array('Android', 'iPhone', 'iPod', 'BlackBerry', 'Windows CE', 'SAMSUNG', 'LG', 'MOT', 'SonyEricsson');
    for (var info in mobileKeyWords) {
        if (navigator.userAgent.match(mobileKeyWords[info]) != null) {
            return true;
        }
    }
    return false;
}

/**
 * LinkedSelectBox
 * fnLinkedSelectBox("select아이디1", "select아이디2", "select아이디3");
 */
function fnLinkedSelectBox() {
	var args = arguments;
	var len = args.length-1;

	for(var i=1; i<len; i++) {
		var masterSelector = args[i];
		var detailSelector = args[i+1];
		//alert(masterSelector+"/"+detailSelector);
		fnLinkedSelectBoxChange(masterSelector, detailSelector, args);
	}
}


/**
 * LinkedSelectBox Change Event
 * @param masterSelector
 * @param detailSelector
 * @param args
 */
function fnLinkedSelectBoxChange(masterSelector, detailSelector, args) {
	$("#"+masterSelector).bind('change', function() {
		var topLabel = args[0]!=null?args[0]:"전체";
		var curValue = $(this).val();
		var data = eval(detailSelector).split(";");
		var isClear = false;
		for(var i=0; i<args.length; i++) {
			if (args[i] == detailSelector) {
				isClear = true;
			}
			if (isClear) {
				$("#"+args[i]).html("");
				$("#"+args[i]).append('<option value="">'+topLabel+'</option>');
			}
		}
		$.each(data, function(){
			var row = this.split(":");
			if (curValue == row[0] && curValue != "") {
				$("#"+detailSelector).append('<option value="'+row[1]+'">'+row[2]+'</option>');
			}
		});
	});
}


function fnLinkedSelectBoxFrm() {
	var args = arguments;
	var len = args.length-1;

	var deaultValArr = null;

	if(args[0]!=null){
		deaultValArr = args[0].split(";");
	}

	for(var i=2; i<len; i++) {
		var frmSelector = args[1];
		var masterSelector = args[i];
		var detailSelector = args[i+1];

		var argsParam = args;

		if(args[0]!=null){
			argsParam[0] = deaultValArr[i-2];
		}

		fnLinkedSelectBoxChangeFrm(frmSelector, masterSelector, detailSelector, argsParam);
	}
}

function fnLinkedSelectBoxChangeFrm(frmSelector, masterSelector, detailSelector, args) {
	$("#"+frmSelector).find("#"+masterSelector).bind('change', function() {
		var topLabel = args[0]!=null?args[0]:"- 전체 -";
		var curValue = $(this).val();
		var data = eval(detailSelector).split(";");
		var isClear = false;
		for(var i=0; i<args.length; i++) {
			if (args[i] == detailSelector) {
				isClear = true;
			}
			if (isClear) {
				$("#"+frmSelector).find("#"+args[i]).html("");
				$("#"+frmSelector).find("#"+args[i]).append('<option value="">'+topLabel+'</option>');
			}
		}
		$.each(data, function(){
			var row = this.split(":");
			if (curValue == row[0] && curValue != "") {
				$("#"+frmSelector).find("#"+detailSelector).append('<option value="'+row[1]+'">'+row[2]+'</option>');
			}
		});
	});
}

//ReplaceAll함수 : replaceAll(대상문자열, 식별자, 치환자)
function replaceAll(str, searchStr, replaceStr) {
	return String(str).split(searchStr).join(replaceStr);
 }

//숫자타입 3자리 콤마
function setComma(str) {
    str = ""+str+"";
    var retValue = "";
    for(var i=0; i<str.length; i++) {
		if(i > 0 && (i%3)==0) {
			retValue = str.charAt(str.length - i -1) + "," + retValue;
		} else {
			retValue = str.charAt(str.length - i -1) + retValue;
		}
    }
    return retValue;
}

//콤마.,-자동삭제
function commonReplace(obj) {
	var objVal="";
	if (isObject(obj)) {
		objVal = $(obj).val();
	} else {
		objVal = obj;
	}

	return objVal.replace(/\//g,"").replace(/-/g,"").replace(/\./g,"").replace(/\,/g,"");
}

function remove_comma(obj) {
	//$("#"+pay).val($("#"+pay).val().replace(/,/gi, ''));
	var objVal="";
	if (isObject(obj)) {
		objVal = $(obj).val();
	} else {
		objVal = obj;
	}
	return objVal.replace(/,/gi, '');
}
/**
 * 천단위 콤마, 달력 하이픈 제거
 *
 * @param obj
 */
function fnFormatUnmask(obj) {
	$.each(obj.find("input[format=integer], input[format=number]"), function() {
		$(this).val($(this).val().replace(/\,/gi, ""));
	});
	$.each(obj.find("input[format=date]"), function() {
		$(this).val($(this).val().replace(/\-/gi, ""));
	});
}
//사용자 정의 달력 포멧(8자리, 10자리, 구분자); 그리드/프리폼 모두 사용 가능
function setDateFormat(cellValue, separator) {
	var str = "";
	if (cellValue != undefined && cellValue != "") {
		if (cellValue.indexOf(separator) != -1) {
			str = cellValue;
		} else if (cellValue.length == 8) {
			str = cellValue.substring(0, 4) + separator
					+ cellValue.substring(4, 6) + separator
					+ cellValue.substring(6, 8);
		} else if (cellValue.length == 10) {
			str = cellValue.substring(0, 4) + separator
					+ cellValue.substring(5, 7) + separator
					+ cellValue.substring(8, 10);
		}
	}
	return str;
}
//우편번호 검색
function execDaumPostcode() {
	var args = arguments;
	var zipNm = args[0]!=null?args[0]:"ZIP";	//tag id 값
	var addrNm = args[1]!=null?args[1]:"ADDRESS";	//tag id 값
	
	var mv = mobile_check();
	
	if(mv=="false"){
		$("body").append('<div class="modal" id="daumWrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="border:1px solid #DDDDDD;width:800px;margin-top:5px"><img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼"></div>'); 
		var element_layer = document.getElementById("daumWrap");
		
		$("#daumWrap").show();
	}else{
		var element_layer = document.getElementById("daumWrap");
		
		$("#daumWrap").modal();
		$("#daumWrap").show();
	}

	new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
        	$('#SIDO').val(data.sido);
            $('#SIGUNGU').val(data.sigungu);
            $('#BNAME').val(data.bname);

        	var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            if(data.userSelectedType == "R"){	//도로명
            	// 우편번호와 주소 정보를 해당 필드에 넣는다.
            	$('#'+zipNm).val(data.zonecode);
            	$('#'+addrNm).val(roadAddr);
            	//$('#ADDRESS').val(data.jibunAddress);
            }else if(data.userSelectedType == "J"){	//지번
            	$('#'+zipNm).val(data.zonecode);
            	$('#'+addrNm).val(data.jibunAddress);
            	//$('#ADDRESS').val(roadAddr);
            }
            element_layer.style.display = 'none';
        },
        width : '100%',
        height : '100%'
    }).embed(element_layer);

    // iframe을 넣은 element를 보이게 한다.
    element_layer.style.display = 'block';

    // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
    initLayerPosition();    

}

function initLayerPosition(){
	var mv = mobile_check();
	var element_layer = document.getElementById("daumWrap");
    var width = (mv=="false")?700:300; //우편번호 서비스가 들어갈 element의 width
    var height = 460; //우편번호 서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    element_layer.style.width = width + 'px';
    element_layer.style.height = height + 'px';
    element_layer.style.border = borderWidth + 'px solid';
    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
    element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
}

function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
	var element_layer = document.getElementById("daumWrap");
    element_layer.style.display = 'none';
}



/**
 * 좌측문자열채우기
 * @params
 *  - str : 원 문자열
 *  - padLen : 최대 채우고자 하는 길이
 *  - padStr : 채우고자하는 문자(char)
 */
function lpad(str, padLen, padStr) {
    if (padStr.length > padLen) {
        console.log("오류 : 채우고자 하는 문자열이 요청 길이보다 큽니다");
        return str;
    }
    str += ""; // 문자로
    padStr += ""; // 문자로
    while (str.length < padLen)
        str = padStr + str;
    str = str.length >= padLen ? str.substring(0, padLen) : str;
    return str;
}

/**
 * 우측문자열채우기
 * @params
 *  - str : 원 문자열
 *  - padLen : 최대 채우고자 하는 길이
 *  - padStr : 채우고자하는 문자(char)
 */
function rpad(str, padLen, padStr) {
    if (padStr.length > padLen) {
        console.log("오류 : 채우고자 하는 문자열이 요청 길이보다 큽니다");
        return str + "";
    }
    str += ""; // 문자로
    padStr += ""; // 문자로
    while (str.length < padLen)
        str += padStr;
    str = str.length >= padLen ? str.substring(0, padLen) : str;
    return str;
}

/******************** 프리폼 TAG Library 함수 시작 ********************/
/**
 * tag 체크박스 사용 함수
 */
function fnCboxChgVal(){
	var args = arguments;
	var _this = args[0];
	var idName = args[1];
	var checks = [];
	var idNm = $('.'+idName);
	
	for(var a=0; a<idNm.length; a++){
		var etcVal = idNm[a].getAttribute('etcVal');
		if(idNm[a].checked == true){ checks.push(idNm[a].value); }
		if(idNm[a].checked == true && etcVal == idNm[a].value){
			$('#'+idName+'_etc').show();
		}else{
			$('#'+idName+'_etc').hide();
		}
	}
	
	$('#'+idName).val(checks);
}
/**
 * tag 라디오박스 사용 함수
 */
function fnRboxChgVal(){
	var args = arguments;
	var _this = args[0];
	var idName = args[1];
	var idNm = $('.'+idName);
	
	for(var a=0; a<idNm.length; a++){
		var etcVal = idNm[a].getAttribute('etcVal');

		if(idNm[a].checked == true && etcVal == idNm[a].value){
			$('#'+idName+'_etc').show();
		}else{
			$('#'+idName+'_etc').hide();
		}
	}
}
