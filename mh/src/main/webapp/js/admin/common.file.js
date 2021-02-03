/**
 * Convert a single file-input element into a 'multiple' input list
 * Usage:
 *
 *   1. Create a file input element (no name)
 *      eg. <input type="file" id="first_file_element">
 *
 *   2. Create a DIV for the output to be written to
 *      eg. <div id="files_list"></div>
 *
 *   3. Instantiate a MultiSelector object, passing in the DIV and an (optional) maximum number of files
 *      eg. var multi_selector = new MultiSelector( document.getElementById( 'files_list' ), 3 );
 *
 *   4. Add the first element
 *      eg. multi_selector.addElement( document.getElementById( 'first_file_element' ) );
 */

var maxFileNum = 1;
var fileUpFlag = null; /*FileUpload 할 경우 양식이 2가지라 이를 구분하기위해 사용한다. 2014-06-18 추가*/
//var contextPath = "${pageContext.request.contextPath}";
//alert(contextPath);

function fnMaxFileNum(num) {
	maxFileNum = num;
}

$(document).ready(function() {	
	var array = $('#comFileList');
	var i = 0;
	$.each(array, function() {
		var multi_selector = new MultiSelector( $('#comFileList').get(i), maxFileNum );
		multi_selector.addElement( $('#comFileSearch').get(i) );
		$($('#comFileSearch').get(i++)).val('');		
	});
	//var multi_selector = new MultiSelector( $('#comFileList').get(0), maxFileNum );
	//multi_selector.addElement( $('#comFileSearch').get(0) );
});


/**
 * 파일 저장후 화면에 첨부파일을 새로 그려준다.
 * [기존의 단일 파일시 상관없는데 멀티파일 업로드시 에러가 발생하여 새로 수정]
 * 2014-06-18 추가 
 */
function fnDrowfileUpload2()
{
	var array = $('#comFileList');
	var i = 0;
	$.each(array, function() {
		var multi_selector = new MultiSelector( $('#comFileList').get(i), maxFileNum );
		multi_selector.addElement( $('#comFileSearch').get(i) );
		$($('#comFileSearch').get(i++)).val('');		
	});
}

/**
 * 파일 다운로드 버튼 remove
 */
function fnDwnImgRemove(){
	var args = arguments;
	var data = args[0];
	var form = args[1];
	
	var fileList = data.fileList;
	$.each(fileList, function() {
		$(form).find("#div"+this['ATFILE_SEQ_NO']).children(":eq(1)").remove();
	});
}

/**
 * 파일 삭제 버튼 remove
 */
function fnDelImgRemove(){
	var args = arguments;
	var data = args[0];
	var form = args[1];
	
	var fileList = data.fileList;
	$.each(fileList, function() {
		$(form).find("#div"+this['ATFILE_SEQ_NO']).children().last().remove();
	});
}

/**
 * 파일 리스트 셋팅
 * 2018-10-25(조창현) 파일삭제시 폼ID 접근자 파라메터 추가
 *   - jsp 파일내에 form을 분리하여 파일 삭제처리시 ID로 판별하기 위함
 *   - comFileList을 동적으로 받아서 처리할수 있도록 변경
 * 2019-10-10(조창현) 파일삭제 이후 콜백함수 구문오류 수정
 */
function fnSetFileList() {	
	var args = arguments; //Parameters
	var data = args[0];
	var frm = args[1];
	var divSelector = args[2]!=null?args[2]:'.MultiFile-list';
	var fileReadOnly = args[3]!=null?args[3]:false;
	var isExFile = args[4]!=null?args[4]:false;
	var isCallbackDelFile = args[5]!=null?args[5]:false;
	
	var varDownloadFileNm = "fnDownloadFile";
	var varDeleteFileNm = "fnDeleteFile";
	if (isExFile) {
		varDownloadFileNm = "fnDownloadFileEx";
		varDeleteFileNm = "fnDeleteFileEx";
	}
	
	if (data != null) {
		//console.log(data.fileCnt);
		for(var d=1; d<=Number(data.fileCnt); d++){
			var dataList = data["fileList"+d];
			//console.log(dataList + "  eval :::::: " + dataList);
			if (dataList != undefined) {
				var divsel = '#MultiFile'+d+'_list';
				//$(divSelector).text(''); lcw
				//$(frm).find(divSelector).text('');
				$(frm).find(divsel).text('');
				//$(frm).find(".file_form").css({'display':'none'});
				var fileList = dataList;
				if(fileList != ""){
					//$(frm).find(".file_form").css({'display':'none'});
				}
				$.each(fileList, function() {
					var tags = '<div id="div'+this['ATFILE_SEQ_NO']+'" value="'+this['ATFILE_CONN_NO']+'">';
					tags += '<input type="text" value="'+this['REAL_FILE_NM']+'" size="30" class="only_contract_use" disabled="disabled">';
					tags += '<button type="button" role="download" class="btn btn-info btn-sm" onclick="'+varDownloadFileNm+'(\''+this['ATFILE_CONN_NO']+'\', \''+this['ATFILE_SEQ_NO']+'\')" alt="파일다운로드" title="파일다운로드"><i class="fa fa-download"></i></button>';
					if (!fileReadOnly) {
						tags += '<button type="button" role="delete" class="btn btn-info btn-sm" onclick="'+varDeleteFileNm+'(\''+this['ATFILE_CONN_NO']+'\', \''+this['ATFILE_SEQ_NO']+'\', \''+frm + '\', '+isCallbackDelFile+', \''+divsel + '\')" alt="파일삭제" title="파일삭제"><i class="fa fa-trash"></i></button>';
						//tags += '<img src="'+contextPath+'/images/ico_del.gif" onclick="'+varDeleteFileNm+'(\''+this['ATFILE_CONN_NO']+'\', \''+this['ATFILE_SEQ_NO']+'\', \''+frm + '\')" alt="파일삭제" title="파일삭제" style="padding-left:5px; cursor:pointer;" />';
					}
					tags += '</div>';				
					/////$(tags).appendTo(divSelector); lcw				
					//$(frm).find(divSelector).append(tags);
					//console.log('#MultiFile'+d+'_list');
					//console.log(dataList +" ~~ "+ divsel);
					//파일이 있을시 해당 파일폼의 notnull값 제거
					$(frm).find(divsel).parent().find("input[type=file]").removeAttr("notnull");
					$(frm).find(divsel).append(tags);
				});
			}
		}
	}
}


