package com.mh.admin.main.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.admin.main.service.AdminMainManagerDao;
import com.mh.admin.main.service.AdminMainManagerService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("AdminMainManagerService")
public class AdminMainManagerServiceImpl extends EgovAbstractServiceImpl implements AdminMainManagerService {
	@Autowired
	AdminMainManagerDao dao;
	
}
