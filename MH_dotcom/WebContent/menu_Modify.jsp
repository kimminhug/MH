<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="login_script.js" ></script>
<script type="text/javascript">
	var row_idx = 1;
	
	//배너행 추가
	function addLine(form,k){
        if(row_idx > (5-k)) return false;
        
        var CurrentRow,CurrentCell;
        CurrentRow = ban_table.insertRow();
        row_idx = CurrentRow.rowIndex;
        CurrentCell = CurrentRow.insertCell();
        row_idx++;
 
        var strHTML =  "<tr>";
        strHTML += "<td align='center' width='150'>"+ row_idx +"번째 배너 이미지</td>";
        strHTML += "<td><input type='file' id='upload_ban' size='50' name='select_ban'" +row_idx + "></td>";
        strHTML += "</tr>";      
	
      	CurrentCell.innerHTML = strHTML;
        form.rowCount.value = row_idx;
    }
    
    //배너행 삭제
    function deleteLine(form){
        if(row_idx<2){
            return false;
        }else{
            form.rowCount.value = form.rowCount.value - 1;
            row_idx--;
            ban_table.deleteRow(row_idx);
        }
    }
    
    function banner_comp(){
    	alert("배너 업로드 완료!");
    	document.ban_upload.submit();
    }
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>정보 수정</title>
</head>
<body>

<div align="center">메뉴 수정

<form name="ban_upload" method="post" enctype="multipart/form-data" action="banner_Mod_Result.jsp">
	<table id="ban_table" border="1" align="center">
	</table>
	<br>
	<input type="button" value="줄 추가" onClick="addLine(ban_upload,1)" border=0 style='cursor:hand'>
    <input type="button" value="줄 삭제" onClick='deleteLine(ban_upload)' border=0 style='cursor:hand'>
	<input type="hidden" name="rowCount" value="1">
    <br>
	<input type="button" value="업로드" onClick="banner_comp()">
	<input type="button" value="취소" onClick='history.back()'>
</form>
</div>

</body>
</html>