/**
 * 파일 다운로드
 * @param atFileConnNo
 * @param atFileSeqNo
 */
function fnDownloadFile(atFileConnNo, atFileSeqNo){
	window.location.href = contextPath+"/sys/file/downloadFile.do?ATFILE_CONN_NO="+atFileConnNo+"&ATFILE_SEQ_NO="+atFileSeqNo;	
}

/**
 * 파일 일괄 다운로드 
 * @param atFileConnNo
 */
function fnDownloadMultiFile(atFileConnNo, atFileSeqNos, atFileNm){
	var parameters = "ATFILE_CONN_NO="+atFileConnNo;
	parameters += (atFileSeqNos != undefined) ?  "&ATFILE_SEQ_NO="+atFileSeqNos : "";
	parameters += (atFileNm != undefined) ?  "&ATFILE_NM="+atFileNm : "";
	window.location.href = contextPath+"/sys/file/downloadMultiFile.do?"+parameters;	
}

/**
 * 파일 일괄 다운로드 
 * @param atFileConnNo
 */
function fnDownloadMultiFile2(atFileConnNo, atFileSeqNos, atFileNm){
	var parameters = "ATFILE_CONN_NO="+atFileConnNo;
	parameters += (atFileSeqNos != undefined) ?  "&ATFILE_SEQ_NO="+atFileSeqNos : "";
	parameters += (atFileNm != undefined) ?  "&ATFILE_NM="+atFileNm : "";
	window.location.href = contextPath+"/sys/file/downloadMultiFile2.do?"+parameters;	
}

/**
 * 파일 삭제
 * @param atFileConnNo
 * @param atFileSeqNo
 */
function fnDeleteFile(atFileConnNo, atFileSeqNo, frm, callbackFunction, divSelector){
	if (confirm("파일을 삭제하시겠습니까?")) {
		var url = contextPath+"/sys/file/deleteFile.do";
		$(frm).find("#ATFILE_CONN_NO").val(atFileConnNo);
		$(frm).find("#ATFILE_SEQ_NO").val(atFileSeqNo);
		//$('#comFileList').find('#div'+$(frm).find("#ATFILE_SEQ_NO").val()).remove();lcw
		//$(frm).find('#comFileList').find('#div'+$(frm).find("#ATFILE_SEQ_NO").val()).remove();
		$(frm).find(divSelector).find('#div'+$(frm).find("#ATFILE_SEQ_NO").val()).remove();
		
		if(callbackFunction) {
			fnAjaxSubmit(url, frm, callbackFunction);
		} else {
			fnAjaxSubmit(url, frm, null);
		}		
	}
}

/**
 * 파일 삭제 콜백 함수
 * @param data
 */
function callDeleteFile(data) {
	// 개발자가 재정의해서 함수 호출할 것!!
	//fnDelCallBack();
	$(".file_form").css({'display':'inline'});
	$("#img_view").empty();
}

/**
 * @param list_target
 * @param max
 * @returns {MultiSelector}
 */
