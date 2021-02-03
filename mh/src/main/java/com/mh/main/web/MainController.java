package com.mh.main.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.core.util.CookieUtil;
import com.core.util.StringUtil;
import com.mh.login.service.LoginManagerService;
import com.mh.main.service.MainManagerService;

@Controller
public class MainController {
	@Autowired
	MainManagerService service;
	
	@Autowired
	LoginManagerService loginService;
	
	@Autowired
	SessionLocaleResolver localeResolver;
	
	@RequestMapping(value="/main.do")
	public String main( ModelMap model
			            , HttpServletRequest request
			            , HttpServletResponse response
			            , @RequestParam LinkedHashMap BindMap
						
						) throws Exception {
					System.out.println("----------------MainController진입------------------");
					
		/*Map mainVD = service.mainvideo(BindMap);
		List mainEV = service.maineventList(BindMap);
			
		model.addAttribute("mainVD", mainVD);//메인 영상
		model.addAttribute("mainEV", mainEV);//메인 영상
		
		List main_notice = service.mainNotice_1(BindMap);
		model.addAttribute("main_notice", main_notice);
		
		List main_release = service.mainRelease_1(BindMap);
		model.addAttribute("main_release", main_release);*/
		// 쿠키에 로그인정보 존재 시 자동로그인 처리
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		String cookie_id = CookieUtil.getCookieValue(request, "sess_id");
					
		// 쿠키에 로그인정보가 존재하고 세션에 정보가 없을경우 로그인 처리
		if (!StringUtil.nullValue(cookie_id).equals("") && session.getAttribute("loginInfo") == null){
			System.out.println("session login : "+cookie_id);
			Map<String, Object> map = new HashMap();
			map.put("SESSID", cookie_id);
			
			Map<String, Object> loginInfo = loginService.selectKeepLogInfo(map);

			session.setAttribute("loginInfo", loginInfo);
		}
					
		return "main/main.user";
	}
	
	
	@RequestMapping(value="/intro.do")
	public String intro( ModelMap model
			            , HttpServletRequest request
			            , HttpServletResponse response
			            , @RequestParam LinkedHashMap BindMap
						
						) throws Exception {
					System.out.println("----------------MainController진입------------------");
					
		return "main/intro.user";
	}
	
	@RequestMapping(value="/changeLang.do")
	public String changeLang( ModelMap model
			, HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam LinkedHashMap BindMap
			
			) throws Exception {
		System.out.println("----------------mainChangeLangController진입------------------");

		HttpSession session = request.getSession();
		Locale locales = null;
		String lang = StringUtil.nullValue((String)BindMap.get("lang"));
		
		if (lang.equals("") || lang.equals("ko")){
			locales = Locale.KOREAN;
			lang = "ko";
		}else{
			locales = Locale.ENGLISH;
			lang = "en";
		}
		
		session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locales);
		session.setAttribute("currentLang", lang);
		
		String redirectURL = request.getHeader("REFERER") == null ? "/" : request.getHeader("REFERER");
		
		return "redirect:"+redirectURL;
	}
}
