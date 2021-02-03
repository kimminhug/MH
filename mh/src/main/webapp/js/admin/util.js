//쿠키생성
function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}

//쿠키삭제
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}

//쿠키
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}
	
/*
 * 입력받은 문자의 byte를 반환
 * */
function chk_byte(obj, maxlen) {
	var txtVal = obj.val();
	var txtLen = txtVal.length;
	var max_len = maxlen;
	
	var bytesLen = 0;
	var validBodyLen = 0;
	var validBytesLen = 0;
	
	for (var i = 0; i < txtLen; i++) {
		var oneChar = txtVal.charAt(i);
		
		if (escape(oneChar).length > 4) {
			bytesLen += 2;
		} else if (oneChar === "·" || oneChar === "\\") {
			bytesLen += 2;
		} else if (oneChar === "\r") {
			continue;
		} else {
			bytesLen++;
		}
	   
	   if(bytesLen <= max_len)	{
	      validBodyLen = i + 1;
	      validBytesLen = bytesLen;
	   }
	}
	
	if(bytesLen > max_len) {
	   alert(max_len+"bytes 이상 저장하실 수 없습니다");
	   
	   var realBodyVal = txtVal.substr(0, validBodyLen);
	   
	   obj.val(realBodyVal);
	   var bytesVal = validBytesLen;
	} else {
	   bytesVal = bytesLen;
	}
		
	return bytesVal;
}

/*
 * 입력받은 숫자에 천단위로 콤마(,) 삽입
 * */
function numberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

/*
 * 입력받은 이메일의 주소형식을 체크
 * */
function isEmailChk(value) {
    var pattern = /^[\w-]+([\\\.]+[\w-]*)?@([\w-]+\.)+[\w-]+$/i;
    var reg     = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
    if(!pattern.test(value) && value!= ''){
    	alert("올바른 이메일 형식이 아닙니다.");
    	return false;
    }
}

/*
 * 입력받은 핸드폰 번호의 형식을 체크
 * */
function isMobileNo(callno){
   var _reg = /^(010|011|016|017|018|019)\-?\d{3,4}\-?\d{4}$/;
   if(! _reg.test(callno)) {
      return false;
   }
   return true;
}

/*
 * 스페이스(공백) 문자 이외에 의미있는 값인지를 체크
 * */
function isEmpty(obj) {
	var objVal="";
	if (isObject(obj)) {
		objVal = String($(obj).val());
	} else {
		objVal = String(obj);
	}
	
	if (objVal == null || objVal == undefined || objVal == "undefined" || objVal.replace(/ /gi,"") == "" || objVal.replace("　","") == "") {
		return true;
	}

	return false;
}

function isNotEmpty(obj) {
	var objVal="";
	if (isObject(obj)) {
		objVal = String($(obj).val());
	} else {
		objVal = String(obj);
	}
	
	if (objVal == null || objVal == undefined || objVal == "undefined" || objVal.replace(/ /gi,"") == "" || objVal.replace("　","") == "") {
		return true;
	}

	return true;
}

/*
 * 숫자 형식만 입력가능
 * */
function onlyNumber(obj){
	regNumber = /^[0-9]*$/;
	
	if(!regNumber.test(obj)) {
	    alert('숫자만 입력해주세요.');
	    return false;
	}
}