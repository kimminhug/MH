/* Korean initialisation for the jQuery calendar extension. */
/* Written by DaeKwon Kang (ncrash.dk@gmail.com). */
jQuery(function($){
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNames: ['1월(JAN)','2월','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)',
		'7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
		//monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['01','02','03','04','05','06','07','08','09','10','11','12'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true, // 셀렉트 박스를 년,월 순으로 바꿔준다. 
		changeMonth: true, // 월을 바꿀수 있는 셀렉트 박스를 표시한다.
	    changeYear: true, // 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
	    minDate: '-100y', // 현재날짜로부터 100년이전까지 년을 표시한다.
	    yearRange: 'c-100:c+10', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
		yearSuffix: '년'};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	
	$('.calendar').datepicker();	//datepicker 사용
	
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
	
	
});