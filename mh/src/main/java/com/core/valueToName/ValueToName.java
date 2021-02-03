package com.core.valueToName;

import java.util.LinkedHashMap;

public abstract class ValueToName {
	//업체관리
	public static LinkedHashMap<Integer , String> PTAREA; //주 시공지역
	public static LinkedHashMap<String , String> LICENSEYN; //라이센스발급여부
	public static LinkedHashMap<String , String> EXCELLENT; //우수업체여부
	public static LinkedHashMap<String , String> APPPUSHYN; // 앱푸쉬허용여부
	public static LinkedHashMap<String , String> JOINSTATE; //가입상태
	//리뷰관리
	public static LinkedHashMap<String , String> BEFAFTER; //청소여부
	public static LinkedHashMap<String , String> BESTREVIEW; //베스트신청
	public static LinkedHashMap<String , String> RECOMMEND; //추천,상태여부
	//이벤트광고 관리
	public static LinkedHashMap<String , String> NOTICEFN; //노출여부
	public static LinkedHashMap<String , String> EVPUBSECTION; //이벤트/광고 선택
	//공동구매
	public static LinkedHashMap<Integer , String> GRPSTATE; //신청상태
	public static LinkedHashMap<String , String> SELAREA; //공구지역(대도시 선택용)
	//시공관리
	public static LinkedHashMap<Integer , String> CLCATEGORY; //시공분류
	public static LinkedHashMap<String , String> CLDISPLAY; //시공노출여뷰
	
	//요금관리
	public static LinkedHashMap<String , String> CLCATEGORY2; //사이즈
	
	// 시공종류
	public static LinkedHashMap<String , String> CL_TYPE; 
	
