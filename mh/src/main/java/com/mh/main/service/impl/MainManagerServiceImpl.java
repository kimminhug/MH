package com.mh.main.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.main.service.MainManagerDao;
import com.mh.main.service.MainManagerService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MainManagerService")
public class MainManagerServiceImpl extends EgovAbstractServiceImpl implements MainManagerService {
	@Autowired
	MainManagerDao dao;

	@Override
	public Map mainvideo(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.mainvideo(map);
	}
	@Override
	public List maineventList(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.maineventList(map);
	}
	
	@Override
	public List mainMenu(Map map) throws Exception {
		// TODO Auto-generated method stub
		int Menu = Integer.parseInt((String)map.get("menu"));
		map.put("menu", Menu);
		if (map.get("menu").equals(0)) {
			return dao.mainMenu1(map);
		}else if (map.get("menu").equals(1)) {
			return dao.mainMenu2(map);
		}else if (map.get("menu").equals(2)) {
			return dao.mainMenu3(map);
		}
		
		
		return null;
	}
	@Override
	public Map mainReview(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.mainReview(map);
	}
	@Override
	public List mainReviewFile(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.mainReviewFile(map);
	}
	@Override
	public Map mainEvent(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.mainEvent(map);
	}
	@Override
	public Map ServiceView(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.ServiceView(map);
	}
	
	
	
}
