$(function(){
	$("input[kind=phone]").on("keyup", function(){
		autoHypen(this);
	});
});

/************************************************************
 * 통합계정 발급 시 id/pw check
 ************************************************************/
/**
 * 시작은 영문 소문자. 소문자, 숫자, 특수문자(- _ .)만 허용 (3~30자)
 * @param str
 * @returns {Boolean}
 */
function fnCheckId(str) {
	var reg_id = /^[a-z][a-z0-9\-_.]{1,28}[a-z0-9]$/;
	if(!reg_id.test(str)) {
		return false;
	}
	return true;
}

/**
 * 영문(대/소문자)과 숫자와 특수문자(!#$%^*+-) 3가지를 혼합 (9~20자) => 특수문자('@', '&', '=')제외
 * @param str
 * @returns {Boolean}
 */
function fnCheckPw(str) {
	var reg_pwd = /^(?=.*[a-zA-Z])(?=.*[!#$%\^*+\-])(?=.*[0-9])[a-zA-Z!#$%\^*+\-0-9]{9,20}$/;
	
	if(!reg_pwd.test(str)) {
		return false;
	}
	return true;
}

/**
 * 유효한 IP주소인지 체크
 * @param str
 * @returns {Boolean}
 */
function fnIsIpAddr(str) {
	var reg_ip_addr = /^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/;
	if(!reg_ip_addr.test(str)) {
		return false;
	}
	return true;
}

/**
 * 유효한 IP대역인지 체크
 * @param str
 * @returns {Boolean}
 */
function fnIsIpBand(str) {
	var reg_ip_addr = /^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/;
	if(!reg_ip_addr.test(str)) {
		return false;
	}
	return true;
}

/**
 * 유효한 이메일인지 체크; 그리드/프리폼 모두 사용 가능
 * @param value
 * @returns
 */
function fnIsEmail(value) {
	var reg = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
	return reg.test(value);
}

/**
 * 유효한 주민번호인지 체크; 그리드/프리폼 모두 사용 가능
 * @param value1
 * @param value2
 * @returns {Boolean}
 */
function fnIsSSN(value1 , value2) {
	var jumin = value1+value2;
	 
	if (jumin.length != 13)  return false;
 
	var tval = jumin.charAt(0)*2 + jumin.charAt(1)*3 + jumin.charAt(2)*4
			+ jumin.charAt(3)*5 + jumin.charAt(4)*6 + jumin.charAt(5)*7
			+ jumin.charAt(6)*8 + jumin.charAt(7)*9 + jumin.charAt(8)*2
			+ jumin.charAt(9)*3 + jumin.charAt(10)*4 + jumin.charAt(11)*5;

	var tval2 = 11- (tval % 11);
	tval2 = tval2 % 10;

//	return (jumin.charAt(12) == tval2 && (jumin.charAt(6) == "1" || jumin.charAt(6) == "2"));
//	return (jumin.charAt(12) == tval2);
	return true;
}

/**
 * text Maxlength 지정; 그리드/프리폼 모두 사용 가능
 * @param element
 * @param maxlength
 */
function fnTextMaxlength(element, maxlength) {
	var obj = $(element);
	obj.attr("usermaxlength", maxlength);
	$(element).keyup(function(event) {
		var obj = $(event.target);
		var maxlength = parseInt(obj.attr("usermaxlength"));
		if (getLength(obj.val()) > maxlength) {
			alert("글자수는 "+parseInt(maxlength/3)+"(한글), "+maxlength+"(영문/숫자)자를 넘을 수 없습니다.");
			obj.val((obj.val()).substring(0, maxlength));
		}
	});
}

/**
 * textarea Maxlength 지정; 그리드/프리폼 모두 사용 가능
 * @param element
 * @param maxlength
 */
function fnTextareaMaxlength(element, maxlength) {
	var obj = $(element);
	obj.attr("usermaxlength", maxlength);
	$(element).keyup(function(event) {
		var obj = $(event.target);
		var maxlength = parseInt(obj.attr("usermaxlength"));
		if (getLength(obj.val()) > maxlength) {
			alert("글자수는 "+parseInt(maxlength/3)+"(한글), "+maxlength+"(영문/숫자)자를 넘을 수 없습니다.");
			obj.val((obj.val()).substring(0, maxlength));
		}
	});
}




/***********************************************************
 * Usage
 *
 *   AutoValidate(FormObject)
 *
 *   <JavaScript>
 *   if (AutoValidate(FormObject) == false)
 *       return;
 *
 *   <Tags>
 *   title="이름" kind="number" notnull="true" minlength="4" maxlength="10"
 *
 *   <kind>
 *      text (default)
 *      number
 *      alphanumeric
 *      korean
 *      ascii
 *      alphabet
 *      date : sep="([.]|[/]|[-]|[\s]|[\,])?"
 *      year month day : group="SameName"
 *      ssn : sep="([-]|[\s])?"
 *      ssn1 ssn2 : group="SameName"
 *      email
 *      nohanmail
 *
 *    <적용가능한 Tags>
 *    input (text/password)
 *    textarea
 *    select
 *
 ***********************************************************/

/***********************************************************
 * Configuration
 ***********************************************************/

var isDebug = true;
//var isDebug = false;
//var undefined = null;

/***********************************************************/

function AutoValidate(FormObject) {
    //var varLength = FormObject.elements.length;
	//alert("33333");
	var retValue = true;
    //for (var i = 0; i < varLength; i++) {
	$.each(FormObject, function() {
	//for(var fobj in FormObject) {
        /*var obj         = FormObject.elements[i];
		var value       = obj.value;
        var kind        = obj.kind;
        var group       = obj.group;
        var notnull     = obj.notnull;
        var minlength   = obj.minlength;
        var maxlength   = obj.maxlength;*/
		var obj         = $(this);
		var value       = obj.val();//obj.attr("value");
        var kind        = obj.attr("kind");
        var group       = obj.attr("group");
        var notnull     = obj.attr("notnull");
        var minlength   = obj.attr("minlength");
        var maxlength   = obj.attr("maxlength");
     
        /******************************************
         * Not Null Check
         ******************************************/
        // 속성에 대한 Validation 체크 유무
        var blnCheckAttr = true;

        if (notnull == 'true') {
            blnCheckAttr = true;
            if(obj.attr("type")=="radio" || obj.attr("type") == "checkbox"){
            	//var flag = false;
            	var name = obj.attr("name"); 
            	var objCnt = $("input[name="+name+"]").length;
            	var chkLen = $("input[name="+name+"]:checked").length;
            	
           		//alert(name +" ::: "+ obj.attr("name") +" :: "+ objCnt + " : " + chkLen);
            	if (chkLen <= 0) {
            		retValue = alertError(obj, 'notnull');
            		return retValue;
            	}
            }else{
            	if (value == '') {
            		retValue = alertError(obj, 'notnull');
            		return retValue;
            	}
            }
        } else {
            if (value == '') {
                blnCheckAttr = false;
            } else {
                blnCheckAttr = true;
            }
        }

        if (blnCheckAttr) {
            /******************************************
             * Min Length Check
             ******************************************/
            if (minlength != '' && minlength != undefined) {
                var length = getLength(value, obj);
                if (length < minlength) {
                	retValue = alertError(obj, 'minlength');
                    return retValue;
                }
            }

            /******************************************
             * Max Length Check
             ******************************************/
            if (maxlength != '' && maxlength != undefined) {
            	if(obj.is("input[format=number]")) {
            		value = value.replace(/\,/g, "");
            	}
                var length = getLength(value, obj);
                if (length > maxlength) {
                	retValue = alertError(obj, 'maxlength');
                    return retValue;
                }
            }

            /******************************************
             * Kind Check
             *
             * Kind Type
             *   number
             *   alphanumeric
             *   alphabet
             *   korean (2 bytes)
             *   ascii
             *   date
             *   compareDate
             *   year month day
             *   email
             *   nohanmail
             *   ssn
             *   ssn1 ssn2
             *
             ******************************************/
            //if (kind == '')
            if (kind != '') {
                //continue;

	            // 숫자 입력태그인 경우
	            if (kind == 'number') {
	            	if(obj.is("input[format=number]")) {
	            		value = value.replace(/\,/g, "");
	            	}
	                if (isNumber(value) == false) {
	                	retValue = alertError(obj, 'number');
	                    return retValue;
	                }
	
	            // 영문자+숫자 입력태그인 경우
	            } else if (kind == 'alphanumeric') {
	                if (isAlphaNumeric(value) == false) {
	                	retValue = alertError(obj, 'alphanumeric');
	                    return retValue;
	                }
	
	            // 영문자 입력태그인 경우
	            } else if (kind == 'alphabet') {
	                if (isAlphabet(value) == false) {
	                	retValue = alertError(obj, 'alphabet');
	                    return retValue;
	                }
	
	            // 한글(2 bytes) 입력태그인 경우
	            } else if (kind == 'korean') {
	                if (isKorean(value) == false) {
	                	retValue = alertError(obj, 'korean');
	                    return retValue;
	                }
	
	            // ASCII 문자만 입력받는 경우
	            } else if (kind == 'ascii') {
	                if (isASCII(value) == false) {
	                	retValue = alertError(obj, 'ascii');
	                    return retValue;
	                }
	
	            // Email 입력 태그인 경우
	            } else if (kind == 'email') {
	                if (isEmail(value) == false) {
	                	retValue = alertError(obj, 'email');
	                    return retValue;
	                }
	
	            // HanMail 체크
	            } else if (kind == 'nohanmail') {
	                if (isEmail(value) == false) {
	                	retValue = alertError(obj, 'email');
	                    return retValue;
	                } else if (isHanMail(value)) {
	                	retValue = alertError(obj, 'hanmail');
	                    return retValue;
	                }
	
	            // 주민등록번호 (6+7) 입력태그인 경우
	            } else if (kind == 'ssn') {
	                if (isSsn(obj, value, 1) == false) {
	                	retValue = alertError(obj, 'ssn');
	                    return retValue;
	                }
	
	            /************************************
	             * 날짜 입력 태그
	             ************************************/
	
	            // 날짜 입력 태그인 경우 (년+월+일)
	            } else if (kind == 'date') {
	                if (isDate(obj, value, 1) == false) {
	                	retValue = alertError(obj, 'date');
	                    return retValue;
	                }
	
	            /**
	             * 날짜 입력 Check
	             *
	             * 년도, 월, 일이 서로다른 입력태그로 입력되는 경우
	             * 년도, 월, 일을 모두 받아서 날짜 검증
	             * 이름에 다음과 같은 규칙이 반드시 있어야 한다.
	             * 
	             * kind="year"  name="strSomeFieldNameYear"  title="등록년도" notnull="true"
	             * kind="month" name="strSomeFieldNameMonth" title="등록월"   notnull="true"
	             * kind="day"   name="strSomeFieldNameDay"   title="등록일"   notnull="true"
	             *
	             * 총 5개 날짜까지 지원한다.
	             */
	            } else if (kind == 'year') {
	                var dateValue = getDate(FormObject, group);
	
	                if (isDate(obj, dateValue, 3) == false) {
	                	retValue = alertError(obj, 'date');
	                    return retValue;
	                }
	    /*
	            } else if (kind == 'month') {
	                var dateValue = getDate(FormObject, group);
	
	                if (isDate(dateValue) == false) {
	                    return alertError(obj, 'date');
	                }
	            } else if (kind == 'day') {
	                var dateValue = getDate(FormObject, group);
	
	                if (isDate(dateValue) == false) {
	                    return alertError(obj, 'date');
	                }
	    */
	            /**
	             * 주민등록번호 입력 Check
	             *
	             * 주민등록번호 앞뒤자리가 별도의 태그로 입력되는 경우
	             * 명명규칙
	             * kind="ssn1" group="SameName" title="주민등록번호", notnull="true"
	             * kind="ssn2" group="SameName" title="주민등록번호", notnull="true"
	             */
	            } else if (kind == 'ssn1') {
	                var ssnValue = getSsn(FormObject, group);
	
	                if (isSsn(obj, ssnValue, 2) == false) {
	                	retValue = alertError(obj, 'ssn');
	                    return retValue;
	                }
	    /*
	            } else if (kind == 'ssn2') {
	                var ssnValue = getSsn(FormObject, group);
	                
	                if (isSsn(ssnValue) == false) {
	                    return alertError(obj, 'ssn');
	                }
	    */
	            } else if (kind == 'phone') {
	                if (isPhoneNumber(value) == false)
	                {
	                	retValue = alertError(obj, 'phone');
	                    return retValue;
	                }
	            } else if(kind == 'busi'){
	            	if (chkBusiNum(value) == false)
	                {
	                	retValue = alertError(obj, 'busi');
	                    return retValue;
	                }
	            }
	
	            
	            /******************************************
	             * Compare
	             *
	             *   <Compare_Attribute>
	             *      eq : Equal
	             *      ne : Not Equal
	             *      gt : Great Then
	             *      ge : Great/Equal
	             *      lt : Less Then
	             *      le : Less/Equal
	             *
	             *   <Tags>
	             *   kind="number" (Compare_Attribute)="비교대상_name"
	             ******************************************/
	            /* dswi 잠깐 주석 (고민필요)
	            var eq = obj.eq;
	            var ne = obj.ne;
	            var gt = obj.gt;
	            var ge = obj.ge;
	            var lt = obj.lt;
	            var le = obj.le;
	
	            if (eq == undefined)  eq = '';
	            if (ne == undefined)  ne = '';
	            if (gt == undefined)  gt = '';
	            if (ge == undefined)  ge = '';
	            if (lt == undefined)  lt = '';
	            if (le == undefined)  le = '';
	
	            var comp2Name = '';
	
	            if      (eq != '')  comp2Name = eq;
	            else if (ne != '')  comp2Name = ne;
	            else if (gt != '')  comp2Name = gt;
	            else if (ge != '')  comp2Name = ge;
	            else if (lt != '')  comp2Name = lt;
	            else if (le != '')  comp2Name = le;
	
	            var comp1Value = null;
	            var comp2Value = null;
	            var comp2Title = null;
	
	            if (comp2Name != '') {
	                comp2Title = FormObject[comp2Name].title;
	
	                if (kind == "number") {
	                    comp1Value = parseInt(value);
	                    comp2Value = parseInt(FormObject[comp2Name].value);
	                } else {
	                    comp1Value = value;
	                    comp2Value = FormObject[comp2Name].value;
	                }
	            }
	
	
	            if (eq != '') {
	                if (comp1Value != comp2Value) {
	                    return alertCompError(obj, comp2Title, 'eq');
	                }
	            } else if (ne != '') {
	                if (comp1Value == comp2Value) {
	                    return alertCompError(obj, comp2Title, 'ne');
	                }
	            } else if (gt != '') {
	                if (comp1Value <= comp2Value) {
	                    return alertCompError(obj, comp2Title, 'gt');
	                }
	            } else if (ge != '') {
	                if (comp1Value < comp2Value) {
	                    return alertCompError(obj, comp2Title, 'ge');
	                }
	            } else if (lt != '') {
	                if (comp1Value >= comp2Value) {
	                    return alertCompError(obj, comp2Title, 'lt');
	                }
	            } else if (le != '') {
	                if (comp1Value > comp2Value) {
	                    return alertCompError(obj, comp2Title, 'le');
	                }
	            }
	            */
	            
	            /******************************************
	             * XSS (Cross Site Script)
	             *
	             * script	- 자바스크립트 or vb 스크립트 포함
	             * iframe	- 악성코드 포함된 싸이트 이동 가능
	             * applet	- 자바 애프릿관 연결 태그
	             * embed	- 웹페이지 여러가지 형태 객체 추가 (자바, 이미지 멀티미디어등)
	             ******************************************/
	            //if(value.indexOf("</script>") != -1 || value.indexOf("</iframe>"))
	            var filter = /<script|<\/script>|\<\/iframe>|<\/applet>|<\/embed>/gi;
				if (filter.test(value)){
					retValue = alertError(obj, 'xss');
	                return retValue;
				}
	            
            }
        }
    });
	
	return retValue;
}



/******************************************************
 * 메시지 & 포커스 함수
 ******************************************************/
function alertError(obj, error) {
    /*var title     = obj.title;
    var minlength = obj.minlength;
    var maxlength = obj.maxlength;*/
    
    var title     = obj.attr("title");
    var minlength = obj.attr("minlength");
    var maxlength = obj.attr("maxlength");

    if      (error == 'notnull'     ) alert(title + '을(를) 입력하십시오.');
    else if (error == 'minlength'   ) alert(title + '을(를) ' + parseInt(minlength/3) + '(한글), ' + minlength + '(영문/숫자)자 이상 적어주세요.');
    else if (error == 'maxlength'   ) alert(title + '은(는) ' + parseInt(maxlength/3) + '(한글), ' + maxlength + '(영문/숫자)자를 넘을 수 없습니다.');
    else if (error == 'number'      ) alert(title + '은(는) 숫자만 입력할 수 있습니다.');
    else if (error == 'alphanumeric') alert(title + '은(는) 알파벳과 숫자만 입력할 수 있습니다.');
    else if (error == 'alphabet'    ) alert(title + '은(는) 알파벳만 입력할 수 있습니다.');
    else if (error == 'korean'      ) alert(title + '은(는) 한글만 입력할 수 있습니다.');
    else if (error == 'ascii'       ) alert(title + '은(는) 한글을 입력할 수 없습니다.');
    else if (error == 'date'        ) alert(title + '은(는) 잘못된 날짜 입니다.');
    else if (error == 'email'       ) alert(title + '은(는) 잘못된 이메일 주소 입니다.');
    else if (error == 'hanmail'     ) alert(title + '에 hanmail.net은 사용하실 수 없습니다.');
    else if (error == 'ssn'         ) alert('잘못된 주민등록번호 입니다.');
    else if (error == 'phone'       ) alert(title + '은(는) 잘못된 전화번호 입니다.');
    else if (error == 'busi'       ) alert(title + '은(는) 잘못된 사업자 번호 입니다.');
    else if (error == 'xss'       ) alert(title + '은(는) <script>,<iframe>,<applet>,<embed> 태그는 사용 할 수 없습니다.');
    else                              alert('개발자님 ' + title + '의 유효성 검사중 문제가 발생했습니다.');

    obj.focus();

    return false;
}


function alertCompError(obj, comp2Title, error) {
    //var comp1Title = obj.title;
	var comp1Title = obj.attr("title");

    if      (error == 'eq') alert(comp1Title + '와(과) ' + comp2Title + '은(는) 같아야 합니다.'      );
    else if (error == 'ne') alert(comp1Title + '와(과) ' + comp2Title + '은(는) 같으면 안 됩니다.'   );
    else if (error == 'gt') alert(comp1Title + '은(는) ' + comp2Title + ' 보다 커야 합니다.'         );
    else if (error == 'ge') alert(comp1Title + '은(는) ' + comp2Title + ' 보다 크거나 같아야 합니다.');
    else if (error == 'lt') alert(comp1Title + '은(는) ' + comp2Title + ' 보다 작아야 합니다.'       );
    else if (error == 'le') alert(comp1Title + '은(는) ' + comp2Title + ' 보다 작거나 같아야 합니다.');
    else                    alert('개발자님 ' + comp1Title + '와(과) ' + comp2Title + '의 유효성 검사중 문제가 발생했습니다.');

    obj.focus();

    return false;
}



/******************************************************
 * 주민등록번호 체크 함수
 ******************************************************/
function isSsn(obj, value, fieldCount) {
    //var sep = obj.sep;
	var sep = obj.attr("sep");
	
    if (sep == undefined) {
        sep = "";
    }

    var pattern = /^([0-9][0-9][0-1][0-9][0-3][0-9])([-]|[\s])?([1-4][0-9][0-9][0-9][0-9][0-9][0-9])$/g;

    if(pattern.test(value)) {
        var ssn1 = RegExp.$1;
        var ssn2 = RegExp.$3;

        if (fieldCount == 1) {
            //obj.value = ssn1 + sep + ssn2;
        	obj.val(ssn1 + sep + ssn2);
        }

        return checkSsn(ssn1, ssn2);
    } else {
        return false;
    }
}


/******************************************************
 * Email 체크 함수
 ******************************************************/
function isEmail(value) {
    //var pattern = /^(\w+(?:\.\w+)*)@((?:\w+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    //                 \w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
    var pattern = /^[\w-]+([\\\.]+[\w-]*)?@([\w-]+\.)+[\w-]+$/i;
    return pattern.test(value);
    return true;
}


/******************************************************
 * Han Mail 체크 함수
 ******************************************************/
function isHanMail(value) {
    var varDomain = value.substring(value.indexOf("@")+1).toLowerCase();
    if (varDomain == "hanmail.net") return true;
    return false;
}

/******************************************************
 * 전화번호 체크 함수
 ******************************************************/
function isPhoneNumber(value) {
    var varField = value.split("-");
    if (varField.length < 2 || varField.length > 3)
    {
        return false;
    }
    for (var i = 0; i < varField.length; i++)
    {
        if (isNumber(varField[i]) == false) {
            return false;
        }
    }
    return true;
}

/******************************************************
 * 날짜 체크 함수
 ******************************************************/
function isDate(obj, value, fieldcount) {
    //var sep = obj.sep;
	var sep = obj.attr("sep");
    if (sep == undefined) {
        sep = "-";
    }

    var pattern = /^([1-2][0-9][0-9][0-9])([.]|[\/]|[-]|[\s]|[\,])?([0-1][0-9]|[1-9])([.]|[\/]|[-]|[\s]|[\,])?([0-3][0-9]|[1-9])$/g;

    if(pattern.test(value)) {
        var year  = RegExp.$1;
        var month = RegExp.$3;
        var day   = RegExp.$5;

        if (fieldcount == 1) {
            var printMonth;
            var printDay;

            if (month.length == 1)  printMonth = "0" + month;
            else                    printMonth = month;

            if (day.length   == 1)  printDay   = "0" + day;
            else                    printDay   = day;

            //obj.value = year + sep + printMonth + sep + printDay;
            obj.val(year + sep + printMonth + sep + printDay);
        }

        return checkDate(year, month, day);
    } else {
        return false;
    }
}


/******************************************************
 * 길이 체크 함수
 ******************************************************/
function getLength(value, obj) {
	//alert("value : " + value + " ~ " + obj.attr("name"));
    var length = 0;
    var i = 0;
    while(true) {  
        var ch = escape(value.charAt(i++));
        if ( ch == "" || ch == null ) {
            break;
        }
        length  += strCharByte(ch);
    }
    return length;
}

/******************************************************
 * 한문자가 1 byte인지 2 bytes인지 체크하는 함수
 ******************************************************/
function strCharByte(chStr) {
    if (chStr.substring(0, 2) == "%u") {
        if (chStr.substring(2,4) == "00") {
            return 1; 
        } else {
            return 3; //한글 
        }
    } else if (chStr.substring(0,1) == "%") { 
        if (parseInt(chStr.substring(1,3), 16) > 127) {
            return 3; //한글 
        } else {
            return 1; 
        }
    } else {
        return 1;
    }
}


/******************************************************
 * 숫자 check 함수
 ******************************************************/
function isNumber(value) {
    for (var i=0; i < value.length; i++) {
        if (!('0' <= value.charAt(i) && value.charAt(i) <= '9') && value.charAt(i) != '.') {
            return false;
        }
    }
    return true;
}
function isNumric(value) {
	for (var i=0; i < value.length; i++) {
		if (!('0' <= value.charAt(i) && value.charAt(i) <= '9') && value.charAt(i) != ',') {
			return false;
		}
	}
	return true;
}

/******************************************************
 * 알파벳 check 함수
 ******************************************************/
function isAlphabet(value) {
    for (var i = 0; i < value.length; i++) {
        temp = value.substring(i, i+1);
        if (value.indexOf(temp) == -1) { 
            return false; 
        } else if (!(('a' <= temp && temp < 'z') ||
                     ('A' <= temp && temp < 'Z') ||
                     (' ' == temp))) {
            return false;
        }
    }
    return true;
}

/******************************************************
 * 알파벳 + 숫자 + 확장문자(-, _) check 함수
 ******************************************************/
/*
function isExtChar(value) {
    var extchar = '-_';
    extchar.charAt(i)

    for (var i = 0; i < value.length; i++) {
        temp = value.substring(i, i+1);
        if (value.indexOf(temp) == -1) { 
            return false; 
        } else if (!(('a' <= temp && temp < 'z') ||
                     ('A' <= temp && temp < 'Z') ||
                     (' ' == temp))) {
            return false;
        }
    }
    return true;
}   
*/


/******************************************************
 * 아스키문자로만 구성되어 있는지 Check
 ******************************************************/
function isASCII(value) {
    if (value.length == 0) {
        return false;
    }

    for (var i = 0; i < value.length; i++ ) {
        // blank : 32
        // ~ : 126
        if (' ' > value.charAt(i) || value.charAt(i) > '~') {
            return false;
        }
    }
    return true;
}

/******************************************************
 * 알파벳과 숫자로 구성되어 있는지 Check
 ******************************************************/
function isAlphaNumeric(value) {
    if (value.length == 0) {
        return false;
    }

    for (var i = 0; i < value.length; i++ ) {
        if (!(('a' <= value.charAt(i) && value.charAt(i) <= 'z') ||
              ('A' <= value.charAt(i) && value.charAt(i) <= 'Z') ||
              ('0' <= value.charAt(i) && value.charAt(i) <= '9') ||
              (' ' == value.charAt(i)) )) {
            return false;
        }
    }
    return true;
}

/******************************************************
 * 한글(2byte)인지 Check
 ******************************************************/
function isKorean(value) {
    var i = 0;
    while (true) {
        var varCh = value.charAt(i++);
        if ( varCh == "" || varCh == null ) {
            break;
        } else if (varCh == ' ') {
            continue;
        } else {
            var ch = escape(varCh);
            if (ch.substring(0, 2) == "%u") {
                if (ch.substring(2, 4) == "00") {
                    return false;
                }
            } else if (ch.substring(0, 1) == "%") {
                if (parseInt(ch.substring(1, 3), 16) <= 127) {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
    return true;
}

/******************************************************
 * 주민등록번호 Check 함수
 ******************************************************/
function checkSsn(first, last) {

    var a1 = first.substring(0, 1);
    var a2 = first.substring(1, 2);
    var a3 = first.substring(2, 3);
    var a4 = first.substring(3, 4);
    var a5 = first.substring(4, 5);
    var a6 = first.substring(5, 6);
    var check_digit = a1 * 2 + a2 * 3 + a3 * 4 + a4 * 5 + a5 * 6 + a6 * 7;
    var b1 = last.substring(0, 1);
    var b2 = last.substring(1, 2);
    var b3 = last.substring(2, 3);
    var b4 = last.substring(3, 4);
    var b5 = last.substring(4, 5);
    var b6 = last.substring(5, 6);
    var b7 = last.substring(6, 7);
    check_digit = check_digit + b1 * 8 + b2 * 9 + b3 * 2 + b4 * 3 + b5 * 4 + b6 * 5;

    check_digit = check_digit % 11;
    check_digit = 11 - check_digit;
    check_digit = check_digit % 10;

    if (check_digit != b7) {
        return false;
    }
    return true;
}

/******************************************************
 * 날짜 Check 함수
 ******************************************************/
function checkDate(year, month, day) {

    if (checkYear(year) == false) {
        return false;
    }

    if (checkMonth(month) == false) {
        return false;
    }

    if (checkDay(day) == false) {
        return false;
    }

    var varYear  = Number(year);
    var varMonth = Number(month);
    var varDay   = Number(day);

    if (varDay > daysInMonth[varMonth]) {
        return false;
    }

    if (varMonth == 2 && varDay > daysInFebrary(varYear)) {
        return false;
    }

    return true;
}

function checkYear(year) {
    if (year == '') {
        return false;
    }
    if (isNumber(year) == false) {
        return false;
    }
    var varYear = Number(year);
    if (varYear < 1889 || varYear > 2100) {
        return false;
    }
    return true;
}

function checkMonth(month) {
    if (month == '') {
        return false;
    }
    if (isNumber(month) == false) {
        return false;
    }
    var varMonth = Number(month);
    if (varMonth < 1 || varMonth > 12) {
        return false;
    }
    return true;
}

function checkDay(day) {
    if (day == '') {
        return false;
    }
    if (isNumber(day) == false) {
        return false;
    }
    var varDay = Number(day);
    if (varDay < 1 || varDay > 31) {
        return false;
    }
    return true;
}

var daysInMonth = new Array(13);
daysInMonth[1]  = 31;
daysInMonth[2]  = 29;
daysInMonth[3]  = 31;
daysInMonth[4]  = 30;
daysInMonth[5]  = 31;
daysInMonth[6]  = 30;
daysInMonth[7]  = 31;
daysInMonth[8]  = 31;
daysInMonth[9]  = 30;
daysInMonth[10] = 31;
daysInMonth[11] = 30;
daysInMonth[12] = 31;

function daysInFebrary(year) { 
    return (year % 4 == 0 && (!(year % 100 == 0) || (year % 400 == 0)) ? 29 : 28); 
}

/******************************************************
 * 주민등록번호의 앞6자리와 뒤7자리가 다른 필드일때, 하나로 합친 ssn 필드 생성
 ******************************************************/
function getSsn(FormObject, groupValue) {
    //var varLength = FormObject.elements.length;
    var ssn1Value = "";
    var ssn2Value = "";
    
    var retValue = "";
    
    //for (var i = 0; i < varLength; i++) {
    $.each(FormObject, function() {
        /*var obj   = FormObject.elements[i];
        var value = obj.value;
        var kind  = obj.kind;
        var group = obj.group;*/
    	var obj   = $(this);
        var value = obj.attr("value");
        var kind  = obj.attr("kind");
        var group = obj.attr("group");
        
        if (group == groupValue) {
            if      (kind == "ssn1")  ssn1Value = value;
            else if (kind == "ssn2")  ssn2Value = value;

            if (!(ssn1Value == "" || ssn2Value == "")) {
            	retValue = ssn1Value + ssn2Value;
                return false;
            }
        }
    });
    
    return retValue;
}


/******************************************************
 * 날짜의 연, 월, 일이 각각 다른 필드일때, 하나로 합친 date 필드 생성
 ******************************************************/
function getDate(FormObject, groupValue) {
    //var varLength = FormObject.elements.length;
    var yearValue  = "";
    var monthValue = "";
    var dayValue   = "";
    
    var retValue = "";
    
    //for (var i = 0; i < varLength; i++) {
    $.each(FormObject, function() {
        /*var obj   = FormObject.elements[i];
        var value = obj.value;
        var kind  = obj.kind;
        var group = obj.group;*/
        var obj   = $(this);
        var value = obj.attr("value");
        var kind  = obj.attr("kind");
        var group = obj.attr("group");
        
        if (group == groupValue) {
            if      (kind == "year" )  yearValue  = value;
            else if (kind == "month")  monthValue = value;
            else if (kind == "day"  )  dayValue   = value;

            if (!(yearValue == "" || monthValue == "" || dayValue == "")) {
            	retValue = yearValue + monthValue + dayValue;
                return false;
            }
        }
    });

    return retValue;
}

/******************************************************
 * 날짜 비교 (obj1은 obj2보다 항상 이른 날짜가 들어가게 하는 체크)
 ******************************************************/
function compareDate(obj1, obj2){
    var varReturn = false;

    if (isDate(obj1, obj1.value, 1) && isDate(obj2, obj2.value, 1)) {


        var sep1 = obj1.sep;
        var sep2 = obj2.sep;

        var varField1 = "";
        var varField2 = "";

        if (sep1 == undefined || sep1 == "") {
            varField1 = obj1.value.substring(0,4)+obj1.value.substring(4,6)+obj1.value.substring(6);
        } else {
            var varObj1 = obj1.value.split(sep1);

            varField1 = varObj1[0]+varObj1[1]+varObj1[2];
        }

        if (sep2 == undefined || sep2 == "") {
            varField2 = obj2.value.substring(0,4)+obj2.value.substring(4,6)+obj2.value.substring(6);
        } else {
            var varObj2 = obj2.value.split(sep2);

            varField2 = varObj2[0]+varObj2[1]+varObj2[2];
        }

        if (Number(varField1) <= Number(varField2)){
            varReturn = true;
        }

    }
    return varReturn;
}

/******************************************************
 * object 타입인지 확인
 ******************************************************/
function isObject(obj) {
	if (typeof(obj) == "object") return true;
	return false;
}

/******************************************************
 * 스페이스 이외에 의미있는값이 있는지 체크
 ******************************************************/
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

/******************************************************
 * 사업자번호 유효성 검증
 ******************************************************/
function chkBusiNum(value) {
    var valueMap = value.replace(/-/gi, '').split('').map(function(item) {
        return parseInt(item, 10);
    });

    if (valueMap.length === 10) {
        var multiply = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5);
        var checkSum = 0;

        for (var i = 0; i < multiply.length; ++i) {
            checkSum += multiply[i] * valueMap[i];
        }

        checkSum += parseInt((multiply[8] * valueMap[8]) / 10, 10);
        return Math.floor(valueMap[9]) === (10 - (checkSum % 10));
    }

    return false;
}

/******************************************************
 * 전화번호 자동 '-' 입력
 ******************************************************/
function autoHypen(obj){
	$(obj).val( $(obj).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})/,"$1-$2-$3").replace("--", "-") );
    /*var str = obj.value.replace(/[^0-9]/g, '');
    var tmp = '';
    
    if( str.length < 4){
  	  obj.value = str;
  	  
    }else if(str.length < 7){
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3);
        
        obj.value = tmp;
        
    }else if(str.length < 11){
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 3);
        tmp += '-';
        tmp += str.substr(6);
        
        obj.value = tmp;
        
    }else{              
        tmp += str.substr(0, 3);
        tmp += '-';
        tmp += str.substr(3, 4);
        tmp += '-';
        tmp += str.substr(7);
        
        obj.value = tmp;
    }*/
}