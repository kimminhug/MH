package com.mh.main.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface MainManagerService {

	Map mainvideo(Map map)throws Exception;
	List maineventList(Map map)throws Exception;

	List mainMenu(Map map)throws Exception;
	
	Map mainReview(Map map)throws Exception;
	List mainReviewFile(Map map)throws Exception;
	Map mainEvent(Map map)throws Exception;
	
	Map ServiceView(Map map)throws Exception;



}