function MultiSelector( list_target, max ){
	// Where to write the list
	this.list_target = list_target;
	// How many elements?
	this.count = 0;
	// How many elements?
	this.id = 0;
	// Is there a maximum?
	if( max ){
		this.max = max;
	} else {
		this.max = -1;
	};
	
	/**
	 * Add a new file input element
	 */
	this.addElement = function( element ){
		// comFileSearch 없는 첨부파일 조회용에서 오류 발행 하여 예외처리 lcw 2014/10/07
		if( element != undefined ){
			// Make sure it's a file input element
			if( element.tagName == 'INPUT' && element.type == 'file' ){
	
				// Element name -- what number am I?
				element.name = 'file_' + this.id++;
				
				// Add reference to this object
				element.multi_selector = this;
				//element.size = '34';
				$(element).attr("multiple", "true");
				
				// What to do when a file is selected
				element.onchange = function(){
					// Check the file extension 2015.12.02 추가
					var filter = /.(jpg|jpeg|gif|bmp|png|txt|doc|docx|hwp|pdf|zip|xls|xlsx|ppt|pptx)$/i;
					if(element.value != ''){
						if (!filter.test(element.value)){
							alert("해당 확장자는 파일 업로드 할 수 없습니다.");
							initFileTypeVal(element);
							return;
						}
					}
					
					// New file input
					var new_element = document.createElement( 'input' );
					new_element.type = 'file';
					//$(new_element).attr("multiple", "true");
					
					// Add new element
					this.parentNode.insertBefore( new_element, this );
					
					// Apply 'update' to element
					this.multi_selector.addElement( new_element );
					
					// Update list
					this.multi_selector.addListRow( this );
					
					// Hide this: we can't use display:none because Safari doesn't like it
					this.style.position = 'absolute';
					this.style.left = '-1000px';
					this.style.top = '-1000px';
					this.style.display = 'none';
					this.style.visibility = 'hidden';
					this.style.width = '0';
					this.style.height = '0';
					this.style.overflow = 'hidden';
	
					new_element.onkeypress = function(){
						return false;
					};
	
				};
				// If we've reached maximum number, disable input element
				if( this.max != -1 && this.count >= this.max ){
					//element.disabled = true;
					//element.style.display = "none";
				};
	
				// File element counter
				this.count++;
				// Most recent element 
				this.current_element = element;
				// CSS
				$(element).addClass("form-control col-8");
				$(element).attr("size", "34");
			} else {
				// This can only be applied to file input elements!
				alert( 'Error: not a file input element' );
			};
		}
	};

	/**
	 * Add a new row to the list of files
	 */
	this.addListRow = function( element ){
		// Row div
		
		//var elementVar = element.value.split(",");
		var elementVar = $(element)[0].files;
		
		if ( elementVar == null )
			elementVar = element.value.split(",");
		
		for(var k = 0; k< elementVar.length; k++)
		{
			
			var new_row = document.createElement( 'div' );
			
			var new_row_text = document.createElement( 'input' );
			new_row_text.type = 'text';
			if( elementVar[k].name != undefined) 
			new_row_text.value = elementVar[k].name;
			else
			new_row_text.value = elementVar[k];
			new_row_text.disabled = 'disabled';
			new_row_text.size = '34';
			
			// Delete button
			var new_row_button = document.createElement( 'button' );
			//var new_row_button_i = document.createElement( 'i' );
			//$(new_row_button_i).attr("class", 'fa fa-trash');
			new_row_button.type = 'button';
			//new_row_button.src = contextPath+'/images/ico_del.gif';
			new_row_button.title = '파일삭제';
			new_row_button.alt = '파일삭제';
			new_row_button.innerHTML = '<i class="fa fa-trash"></i>';
			$(new_row_button).attr("class", 'btn btn-info btn-sm'); // 
			$(new_row_button).attr("i_name", element.name); // input 을 구분 짓는 값						[]
			$(new_row_button).attr("i_cnt", k);				// input box안에 multi 파일들을 구분하는 번호 	[]
			//new_row_button.value = '파일삭제';
			
			// References
			new_row.element = element;
			
	
			// Delete function
			new_row_button.onclick= function() {
				var i_name = $(this).attr("i_name"); // input 를 구분 짓기위해 지정해놓은 값을 가지고온다
				var i_cnt = $(this).attr("i_cnt");   // input 박스 안에 몇번째 파일인지 구분 짓기 위해 가지고 온다

				var files = $(this.parentNode.element.parentNode).find("input[name='"+i_name+"']")[0].files;
				//해당 input 박스에 들어간 멀티 파티 부분의 파일들의 값을 모두다 가지고 온
				if($.browser.version != 9.0)
				{
					files[i_cnt] = null;
				}
				//파일들 중에서 i_cnt(해당 파일의 index를 찾아서 삭제 한다) null처리
				
				// Remove element from form
				//this.parentNode.element.parentNode.removeChild( this.parentNode.element );
	
				// Remove this row from the list
				this.parentNode.parentNode.removeChild( this.parentNode );
				
				// Decrement counter
				this.parentNode.element.multi_selector.count--;
	
				// Re-enable input element (if it's disabled)
				//this.parentNode.element.multi_selector.current_element.disabled = false;
				this.parentNode.element.multi_selector.current_element.style.display = 'inline';
				
				// which nixes your already queued uploads
				return false;
				
			};
	
			// Set row value
			//new_row.innerHTML = element.value;
			
			// Add text
			new_row.appendChild( new_row_text );
			
			// Add button
			new_row.appendChild( new_row_button );
	
			// Add it to the list
			this.list_target.appendChild( new_row );
			
			$(new_row_text).addClass("ctl_input");
			$(new_row_button).css("padding-left", "5px");
			//$(new_row_button).addClass("button small white");
		}
	};
};