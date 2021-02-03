package com.core.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.core.util.CommonUtil;

import egovframework.rte.psl.orm.ibatis.SqlMapClientTemplate;

public class ComUserDetailsService implements UserDetailsService {
	
    private final Log logger = LogFactory.getLog(getClass());

    private final String DIVISION_PORTAL = "DGISTPORTAL";
    
	@Autowired
	protected SqlMapClientTemplate sqlMap;
	
	public ComUserDetailsService() {
		System.out.println("DgistUserDetailsService() Initialize");
	}

	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String paramString) throws UsernameNotFoundException {
		Map<String, String> paramUser = CommonUtil.getParamUser(paramString, ";", "=");
		
		String username  = paramUser.get("username");
		String division  = paramUser.get("division");
		String selection = paramUser.get("selection");
		String language  = paramUser.get("language");
		String userIpAddr= paramUser.get("useripaddr");
		String userrole	 = paramUser.get("userrole");
		this.logger.debug("paramUser username  : " + username);
		this.logger.debug("paramUser division  : " + division);
		this.logger.debug("paramUser selection : " + selection);
		this.logger.debug("paramUser language  : " + language);
		this.logger.debug("paramUser userIpAddr: " + userIpAddr);
		this.logger.debug("paramUser userrole  : " + userrole);
		
		System.out.println("paramUser username  : " + username);
		System.out.println("paramUser division  : " + division);
		System.out.println("paramUser selection : " + selection);
		System.out.println("paramUser language  : " + language);
		System.out.println("paramUser userIpAddr: " + userIpAddr);
		System.out.println("paramUser userrole  : " + userrole);
		
		UserDetailsImpl userDetails = null;
		Map<String, String> user = null;
		Map<String, String> rnduser = null;
		List<?> persBg = null;
		List<?> sttsMenu = null;	
		List<String> allDetpCd = null;
		List<String> deptDetpCd = null;
		List<String> deptlwDetpCd = null;
		List<String> persDetpCd = null;
		List<String> deptAcctCd = null;
		try {
			Map<String, String> loginMap = new HashMap<String, String>();
			loginMap.put("username", username);
			loginMap.put("selection", selection);
			user = (Map<String, String>)this.sqlMap.queryForObject("security.retrieveUser", loginMap);

			String stts_no = null;
			if(user != null) {
				stts_no = user.get("STTS_NO");
			}
			
			//연구원 정보
			rnduser = (Map<String, String>)this.sqlMap.queryForObject("security.retrieveRndUser", stts_no);
			if (rnduser != null) {
				user.putAll(rnduser);
			}
			
			// master pwd 로그인 체크
			user.put("userrole", userrole);
			
			this.logger.debug("memberNo :: " + stts_no);
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.clear();
			paramMap.put("stts_no" , stts_no);
			paramMap.put("language", language.substring(0,2));
			
			if(stts_no != null) {
				persBg       = (List<HashMap<String, String>>)this.sqlMap.queryForList("security.retrievePersBg", paramMap);
				sttsMenu     = (List<HashMap<String, String>>)this.sqlMap.queryForList("security.retrieveSttsMenu", paramMap);
				allDetpCd    = (List<String>)this.sqlMap.queryForList("security.retrieveAllDept", stts_no);
				deptDetpCd   = (List<String>)this.sqlMap.queryForList("security.retrieveDeptDept", stts_no);
				deptlwDetpCd = (List<String>)this.sqlMap.queryForList("security.retrieveDeptlwDept", stts_no);
				persDetpCd   = (List<String>)this.sqlMap.queryForList("security.retrievePersDept", stts_no);
				deptAcctCd 	 = (List<String>)this.sqlMap.queryForList("security.retrieveDeptAcct", stts_no);
			}
		} catch(DataAccessException e) {
			this.logger.error(e);
			throw new AuthenticationServiceException(e.getMessage(), e);
		}
		
		if(user == null) {
			throw new UsernameNotFoundException("UserDetailsService returned null, which is an interface contract violation");
		}
		
		String intgLoginId = user.get("LOGIN_ID");
		String intgPwd = user.get("PWD");
		
		if (division.equals(DIVISION_PORTAL) || division.equals("DGISTSSO")) {
			userDetails = new UserDetailsImpl(paramString, "");
		} else {
			userDetails = new UserDetailsImpl(intgLoginId, intgPwd);
		}
		
		/*
		this.logger.debug("bizNos :: " + bizNos);
		if(bizNos != null && bizNos.size() > 0) {
			for(String authority : bizNos) {
				userDetails.addAuthority(authority);
			}
		}*/
		
		Map<String, String> authDeptCd = new HashMap<String, String>();
		String ALL = inAuthDeptCd(allDetpCd);
		String DEPT = inAuthDeptCd(deptDetpCd);
		String DEPTLW = inAuthDeptCd(deptlwDetpCd);
		String PERS = inAuthDeptCd(persDetpCd);
		String DEPTACCT = inAuthDeptCd(deptAcctCd);
		
		authDeptCd.put("ALL", ALL);
		authDeptCd.put("DEPT", DEPT);
		authDeptCd.put("DEPTLW", DEPTLW);
		authDeptCd.put("PERS", PERS);
		authDeptCd.put("DEPTA", DEPTACCT);
		
		//포털 여부 확인용
		userDetails.setAttr("division", division);
		
		userDetails.setAuthDeptCd(authDeptCd); //부서권한정보
		userDetails.setAuthInfo(persBg); //개인별권한정보
		userDetails.setSttsMenuInfo(sttsMenu); //개인별권한정보
		userDetails.setAll(user); //사용자정보
		
		userDetails.setAccountNonExpired(true);
		userDetails.setAccountNonLocked(true);
		userDetails.setCredentialsNonExpired(true);
		userDetails.setEnabled(true);

		return userDetails;
	}
	
	/**
	 * 부서권한 코드 IN절 문자 조합
	 * @param persDetpCd
	 * @return
	 * @throws Exception
	 */
	private String inAuthDeptCd(List<String> persDetpCd) {
		String authDeptCd = "";
		authDeptCd += "(";
		for(String deptCd : persDetpCd) {
			authDeptCd += "'"+deptCd+"',";
		}
		authDeptCd += "''";
		authDeptCd += ")";
		return authDeptCd;
	}

}
