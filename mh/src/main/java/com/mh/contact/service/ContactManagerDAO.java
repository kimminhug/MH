package com.mh.contact.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("ContactManagerDao")
public class ContactManagerDAO extends EgovAbstractDAO {

	public Map countContactMap(Map map) {
		// TODO Auto-generated method stub
		return (Map) select("contactManager.countContactMap", map);
	}
	
	public List getContactList(Map map) {
		// TODO Auto-generated method stub
		return list("contactManager.getContactList", map);
	}

	public Map getContactView(Map map) {
		// TODO Auto-generated method stub
		return (Map) select("contactManager.getContactView", map);
	}

	public List getArea2List(Map map) {
		// TODO Auto-generated method stub
		return list("contactManager.getArea2List", map);
	}

	public List getMemberInfo(Map map) {
		// TODO Auto-generated method stub
		return list("contactManager.getMemberInfo", map);
	}

	public void insertContact(Map map) {
		// TODO Auto-generated method stub
		insert("contactManager.insertContact", map);
	}

	public void updateContact(Map map) {
		// TODO Auto-generated method stub
		update("contactManager.updateContact", map);
	}

	public List getComstateList(Map map) {
		// TODO Auto-generated method stub
		return list("contactManager.getComstateList", map);
	}

	public Map getComstateOne(Map map) {
		// TODO Auto-generated method stub
		return (Map) select("contactManager.getComstateOne", map);
	}


	
	
}
