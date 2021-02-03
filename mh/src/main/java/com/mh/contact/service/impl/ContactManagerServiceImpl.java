package com.mh.contact.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.contact.service.ContactManagerDAO;
import com.mh.contact.service.ContactManagerService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ContactManagerService")
public class ContactManagerServiceImpl extends EgovAbstractServiceImpl implements ContactManagerService {
	@Autowired
	ContactManagerDAO dao;

	@Override
	public Map countContactmap(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.countContactMap(map);
	}
	
	@Override
	public List getContactlist(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.getContactList(map);
	}

	@Override
	public Map getContactview(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.getContactView(map);
	}

	@Override
	public List getArea2List(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.getArea2List(map);
	}

	@Override
	public List getMemberInfo(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.getMemberInfo(map);
	}

	@Override
	public void insertContact(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.insertContact(map);
	}

	@Override
	public void updateContact(Map map) throws Exception {
		// TODO Auto-generated method stub
		dao.updateContact(map);
	}

	@Override
	public List getComstateList(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.getComstateList(map);
	}

	@Override
	public Map getComstateOne(Map map) throws Exception {
		// TODO Auto-generated method stub
		return dao.getComstateOne(map);
	}



	
	
}
