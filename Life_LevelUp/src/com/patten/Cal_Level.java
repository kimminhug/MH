package com.patten;

public class Cal_Level {
	public boolean cal_b_level (Level_DTO dto){
		double obe = dto.getObesity();
		int level = 0;
		double exp = 0;
		
		final int L0=-100, L1=-50, L2=-45, L3=-43, L4=-41, L5=-39, L6=-37, L7=-35, L8=-33, L9=-31, L10=-29,
				  L11=-27, L12=-25, L13=-23, L14=-21, L15=-19, L16=-17, L17=-15, L18=-13, L19=-11;
		final int LH=6, H19=8, H18=10, H17=12, H16=14, H15=16, H14=18, H13=20, H12=23, H11=26, H10=30,
				  H9=35, H8=40, H7=45, H6=50, H5=55, H4=60, H3=75, H2=90, H1=300;
		
		if (obe >= LH && obe < H19){
			level = 19;
			exp = (H19 - obe) / (H19 - LH) * 100;
		}else if (obe < H18){
			level = 18;
			exp = (H18 - obe) / (H18 - H19) * 100;
		}else if (obe < H17){
			level = 17;
			exp = (H17 - obe) / (H17 - H18) * 100;
		}else if (obe < H16){
			level = 16;
			exp = (H16 - obe) / (H16 - H17) * 100;
		}else if (obe < H15){
			level = 15;
			exp = (H15 - obe) / (H15 - H16) * 100;
		}else if (obe < H14){
			level = 14;
			exp = (H14 - obe) / (H14 - H15) * 100;
		}else if (obe < H13){
			level = 13;
			exp = (H13 - obe) / (H13 - H14) * 100;
		}else if (obe < H12){
			level = 12;
			exp = (H12 - obe) / (H12 - H13) * 100;
		}else if (obe < H11){
			level = 11;
			exp = (H11 - obe) / (H11 - H12) * 100;
		}else if (obe < H10){
			level = 10;
			exp = (H10 - obe) / (H10 - H11) * 100;
		}else if (obe < H9){
			level = 9;
			exp = (H9 - obe) / (H9 - H10) * 100;
		}else if (obe < H8){
			level = 8;
			exp = (H8 - obe) / (H8 - H9) * 100;
		}else if (obe < H7){
			level = 7;
			exp = (H7 - obe) / (H7 - H8) * 100;
		}else if (obe < H6){
			level = 6;
			exp = (H6 - obe) / (H6 - H7) * 100;
		}else if (obe < H5){
			level = 5;
			exp = (H5 - obe) / (H5 - H6) * 100;
		}else if (obe < H4){
			level = 4;
			exp = (H4 - obe) / (H4 - H5) * 100;
		}else if (obe < H3){
			level = 3;
			exp = (H3 - obe) / (H3 - H4) * 100;
		}else if (obe < H2){
			level = 2;
			exp = (H2 - obe) / (H2 - H3) * 100;
		}else if (obe < H1){
			level = 1;
			exp = (H1 - obe) / (H1 - H2) * 100;
		}
		
		if (obe < L1 && obe >= L0){
			level = 1;
			exp = (obe - L0) / (L1 - L0) * 100;
		}else if (obe < L2){
			level = 2;
			exp = (obe - L1) / (L2 - L1) * 100;
		}else if (obe < L3){
			level = 3;
			exp = (obe - L2) / (L3 - L2) * 100;
		}else if (obe < L4){
			level = 4;
			exp = (obe - L3) / (L4 - L3) * 100;
		}else if (obe < L5){
			level = 5;
			exp = (obe - L4) / (L5 - L4) * 100;
		}else if (obe < L6){
			level = 6;
			exp = (obe - L5) / (L6 - L5) * 100;
		}else if (obe < L7){
			level = 7;
			exp = (obe - L6) / (L7 - L6) * 100;
		}else if (obe < L8){
			level = 8;
			exp = (obe - L7) / (L8 - L7) * 100;
		}else if (obe < L9){
			level = 9;
			exp = (obe - L8) / (L9 - L8) * 100;
		}else if (obe < L10){
			level = 10;
			exp = (obe - L9) / (L10 - L9) * 100;
		}else if (obe < L11){
			level = 11;
			exp = (obe - L10) / (L11 - L10) * 100;
		}else if (obe < L12){
			level = 12;
			exp = (obe - L11) / (L12 - L11) * 100;
		}else if (obe < L13){
			level = 13;
			exp = (obe - L12) / (L13 - L12) * 100;
		}else if (obe < L14){
			level = 14;
			exp = (obe - L13) / (L14 - L13) * 100;
		}else if (obe < L15){
			level = 15;
			exp = (obe - L14) / (L15 - L14) * 100;
		}else if (obe < L16){
			level = 16;
			exp = (obe - L15) / (L16 - L15) * 100;
		}else if (obe < L17){
			level = 17;
			exp = (obe - L16) / (L17 - L16) * 100;
		}else if (obe < L18){
			level = 18;
			exp = (obe - L17) / (L18 - L17) * 100;
		}else if (obe < L19){
			level = 19;
			exp = (obe - L18) / (L19 - L18) * 100;
		}else if (obe < LH){
			level = 20;
			exp = (obe - L19) / (LH - L19) * 100;
		}
		
		if (level == 0){
			return false;
		}
		
		dto.setB_level(level);
		dto.setB_exp(exp);
		return true;
	}
}
