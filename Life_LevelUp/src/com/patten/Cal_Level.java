package com.patten;

public class Cal_Level {
	public boolean cal_B_level (Level_DTO dto){
		// 역할 : [비만도] 정보를 받아서 [신체 레벨]과 [신체경험치]를 계산 (경험치 = 비율 고정)
		
		double obe = dto.getObesity();
		int level = 0, exp = 0;
		
		final int L0=-100, L1=-50, L2=-45, L3=-43, L4=-41, L5=-39, L6=-37, L7=-35, L8=-33, L9=-31, L10=-29,
				  L11=-27, L12=-25, L13=-23, L14=-21, L15=-19, L16=-17, L17=-15, L18=-13, L19=-11;
		final int LH=6, H19=8, H18=10, H17=12, H16=14, H15=16, H14=18, H13=20, H12=23, H11=26, H10=30,
				  H9=35, H8=40, H7=45, H6=50, H5=55, H4=60, H3=75, H2=90, H1=300;
		
		if (obe >= LH && obe < H19){
			level = 19;
			exp = (int)((H19 - obe) / (H19 - LH) * 100);
		}else if (obe < H18){
			level = 18;
			exp = (int)((H18 - obe) / (H18 - H19) * 100);
		}else if (obe < H17){
			level = 17;
			exp = (int)((H17 - obe) / (H17 - H18) * 100);
		}else if (obe < H16){
			level = 16;
			exp = (int)((H16 - obe) / (H16 - H17) * 100);
		}else if (obe < H15){
			level = 15;
			exp = (int)((H15 - obe) / (H15 - H16) * 100);
		}else if (obe < H14){
			level = 14;
			exp = (int)((H14 - obe) / (H14 - H15) * 100);
		}else if (obe < H13){
			level = 13;
			exp = (int)((H13 - obe) / (H13 - H14) * 100);
		}else if (obe < H12){
			level = 12;
			exp = (int)((H12 - obe) / (H12 - H13) * 100);
		}else if (obe < H11){
			level = 11;
			exp = (int)((H11 - obe) / (H11 - H12) * 100);
		}else if (obe < H10){
			level = 10;
			exp = (int)((H10 - obe) / (H10 - H11) * 100);
		}else if (obe < H9){
			level = 9;
			exp = (int)((H9 - obe) / (H9 - H10) * 100);
		}else if (obe < H8){
			level = 8;
			exp = (int)((H8 - obe) / (H8 - H9) * 100);
		}else if (obe < H7){
			level = 7;
			exp = (int)((H7 - obe) / (H7 - H8) * 100);
		}else if (obe < H6){
			level = 6;
			exp = (int)((H6 - obe) / (H6 - H7) * 100);
		}else if (obe < H5){
			level = 5;
			exp = (int)((H5 - obe) / (H5 - H6) * 100);
		}else if (obe < H4){
			level = 4;
			exp = (int)((H4 - obe) / (H4 - H5) * 100);
		}else if (obe < H3){
			level = 3;
			exp = (int)((H3 - obe) / (H3 - H4) * 100);
		}else if (obe < H2){
			level = 2;
			exp = (int)((H2 - obe) / (H2 - H3) * 100);
		}else if (obe < H1){
			level = 1;
			exp = (int)((H1 - obe) / (H1 - H2) * 100);
		}
		
		if (obe < L1 && obe >= L0){
			level = 1;
			exp = (int)((obe - L0) / (L1 - L0) * 100);
		}else if (obe < L2){
			level = 2;
			exp = (int)((obe - L1) / (L2 - L1) * 100);
		}else if (obe < L3){
			level = 3;
			exp = (int)((obe - L2) / (L3 - L2) * 100);
		}else if (obe < L4){
			level = 4;
			exp = (int)((obe - L3) / (L4 - L3) * 100);
		}else if (obe < L5){
			level = 5;
			exp = (int)((obe - L4) / (L5 - L4) * 100);
		}else if (obe < L6){
			level = 6;
			exp = (int)((obe - L5) / (L6 - L5) * 100);
		}else if (obe < L7){
			level = 7;
			exp = (int)((obe - L6) / (L7 - L6) * 100);
		}else if (obe < L8){
			level = 8;
			exp = (int)((obe - L7) / (L8 - L7) * 100);
		}else if (obe < L9){
			level = 9;
			exp = (int)((obe - L8) / (L9 - L8) * 100);
		}else if (obe < L10){
			level = 10;
			exp = (int)((obe - L9) / (L10 - L9) * 100);
		}else if (obe < L11){
			level = 11;
			exp = (int)((obe - L10) / (L11 - L10) * 100);
		}else if (obe < L12){
			level = 12;
			exp = (int)((obe - L11) / (L12 - L11) * 100);
		}else if (obe < L13){
			level = 13;
			exp = (int)((obe - L12) / (L13 - L12) * 100);
		}else if (obe < L14){
			level = 14;
			exp = (int)((obe - L13) / (L14 - L13) * 100);
		}else if (obe < L15){
			level = 15;
			exp = (int)((obe - L14) / (L15 - L14) * 100);
		}else if (obe < L16){
			level = 16;
			exp = (int)((obe - L15) / (L16 - L15) * 100);
		}else if (obe < L17){
			level = 17;
			exp = (int)((obe - L16) / (L17 - L16) * 100);
		}else if (obe < L18){
			level = 18;
			exp = (int)((obe - L17) / (L18 - L17) * 100);
		}else if (obe < L19){
			level = 19;
			exp = (int)((obe - L18) / (L19 - L18) * 100);
		}else if (obe < LH){
			level = 20;
			exp = 100;
		}
		
		if (level == 0){
			return false;
		}
		
		dto.setB_level(level);
		dto.setB_exp(exp);
		return true;
	}
	
