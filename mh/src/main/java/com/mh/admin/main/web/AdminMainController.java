package com.mh.admin.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mh.admin.main.service.AdminMainManagerService;


@Controller
public class AdminMainController {
	@Autowired
	AdminMainManagerService service;
	
	@RequestMapping(value="/admin/main.do")
	public String main( ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("----------------AdminMainController진입------------------");
		/*관리자 로그인 정보 */
		/*model.addAttribute(SessionUtil.getAttribute("ADIDX"));
		model.addAttribute(SessionUtil.getAttribute("ANAME"));
		model.addAttribute(SessionUtil.getAttribute("ADID"));*/
		
		/*List PTList = service.selectPTList(BindMap);
		List CrmList = service.selectCRList(BindMap);
		List PTAList = service.selectPTAList(BindMap);
		List EVTList = service.selectEAList(BindMap);
		메인 가입업체 리스트
		model.addAttribute( "PTList", PTList );
		
		메인 가입회원 리스트
		model.addAttribute( "CrmList", CrmList );
		
		메인 가입대기 업체 리스트
		model.addAttribute( "PTAList", PTAList );
		
		메인 최근 이벤트 리스트
		model.addAttribute("EVTList", EVTList);*/
					
		return "admin/main/main.admin";
	}
}