	public static LinkedHashMap<String , String> BUILDCONTENT; //업체실적 시공내용
	
	
	static{
		//업체관리
		PTAREA = new LinkedHashMap<Integer, String>();
		PTAREA.put(0, "전국");
		PTAREA.put(1, "서울/인천/경기/세종");
		PTAREA.put(2, "대전/충북/충남");
		PTAREA.put(3, "광주/전북/전남");
		PTAREA.put(4, "대구/울산/경북");
		PTAREA.put(5, "부산/경남");
		PTAREA.put(6, "강원도");
		PTAREA.put(7, "제주/그 외 지역");
		
		LICENSEYN =new LinkedHashMap<String, String>();
		LICENSEYN.put("y", "라이센스 발급");
		LICENSEYN.put("n", "라이센스 미발급");
		
		EXCELLENT =new LinkedHashMap<String, String>();
		EXCELLENT.put("y", "베스트업체");
		EXCELLENT.put("n", "일반업체");
		
		APPPUSHYN =new LinkedHashMap<String, String>();
		APPPUSHYN.put("y", "허용");
		APPPUSHYN.put("n", "거부");
		
		JOINSTATE =new LinkedHashMap<String, String>();
		JOINSTATE.put("y", "가입완료");
		JOINSTATE.put("w", "가입신청중");
		JOINSTATE.put("n", "가입거부");
		
		//리뷰관리
		BEFAFTER = new LinkedHashMap<String, String>();
		BEFAFTER.put("s",  "-선택");
		BEFAFTER.put("y",  "청소완료");
		BEFAFTER.put("w", "진행중");
		BEFAFTER.put("n",  "청소전");
		
		BESTREVIEW = new LinkedHashMap<String, String>();
		BESTREVIEW.put("y",  "베스트");
		BESTREVIEW.put("w", "베스트신청");
		BESTREVIEW.put("n",  "신청거절");
		BESTREVIEW.put("s",  "일반");
		
		RECOMMEND = new LinkedHashMap<String, String>();
		RECOMMEND.put("s",  "선택");
		RECOMMEND.put("y",  "추천");
		RECOMMEND.put("n", "일반");
		RECOMMEND.put("h", "숨기기");
		
		//이벤트광고
		NOTICEFN = new LinkedHashMap<String, String>();
		NOTICEFN.put("s",  "선택");
		NOTICEFN.put("y",  "노출");
		NOTICEFN.put("n",  "미노출");
		
		
		EVPUBSECTION = new LinkedHashMap<String, String>();
		EVPUBSECTION.put("s",  "선택");
		EVPUBSECTION.put("e",  "이벤트");
		EVPUBSECTION.put("a",  "광고");
		
		//공동구매
		GRPSTATE =new LinkedHashMap<Integer, String>();
		GRPSTATE.put(0, "승인전");
		GRPSTATE.put(1, "모집중");
		GRPSTATE.put(2, "매칭대기");
		GRPSTATE.put(3, "진행중");
		GRPSTATE.put(4, "공구종료");
		GRPSTATE.put(5, "공구취소");
		
		SELAREA  =new LinkedHashMap<String, String>();
		SELAREA.put("s","지역선택");
		SELAREA.put("a1","서울특별시");
		SELAREA.put("a2","인천광역시");
		SELAREA.put("b1","대전광역시");
		SELAREA.put("c1","광주광역시");
		SELAREA.put("d1","대구광역시");
		SELAREA.put("d2","울산광역시");
		SELAREA.put("e1","부산광역시");
		SELAREA.put("a4","세종특별자치시");
		SELAREA.put("a3","경기도");
		SELAREA.put("f1","강원도");
		SELAREA.put("b2","충청북도");
		SELAREA.put("b3","충청남도");
		SELAREA.put("d3","경상북도");
		SELAREA.put("e2","경상남도");
		SELAREA.put("c2","전라북도");
		SELAREA.put("c3","전라남도");
		SELAREA.put("g1","제주특별자치도");
		
		//시공관리
		CLCATEGORY =new LinkedHashMap<Integer, String>();
		CLCATEGORY.put(0, "선택");
		CLCATEGORY.put(1, "일반청소(모든청소)");
		CLCATEGORY.put(2, "기타청소(기타시공)");
		CLCATEGORY.put(3, "패키지시공(일반+기타)");
		
		//시공관리
		/*CLCATEGORY =new LinkedHashMap<Integer, String>();
		CLCATEGORY.put(0, "선택");
		CLCATEGORY.put(1, "홈클리닝");
		CLCATEGORY.put(2, "오피스클리닝");
		CLCATEGORY.put(3, "스페셜클리닝");*/
		
		//시공관리
		CLCATEGORY2 =new LinkedHashMap<String, String>();
		CLCATEGORY2.put("c", "선택");
		CLCATEGORY2.put("s", "20평 이하 S");
		CLCATEGORY2.put("n", "20평 초과 50평 미만 N");
		CLCATEGORY2.put("l", "50평 이상 L");
		CLCATEGORY2.put("etc", "기타");
		
		CLDISPLAY = new LinkedHashMap<String, String>();
		CLDISPLAY.put("s",  "선택");
		CLDISPLAY.put("y",  "노출");
		CLDISPLAY.put("n",  "미노출");
		
		// 시공종류
		CL_TYPE = new LinkedHashMap<String, String>();
		CL_TYPE.put("0",  "선택");
		CL_TYPE.put("1",  "노출");
		CL_TYPE.put("2",  "미노출");
		CL_TYPE.put("1",  "일반청소-전체");
		CL_TYPE.put("2",  "기타시공-전체");
		CL_TYPE.put("3",  "일반청소-홈클리닝");
		CL_TYPE.put("4",  "일반청소-오피스클리닝");
		CL_TYPE.put("5",  "기타시공-정기청소");
		CL_TYPE.put("6",  "기타시공-줄눈시공");
		CL_TYPE.put("7",  "기타시공-상판연마");
		CL_TYPE.put("8",  "기타시공-피톤치드");
		CL_TYPE.put("9",  "기타시공-바이오세라믹/탄성시트");
		CL_TYPE.put("10",  "기타시공-광택");
		CL_TYPE.put("11",  "기타시공-미세/안전");
		CL_TYPE.put("12",  "기타시공-방충망");
		CL_TYPE.put("13",  "기타시공-세탁조 및 에어컨 필터");
		CL_TYPE.put("14",  "기타시공-기타시공");
		CL_TYPE.put("15",  "패키지시공-일반+기타시공");
		CL_TYPE.put("16",  "일반청소-부가시공");
		
		// 업체실적 시공내용
		BUILDCONTENT =new LinkedHashMap<String, String>();
		BUILDCONTENT.put("B1", "이사청소");
		BUILDCONTENT.put("B2", "입주청소");
		BUILDCONTENT.put("B3", "줄눈시공");
	}
}
