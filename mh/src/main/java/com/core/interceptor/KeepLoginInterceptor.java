package com.core.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.core.util.CookieUtil;
import com.mh.login.service.LoginManagerService;

public class KeepLoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	LoginManagerService service ;

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		System.out.println("KeepLoginInterceptor :::::::::::::: preHandle!!!!!!! ");
		
		HttpSession session = request.getSession();
		//LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> sessLoginVO = (Map<String, Object>) session.getAttribute("loginInfo");
		String getCookie = CookieUtil.getCookieValue(request, "keepLog");
		
		//로그인 유지 상태면 강제로 session set
		if(sessLoginVO == null && getCookie != null && !getCookie.equals("")){
			System.out.println(sessLoginVO + "sess null ~~~~~~~~~~~~~~ cookie you " + getCookie);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("SESSID", getCookie);
			Map<String, Object> logInfo = service.selectKeepLogInfo(map);
			
			session.setAttribute("loginInfo", logInfo);
		}
		
		return true;
	 }
	 
	 @Override
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		 // super.postHandle(request, response, handler, modelAndView);
		 System.out.println("KeepLoginInterceptor :::::::::::::: postHandle!!!!!!! ");

		 HttpSession session = request.getSession();
		 String getCookie = CookieUtil.getCookieValue(request, "userKeepLog");
		 //System.out.println(getCookie + " ~~~~ " + CookieUtil.isCookieValid(request, "userKeepLog"));
		 if(CookieUtil.isCookieValid(request, "userKeepLog") == true && getCookie.equals("Y")){
			 Cookie keepLogCookie = new Cookie("keepLog", session.getId());
			 keepLogCookie.setPath("/");
			 keepLogCookie.setMaxAge(60*60*24*7);	//1주일간 보관
			 response.addCookie(keepLogCookie);
		 }
	 }

}
