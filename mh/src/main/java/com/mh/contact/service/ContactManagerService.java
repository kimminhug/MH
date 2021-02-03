package com.mh.contact.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ContactManagerService {

	List getContactlist(Map map)throws Exception;

	Map getContactview(Map map)throws Exception;

	Map countContactmap(Map map) throws Exception;

	List getArea2List(Map map) throws Exception;

	List getMemberInfo(Map map) throws Exception;

	void insertContact(Map map) throws Exception;

	void updateContact(Map map) throws Exception;

	List getComstateList(Map map) throws Exception;

	Map getComstateOne(Map map)throws Exception;


}
