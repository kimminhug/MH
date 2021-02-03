package com.core.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.core.util.CommonUtil;

public class BizInterceptor extends HandlerInterceptorAdapter{
	private static Log logger = LogFactory.getLog(CommonUtil.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		System.out.println("BizInterceptor :::::::::::::: preHandle!!!!!!! ");
		
		/*boolean isPermittedURL = false;
		HttpSession session = request.getSession();
		//LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Map<String, Object> sessLoginVO = (Map<String, Object>) session.getAttribute("loginInfo");
		
		//System.out.println("auth inter :::::::::::::::::::::: " + loginVO);
		//System.out.println("auth inter sessLoginVO :::::::::::::::::::::: " + sessLoginVO);
		
		if(sessLoginVO != null){
			logger.info(sessLoginVO.get("usid") + " Login Success");
			
			return true;
		} else if(!isPermittedURL){
			ModelAndView modelAndView = new ModelAndView("redirect:/admin/admin.li");
			throw new ModelAndViewDefiningException(modelAndView);
		}else{
		}*/
		return true;
	 }
	 
	 @Override
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		 super.postHandle(request, response, handler, modelAndView);
		 System.out.println("BizInterceptor :::::::::::::: postHandle!!!!!!! ");
	 }

}
