package com.mh.login.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.login.service.LoginManagerService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("LoginManagerService")
public class LoginManagerServiceImpl extends EgovAbstractServiceImpl implements LoginManagerService{
	
	@Autowired
	private LoginManagerDAO dao;

	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> map) throws Exception {
		return dao.selectUserInfo(map);
	}
	
	@Override
	public Map<String, Object> selectKeepLogInfo(Map<String, Object> map) throws Exception {
		return dao.selectKeepLogInfo(map);
	}

	@Override
	public void keepLogin(String userId, String sessId, Date sessLimit) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("USERID", userId);
		map.put("SESSID", sessId);
		map.put("SESSLIMIT", sessLimit);
		
		dao.keepLogin(map);
	}
}
