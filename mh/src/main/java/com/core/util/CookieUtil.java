package com.core.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.core.security.UserDetailsImpl;


public class CookieUtil {
	/**
	 * Cookie value 가져오기
	 * @param request
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String cookiename) {
		Cookie[] cookies = request.getCookies();
		String cookieVal = "";
		
		if(cookies != null){
			for(int i = 0 ; i<cookies.length; i++){ 
				if(cookies[i].getName().equals(cookiename)){
					cookieVal = StringUtil.nullValue(cookies[i].getValue());
				}
			}
		}
		
	    return cookieVal;
	}

	/**
	 * Cookie set
	 * @param request
	 * @return
	 */
	public static void setCookieValue(String cookiename, String cookievalue, int expiredtime) {
		Cookie cookie = new Cookie(cookiename, cookievalue);
		cookie.setPath("/");
		cookie.setMaxAge(expiredtime);
	}
	
	/**
	 * Cookie delete
	 * @param request
	 * @return
	 */
	public static void removeCookie(HttpServletRequest request, String cookiename) {
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null){
			for(int i = 0 ; i<cookies.length; i++){ 
				if(cookies[i].getName().equals(cookiename)){
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
				}
			}
		}
	}
	
	/**
	 * 유효한 쿠키명인지 확인
	 * @param request
	 * @return
	 */
	public static boolean isCookieValid(HttpServletRequest request, String cookiename) {
		Cookie[] cookies = request.getCookies();
		boolean isValid = false;
		
		if(cookies != null){
			 for(int i = 0 ; i<cookies.length; i++){ 
				 if(cookies[i].getName().equals(cookiename)){
					 isValid = true;
				 }
			 }
		 }
		
	    return isValid;
	}
}