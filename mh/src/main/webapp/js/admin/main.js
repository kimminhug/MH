var dialog, form;

$(function(){
	var toggleFlag = getCookie('moreSch');
	//console.log("toggleFlag111 ::::::: " + toggleFlag);
	if(toggleFlag=='' || toggleFlag=='N'){
		$('.search-area').hide();
		$('#btnMore').text("+더보기");
	}else{
		$('.search-area').show();
		$('#btnMore').text("-닫기");
	}
	
	//검색 더보기 버튼
	$('#btnMore').on('click', function(){
		var toggleFlag = getCookie('moreSch');
		//console.log("toggleFlag ::::::: " + toggleFlag);
		
		//검색 더보기가 닫힘 상태일 때
		if(toggleFlag=='' || toggleFlag=='N'){
			setCookie("moreSch", "Y", 1); // 1일 동안 쿠키 보관
			$(this).text("-닫기");
			$('.search-area').show(500);
		}else{
			setCookie("moreSch", "N", 1); // 1일 동안 쿠키 보관
			$(this).text("+더보기");
			$('.search-area').hide(500);
		}
	});
});

/**
 * 입력 폼 사용 모달
 */
function fnDialogForm(){
	var mv = mobile_check();

	var args = arguments; //Parameters
	var url = args[0];
	var frmNm = args[1];
	var titNm = args[2];
	var wid = (mv=="false")?args[3]:'100%';
	var hei = args[4];
	var idx = args[5];
	
	$("body").append('<div id="'+frmNm+'" title="'+titNm+'"></div>');
	
	dialog = $( "#"+frmNm ).dialog({
			autoOpen: false,
	      	width: wid,
	      	height: hei,
	      	modal: true,
	      	dialogClass: "ui-dialog-zindex",
	      	buttons: {
	        	"저장": function(){fnDialogSave(frmNm);},
	        	"닫기": function(){dialog.dialog( "close" );}
	      	},
	      	open: function(event, ui) { 
	    	  	//$('input:first', $(this).parent()).attr("tabindex","1"); 
	      		$('input').blur();	//모달 팝업창 open시에 input auto focus 해제

	      		var _this = this;
	      		$.ajax({
	      			dataType: 'html'
	      			, url : url
	    	      	, data : idx
	    	      	, method : 'post'
	      			, cache : false
	      			, success: function(data) {
	      				$(_this).html(data);
	      				
	      				$('.calendar').datepicker();	//ajax폼에서 datepicker 사용
	      				
	      				var dateFormat = "yy-mm-dd",
	      				from = $( ".from_calendar" )
	      				.datepicker()
	      				.on( "change", function() {
	      					to.datepicker( "option", "minDate", getDate( this ) );
	      				}),
	      				to = $( ".to_calendar" ).datepicker()
	      				.on( "change", function() {
	      					from.datepicker( "option", "maxDate", getDate( this ) );
	      				});
	      				
	      				function getDate( element ) {
	      					var date;
	      					try {
	      						date = $.datepicker.parseDate( dateFormat, element.value );
	      					} catch( error ) {
	      						date = null;
	      					}
	      					return date;
	      				}
	      			}
	      			, error: function(xhr, status, err) {

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
	      	},
	      	close: function() {
	      		//form[ 0 ].reset();
	      		$("#"+frmNm).remove();
	      	}
	    });
	
	form = dialog.find( "form" ).on( "submit", function( event ) {
		event.preventDefault();
	});
		
	dialog.dialog( "open" );
}

