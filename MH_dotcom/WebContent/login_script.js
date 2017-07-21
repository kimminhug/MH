function login_submit(index) {
	if (index == 1){
		document.login.action='Regist.html';
	}
	if (index == 2){
		document.login.action='admin_main.jsp';
	}
	if (index == 3){
		document.login.action='admin.html';
	}
	if (index == 4){
		document.login.action='Modify.jsp';
	}
	if (index == 5){
		document.login.action='User_List.jsp';
	}
	if (index == 6){
		document.login.action='Modify_Result.jsp';
	}
	if (index == 7){
		document.login.action='banner_Modify.jsp';
	}
	if (index == 8){
		document.login.action='menu_Modify.jsp';
	}
	document.login.submit();
}

function login_alert(){
	alert("아이디나 비밀번호가 올바르지 않습니다!");
	history.back();
}

function regist_alert(){
	alert("잘못된 입력이거나 이미 존재하는 ID입니다!");
	history.back();
}

function modify_alert(){
	alert("필수입력 정보가 빈칸이거나 입력이 올바르지 않습니다!");
	history.back();
}

