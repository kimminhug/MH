package com.mh.login.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("LoginManagerDAO")
public class LoginManagerDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectUserInfo(Map<String, Object> map) {
		return (Map<String, Object>) select("LoginManager.selectUserInfo", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectKeepLogInfo(Map<String, Object> map) {
		return (Map<String, Object>) select("LoginManager.selectKeepLogInfo", map);
	}
	
	public void keepLogin(Map<String, Object> map){
		update("LoginManager.keepLogin", map);
	}
}
