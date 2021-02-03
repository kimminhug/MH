package com.mh.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mh.main.service.MainManagerDao;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("MainManagerDao")
public class MainManagerDao extends EgovAbstractDAO {

	public Map mainvideo(Map map) {
		// TODO Auto-generated method stub
		return (Map) select("MainManager.mainvideo", map);
	}
	public List maineventList(Map map) {
		// TODO Auto-generated method stub
		return list("MainManager.maineventList", map);
	}
	
	public List mainMenu1(Map map) {
		// TODO Auto-generated method stub
		return list("MainManager.mainMenu1", map);
	}

	public List mainMenu2(Map map) {
		// TODO Auto-generated method stub
		return list("MainManager.mainMenu2", map);
	}

	public List mainMenu3(Map map) {
		// TODO Auto-generated method stub
		return list("MainManager.mainMenu3", map);
	}
	
	public Map mainReview(Map map) {
		// TODO Auto-generated method stub
		return (Map) select("MainManager.mainReview", map);
	}
	public List mainReviewFile(Map map) {
		// TODO Auto-generated method stub
		return list("MainManager.mainReviewFile", map); 
				
	}
	public Map mainEvent(Map map) {
		// TODO Auto-generated method stub
		return (Map) select("MainManager.mainEvent", map);
	}
	public Map ServiceView(Map map) {
		// TODO Auto-generated method stub
		return (Map) select("MainManager.ServiceView", map);
	}

	
	
}