	public boolean cal_E_level (Level_DTO dto){
		// 역할 : [운동 경험치]정보를 받아서 [운동 레벨], [베이스 경험치], [요구 경험치], [경험치 비율]을 계산
		
		int exp = dto.getE_exp();
		int level = 0;
		int bas_exp = 0, req_exp = 0, rate = 0;
		
		int[] base = new int[30];
		int[] base2 = new int[30];
		int[] req = new int[30];
		int[] total = new int[31];
		
		base[1] = 1;
		base2[1] = 1;
		req[1] = 10;
		total[0] = 0; total[1] = 50;
		
		for(int i=2; i<30; i++){
			base[i] = base[i-1] + 1;
			base2[i] = base2[i-1] + base[i];
			req[i] = req[i-1] + base2[i];
			total[i] = total[i-1] + req[i]; 
		}
		
		for (int i=1; i<=30; i++){
			bas_exp = total[i - 1];
			req_exp = total[i];
			
			if (exp >= bas_exp && exp < req_exp){
				level = i;
				rate = (int)((double)(exp - bas_exp) / (double)(req_exp - bas_exp) * 100);
				break;
			}else if (i == 30){
				level = 30;
				rate = 100;
				bas_exp = req_exp;
			}
		}
		
		dto.setE_level(level);
		dto.setE_bas_exp(bas_exp);
		dto.setE_req_exp(req_exp);
		dto.setE_rate(rate);	
			
		
		 //	< 경험치 계산 테스트 >
		System.out.println("로그인된 내 운동경험치 bas : "+dto.getE_bas_exp());
		System.out.println("로그인된 내 운동경험치 req : "+dto.getE_req_exp());
		System.out.println("로그인된 내 운동경험치 비율 : "+dto.getE_rate());
		System.out.println("로그인된 내 운동경험치: "+dto.getE_exp());
		System.out.println("로그인된 내 운동레벨: "+dto.getE_level());
		
		return true;
	}
}
