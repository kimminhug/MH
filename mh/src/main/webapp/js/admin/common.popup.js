
/**
 * 팝업창 오픈 (화면 가운데로)
 * @returns
 */
function fnOpenPopup() {
	var args = arguments;
	var name = args[0];
	var url = args[1];
	var width = args[2];
	var height = args[3];
	var scrollbars = args[4]!=null?args[4]:'yes';
	
	var leftpos = (screen.availWidth - width) / 2; 
	var toppos = (screen.availHeight - height) / 2; 
	if (!url || url == "") url = "about:blank";
	
	wrapWindowByMask();
	
	return window.open(url, name, 'scrollbars='+scrollbars+', toolbar=no, location=no, status=no, menubar=no, resizable=no, width='+width+', height='+height+', left='+leftpos+', top='+toppos);
}

/**
 * 팝업창 오픈 (화면 좌측상단)
 * @returns
 */
function fnOpenLeftPopup() {
	var args = arguments;
	var name = args[0];
	var url = args[1];
	var width = args[2];
	var height = args[3];
	var scrollbars = args[4]!=null?args[4]:'no';
	
	var leftpos = 0; 
	var toppos = 0; 
	if (!url || url == "") url = "about:blank";
	return window.open(url, name, 'scrollbars='+scrollbars+', toolbar=no, location=no, status=no, menubar=no, resizable=no, width='+width+', height='+height+', left='+leftpos+', top='+toppos);
}

/**
 * 팝업창 오픈 (화면 가운데로, 리사이즈 가능)
 * @returns
 */
function fnOpenPopupResize() {
	var args = arguments;
	var name = args[0];
	var url = args[1];
	var width = args[2];
	var height = args[3];
	var scrollbars = args[4]!=null?args[4]:'no';
	var location = args[5]!=null?args[5]:'no';
	
	var leftpos = (screen.availWidth - width) / 2; 
	var toppos = (screen.availHeight - height) / 2; 
	if (!url || url == "") url = "about:blank";
	return window.open(url, name, 'scrollbars='+scrollbars+', toolbar=no, location='+location+', status=no, menubar=no, resizable=yes, width='+width+', height='+height+', left='+leftpos+', top='+toppos);
}

/**
 * 모달 팝업창 오픈 (화면 가운데로)
 * @returns
 */
function fnOpenModalPopup() {
	var args = arguments;
	var name = args[0];
	var url = args[1];
	var width = args[2];
	var height = args[3];
	var enable = args[4]!=null?args[4]:true;
	if (!enable) return;
	var leftpos = (screen.availWidth - width) / 2; 
	var toppos = (screen.availHeight - height) / 2; 
	if (!url || url == "") url = "about:blank";
	return window.showModalDialog(url, name, 'scroll=no; center=yes; help=no; status=no; resizable=no; dialogWidth='+width+'px; dialogHeight='+height+'px; dialogLeft='+leftpos+'px; dialogTop='+toppos+'px');
}


/**
 * 모달 팝업창 오픈 (화면 가운데로, 리사이즈 가능)
 * @returns
 */
function fnOpenModalPopupResize() {
	var args = arguments;
	var name = args[0];
	var url = args[1];
	var width = args[2];
	var height = args[3];
	var enable = args[4]!=null?args[4]:true;
	if (!enable) return;
	var leftpos = (screen.availWidth - width) / 2; 
	var toppos = (screen.availHeight - height) / 2; 
	if (!url || url == "") url = "about:blank";
	return window.showModalDialog(url, name, 'scroll=no; center=yes; help=no; status=no; resizable=yes; dialogWidth='+width+'px; dialogHeight='+height+'px; dialogLeft='+leftpos+'px; dialogTop='+toppos+'px');
}
