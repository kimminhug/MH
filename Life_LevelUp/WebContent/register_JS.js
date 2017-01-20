
function cal(){
	var height, weight;
	var waist, hip, age;
	var k;
	
	height = jq("#height").val();
	weight = jq('#weight').val();
	waist = jq('#waist').val();
	hip = jq('#hip').val();
	age = jq('#age').val();
	
	if(age == ""){
		alert("나이를 입력하세요");
		jq('#age').focus();
		return;
		
	} else if(height==""){
		alert("신장을 입력하세요");
		jq('#height').focus();
		return;
		
	} else if(weight==""){
		alert("체중을 입력하세요");
		jq('#weight').focus();
		return;
		
	} else if(waist==""){
		alert("허리 사이즈를 입력하세요");
		jq('#waist').focus();
		return;
		
	} else if(hip==""){
		alert("엉덩이 사이즈를 입력하세요");
		jq('#hip').focus();
		return;
	}
	
	if(!jq.isNumeric(height)){
		alert("숫자만 가능합니다");
		jq('#height').val('');
		jq('#height').focus();
		return;
		
	} else if(!jq.isNumeric(weight)){
		alert("숫자만 가능합니다");
		jq('#weight').val('');
		jq('#weight').focus();
		return;
		
	} else if(!jq.isNumeric(waist)){
		alert("숫자만 가능합니다");
		jq('#waist').val('');
		jq('#waist').focus();
		return;
		
	} else if(!jq.isNumeric(hip)){
		alert("숫자만 가능합니다");
		jq('#hip').val('');
		jq('#hip').focus();
		return;
		
	} else if(!jq.isNumeric(age)){
		alert("숫자만 가능합니다");
		jq('#age').val('');
		jq('#age').focus();
		return;
	}
	
	if( height <= 0 || weight <= 0 || waist<=0 || hip<=0 || age<=0 ){
		alert("0 이상의 값을 입력하세요");
		return;
	}
	
	waist = waist * 2.54;
	hip = hip * 2.54;
	
	if(Sex==0 || Sex==null){
		k = (Math.round((1-((weight*1.082)-(waist*0.7417)+44.682)/weight)*10000))/100;
	}else{
		k = (Math.round((1-((weight*1.082)-(waist*0.7417)+34.849)/weight)*10000))/100;
	}
	if(k<0){
		alert("값을 잘못 입력 하셨습니다");
		Recal();
		return;
	}
	if(Sex==0 || Sex==null){
		if(k<=25){
			jq('#bmi_val').val(k);
			jq('#bmi_txt').val('정상수치');
		}else{
			jq('#bmi_val').val(k);
			jq('#bmi_txt').val('비만수치');
		}
	}else{
		if(k<=30){
			jq('#bmi_val').val(k);
			jq('#bmi_txt').val('정상수치');
		}else{
			jq('#bmi_val').val(k);
			jq('#bmi_txt').val('비만수치');
		}
	}
	A_bmi('visible');
	graph(k);
}
function Recal(){
	jq('#height').val('');
	jq('#weight').val('');
	jq('#waist').val('');
	jq('#hip').val('');
	jq('#age').val('');
}
function A_bmi(cond){
	if(cond=="visible"){
		document.getElementById("bmi").style.display="";
	} else {
		document.getElementById("bmi").style.display="";
	}
}
function graph(result){
	var loc = 0;
	loc = (result >= 100) ? 549 : (5.64 * result) + 10 - 15;
	document.getElementById("bmi_g_img").style.left = loc + 'px';
}
function handle(val){
	Sex = val;
	if(val==1){
		document.getElementById("gg").src="http://i1.search.daumcdn.net/s/special_search/kocn/0807_icon_graph_02_woman.gif";
	} else {
		document.getElementById("gg").src="http://i1.search.daumcdn.net/s/special_search/kocn/0807_icon_graph_02_man.gif";
	}
}
