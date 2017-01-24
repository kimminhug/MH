function cal(){
	var height, weight, age, sex;
	var BMI, BMR, obesity, average;
	
	height = $("#height option:selected").val();
	weight = $("#weight option:selected").val();
	age = $("#age option:selected").val();
	
	BMI = weight / (height * height / 10000);
	obesity = (weight - average) / average * 100;
	
	if(sex==0 || sex==null){
		average = (weight - 100) * 0.9;
		BMR = 66 + (13.7 * weight) + (5 * height) - (6.8 * age) ;
		
	}else{
		average = (weight - 100) * 0.85;
		BMR = 655 + (9.6 * weight) + (1.7 * height) - (4.7 * age);
	}
}

