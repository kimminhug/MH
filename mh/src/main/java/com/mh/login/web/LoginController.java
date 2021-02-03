package com.mh.login.web;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.core.model.Bind;
import com.core.util.CommonUtil;
import com.core.util.CookieUtil;
import com.core.util.SecurityUtil;
import com.core.util.StringUtil;
import com.mh.login.service.LoginManagerService;

@Controller
public class LoginController {
	@Autowired
	LoginManagerService service;

	@RequestMapping(value = "/login.do")
	public ModelAndView LoginMain(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("----------------LoginController진입------------------");
		ModelAndView mv = new ModelAndView();
		// 세션정보가 null이 아닐 때
		if (request.getSession().getAttribute("loginInfo") != null) {
			mv.setViewName("redirect:/main.do");
		} else {
			mv.setViewName("/login/login.user");
		}
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/logintry.do")
	public void UserLogin(ModelMap model, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.println("----------------LoginController진입 : logintry------------------");
		
		Bind bind = new Bind(request);
		Map<String, Object> bindMap = bind.getDto();
		SecurityUtil security = new SecurityUtil();
		HttpSession session = request.getSession();
		Random random = new Random();
		String msg = "";
		
		String pwd = security.encryptSHA256((String) bindMap.get("pwd"));
		bindMap.put("pwd", pwd);

		Map<String, Object> map = service.selectUserInfo(bindMap);
		if (map == null) {
			msg = "로그인에 실패하였습니다.";
			CommonUtil.jsonResponse(response, "fail", msg);
		} else {
			session.setAttribute("loginInfo", map);
			//session.setMaxInactiveInterval(60 * 60 * 24);	//초단위 (1일)

			System.out.println("login keep :::::!!!!!!!! " + bindMap.get("logKeep"));
			
			// 자동로그인 체크 시 쿠키에 암호화된 무작위ID 추가 및 회원정보에 업데이트 - 이후 해당 ID로 자동로그인
			if(StringUtil.nullValue((String) bindMap.get("logKeep")).equals("Y")){
				String sess_id = security.encryptSHA256(Integer.toString(random.nextInt()));
				
				Date sess_limit = new Date(System.currentTimeMillis() + (60*60*24*7*1000));
				service.keepLogin(StringUtil.nullValue(map.get("usid")), sess_id, sess_limit);
				
				Cookie cookie = new Cookie("sess_id", sess_id);
				cookie.setMaxAge(60*60*24*7*1000);
				
				response.addCookie(cookie);
				System.out.println("session update success");
			}
			
			CommonUtil.jsonResponse(response, "success", "");
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/logouttry.do")
	public ModelAndView UserLogout(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("----------------LogoutController진입 : logouttry------------------");
		HttpSession session = request.getSession();
		Map<String, Object> sessLoginVO = (Map<String, Object>) session.getAttribute("loginInfo");

		session.removeAttribute("loginInfo");
		session.invalidate();
		
		// 쿠키 및 저장된 유저세션정보 초기화
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("sess_id")){
					service.keepLogin(StringUtil.nullValue(sessLoginVO.get("usid")), "", new Date());
					
					cookie.setMaxAge(0);
					cookie.setPath("/");
					cookie.setValue("");
					response.addCookie(cookie);
				}
			}
		}
		
		ModelAndView mv = new ModelAndView("main/main.user");
		mv.addObject("msg", "로그아웃되었습니다.");

		return mv;
	}

	
}
