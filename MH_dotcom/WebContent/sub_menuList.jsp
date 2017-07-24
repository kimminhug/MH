<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <% 
 	   String key = request.getParameter("key")==null ? "" : request.getParameter("key").toString(); 
    %>
 <SELECT id="List_2" multiple>
	<OPTION value="a">Afghanistan</OPTION>
	<OPTION value="b">Bahamas</OPTION>
	<OPTION value="c">Barbados</OPTION>
	<OPTION value="d">Belgium</OPTION>
	<OPTION value="e">Bhutan</OPTION>
	<OPTION value="f">China</OPTION>
	<OPTION value="g">Croatia</OPTION>
	<OPTION value="h">Denmark</OPTION>
	<OPTION value="i">France</OPTION>
</SELECT>
    
    
<%-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/jquery-3.1.1.min.js" ></script>
<script type="text/javascript" src="login_script.js" ></script>
<script type="text/javascript">
	
	// ========= 리스트를 JS로 구현(유사배열) ================
	function List() {
		this.elements = {};
		this.idx = 0;
		this.length = 0;
	}
	List.prototype.add = function(element) {
		this.length++;
		this.elements[this.idx++] = element;
	};
	List.prototype.get = function(idx) {
		return this.elements[idx];
	};
	// =================================================

	var list_1 = new List();	// 대메뉴 리스트
	var list_2 = new List();	// 중메뉴 리스트
	var list_3 = new List();	// 소메뉴 리스트
	
	 $(document).ready(function(){
		 for (var i in list_1){
			 list_1.add($());
		 }
		 // 샘플 메뉴 -> 대분류 text값을 에 차곡차곡 저장
	 });
	
	
	
	//리스트박스 순서바꾸기
	function listbox_move(listID, direction) {

	var listbox = document.getElementById(listID);
	var selIndex = listbox.selectedIndex;

	if(-1 == selIndex) {
		alert("위치를 바꿀 박스를 선택하세요!");
		return;
	}

	var increment = -1;
	if(direction == 'up')
		increment = -1;
	else
		increment = 1;

	if((selIndex + increment) < 0 ||
		(selIndex + increment) > (listbox.options.length-1)) {
		return;
	}

	var selValue = listbox.options[selIndex].value;
	var selText = listbox.options[selIndex].text;
	listbox.options[selIndex].value = listbox.options[selIndex + increment].value
	listbox.options[selIndex].text = listbox.options[selIndex + increment].text

	listbox.options[selIndex + increment].value = selValue;
	listbox.options[selIndex + increment].text = selText;

	listbox.selectedIndex = selIndex + increment;
	}
	
    function menu_comp(){
    	alert("메뉴 변경 완료!");
    	document.menu_upload.submit();
    }
    function menuClick(type){
    	if(type=='top'){
    		$('#sub').html()
    	}
    	
    }
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>정보 수정</title>
</head>
<body>

<div align="center">메뉴 수정

<form name="menu_upload" method="post" enctype="multipart/form-data" action="banner_Mod_Result.jsp">
	<%@include file="menu.jsp"%>
	<br>
	<SELECT id="List_1" multiple onclick="menuClick('top');">
		<OPTION value="a">Afghanistan</OPTION>
		<OPTION value="b">Bahamas</OPTION>
		<OPTION value="c">Barbados</OPTION>
		<OPTION value="d">Belgium</OPTION>
		<OPTION value="e">Bhutan</OPTION>
		<OPTION value="f">China</OPTION>
		<OPTION value="g">Croatia</OPTION>
		<OPTION value="h">Denmark</OPTION>
		<OPTION value="i">France</OPTION>
	</SELECT>
	&nbsp;&nbsp;&nbsp;
	<div id="sub">
		<SELECT id="List_2" multiple>
			<OPTION value="a">Afghanistan</OPTION>
			<OPTION value="b">Bahamas</OPTION>
			<OPTION value="c">Barbados</OPTION>
			<OPTION value="d">Belgium</OPTION>
			<OPTION value="e">Bhutan</OPTION>
			<OPTION value="f">China</OPTION>
			<OPTION value="g">Croatia</OPTION>
			<OPTION value="h">Denmark</OPTION>
			<OPTION value="i">France</OPTION>
		</SELECT>
	</div>
	
	
	<br>
	<input type="button" value="줄 추가" onClick="addLine(menu_upload,1)" border=0 style='cursor:hand'>
    <input type="button" value="줄 삭제" onClick='deleteLine(menu_upload)' border=0 style='cursor:hand'>
	<input type="hidden" name="rowCount" value="1">
    <br>
	<input type="button" value="업로드" onClick="banner_comp()">
	<input type="button" value="취소" onClick='history.back()'>
</form>
</div>

</body>
</html> --%>