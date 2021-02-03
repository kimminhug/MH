package com.core.advice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

import com.core.util.BeanUtil;
import com.core.util.StringUtil;
import com.mh.login.vo.LoginVO;


@Aspect
public class InfoAdvice {

	/**
	 * Controller 메소드 호출 전 정보 셋팅
	 * @param jp
	 * @throws Throwable
	 */
	public void beforeMethodCall(JoinPoint jp) throws Throwable {
		System.out.println("InfoAdvice Before Method");

		//Signature sig = jp.getSignature();
		//String className = sig.getDeclaringType().getSimpleName();
		//String commonProgramId = className.substring(0, className.indexOf("Controller"));
		//String commonProgramIdOrg = commonProgramId;
		//System.out.println("commonProgramId========>"+commonProgramId);
		
		Object[] args = jp.getArgs();
		//System.out.println("argsargsargsargs :: " + args);
		for(Object arg : args) {
			if(arg == null){
                continue;
            }
			//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa :::::::::::: " + arg);
			if(arg instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest)arg;
                //System.out.println("requestrequest : : " + request);
                setBaseInfoColumn(request);
                
                //Menu
                /*Cookie[] cookies = request.getCookies();
                if(cookies != null){
	                for(int i = 0 ; i<cookies.length; i++){ 
	                	if(cookies[i].getName().equals("menuNo")){
	                		Map<String, Object> menuParam = new HashMap<String, Object>();
	                		menuParam.put("MENU_NO", cookies[i].getValue());
	                		Map<String, Object> curMenu = sysDao.getCurMenu(menuParam);
	                		request.setAttribute("curMenu", curMenu);
	                	}
	                }
                }*/
   			}
		}
	}
	
	/**
	 * 기본정보 셋팅
	 * @param request
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setBaseInfoColumn(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		String dt = sdf.format(new Date()); //일시
		//LoginVO loginInfo = new LoginVO();
		String loginId = "";
		try {
			Map<String, Object> loginInfoMap = (Map<String, Object>)request.getSession().getAttribute("loginInfo");
			System.out.println("sttsNosttsNosttsNo :: " + loginInfoMap);
			LoginVO loginInfoVO = new LoginVO();
			BeanUtil.populate(loginInfoVO, loginInfoMap); //Map 데이터를 VO 객체에 복사
			loginId = StringUtil.nullValue(loginInfoVO.getUsid()); 
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		//System.out.println("lvlvlvlvlvl :: " + sttsNo.getUseLvl());
		//String pgmId = StringUtil.nullValue(request.getAttribute("commonProgramId")); //프로그램ID
		//String uriInfo = StringUtil.nullValue(request.getServerName()); //프로그램ID
		String fullURL = StringUtil.nullValue(request.getRequestURL()); //URL
		//String getParam = StringUtil.nullValue(request.getQueryString()); //get param
		String ipAddr = request.getRemoteAddr(); //IP주소

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("create_date", dt);          //생성일시
		map.put("create_id", loginId); //생성신분번호
		map.put("create_route", fullURL);   //생성프로그램ID
		map.put("create_host", ipAddr); //생성IP주소
		map.put("update_date", dt);          //생성일시
		map.put("update_id", loginId); //생성신분번호
		map.put("update_route", fullURL);   //생성프로그램ID
		map.put("update_host", ipAddr); //생성IP주소
		
		request.setAttribute("baseInfo", map);
		
	}
}
