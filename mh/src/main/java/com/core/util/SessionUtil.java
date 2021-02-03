package com.core.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.core.security.UserDetailsImpl;

/**
 * session Util
 * - Spring에서 제공하는 RequestContextHolder 를 이용하여
 * request 객체를 service까지 전달하지 않고 사용할 수 있게 해줌
 *
 */
public class SessionUtil {
    /**
     * attribute 값을 가져 오기 위한 method
     *
     * @param String  attribute key name
     * @return Object attribute obj
     */
    public static Object getAttribute(String name) throws Exception {
        return (Object)RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * attribute 설정 method
     *
     * @param String  attribute key name
     * @param Object  attribute obj
     * @return void
     */
    public static void setAttribute(String name, Object object) throws Exception {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 설정한 attribute 삭제
     *
     * @param String  attribute key name
     * @return void
     */
    public static void removeAttribute(String name) throws Exception {
        RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * session id
     *
     * @param void
     * @return String SessionId 값
     */
    public static String getSessionId() throws Exception  {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }

    /**
     * 로그인 유무 리턴
     */
    public static Boolean isLogin() throws Exception {
    	boolean isLogin = false;
    	
    	if(!StringUtil.isEmpty(getAttribute("ID"))){
    		isLogin = true;
    	}
    	
        return isLogin;
    }
    
    /**
	 * 사용자 정보 셋팅하기
	 * @param userInfo
	 */
	public static void setUserInfo(Map<String, String> userInfo) {
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    principal.setAll(userInfo);
	}
	
	/**
	 * 사용자 정보 가져오기
	 * @param request
	 * @return
	 */
	public static Map<String, String> getUserInfo() {
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    return principal.getAll();
	}
	
	/**
	 * 권한 정보 가져오기
	 * @return
	 */
	public static List<?> getAuthInfo() {
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    return (List<?>) principal.getAuthInfo();
	}
	
	/**
	 * 정보 셋팅하기
	 * @param request
	 * @param newUserInfo
	 */
	public static void set(String key, String value) {
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		principal.setAttr(key, value);
	}
	
	/**
	 * 정보 가져오기
	 * @param key
	 */
	public static String get(String key) {
		UserDetailsImpl principal = null;
		try {
			principal = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e) {
			return "";
		}
		if (principal != null) {
			return StringUtil.nullValue(principal.getAttr(key));
		} else {
			return "";
		}
	}
	
	/**
	 * 정보(숫자타입) 가져오기
	 * @param key
	 */
	public static String getAttrNumber(String key) {
		UserDetailsImpl principal = null;
		try {
			principal = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e) {
			return "";
		}
		if (principal != null) {
			return StringUtil.nullValue(principal.getAttrNumber(key));
		} else {
			return "";
		}
	}
	
	/**
	 * Session ID 가져오기
	 * @param request
	 * @return
	 */
	public static String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String sessionId = "";
		
		//Session ID get
	    if (session != null) sessionId = session.getId();
	    
	    return sessionId;
	}
}