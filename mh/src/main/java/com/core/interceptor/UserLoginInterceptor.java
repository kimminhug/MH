package com.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	 public boolean preHandle(HttpServletRequest request
			, HttpServletResponse response
			, Object handler
			)throws Exception {
		System.out.println("인터셉터"+request.getSession().getAttribute("uloginInfo"));
	  try{
		   //로그인상태가 아니면 홈으로 이동시킨다.
		   if (request.getSession().getAttribute("uloginInfo") == null){
		    // 프로젝트의 Context Path명을 반환하고 그 경로에 /user/needLogin.do를 추가한다.
		    // 적용 예 >> http://localhost:8080/myFirst2/user/needLogin.do
		    response.sendRedirect(request.getContextPath() + "/user/main.li");
		    return false;
		   }else{
		    return true;
		   }
	  }catch (Exception e){
		  e.printStackTrace();
	  	}
	  return super.preHandle(request, response, handler);
	 }
	 
	 @Override
	 public void postHandle(HttpServletRequest request
			 , HttpServletResponse response
			 , Object handler
			 , ModelAndView modelAndView
			 ) throws Exception {
	  // TODO Auto-generated method stub
	  super.postHandle(request, response, handler, modelAndView);
	 }
}
