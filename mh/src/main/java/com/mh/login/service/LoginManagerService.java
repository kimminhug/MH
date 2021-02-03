package com.mh.login.service;

import java.util.Date;
import java.util.Map;

public interface LoginManagerService {

	public Map<String, Object> selectUserInfo(Map<String, Object> map)throws Exception;

	public Map<String, Object> selectKeepLogInfo(Map<String, Object> map) throws Exception;

	public void keepLogin(String userId, String sessId, Date sessLimit) throws Exception;

}
