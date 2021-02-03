package com.mh.contact.web;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.core.model.Bind;
import com.core.util.SessionUtil;
import com.core.vo.EgovPagingVO;
import com.core.vo.PagingVO;
import com.mh.contact.service.ContactManagerService;

@Controller
public class ContactController {
	@Autowired
	ContactManagerService service;
	
	@RequestMapping(value="/contact.do")
	public String contact_write( ModelMap model
			            , HttpServletRequest request
			            , HttpServletResponse response
			            , @RequestParam LinkedHashMap BindMap
						
						) throws Exception {
					System.out.println("----------------Contact Controller------------------");
					System.out.println("BindMap :: " + BindMap);
					
		return "contact/contact.user";
	}
	
	@RequestMapping(value="/contactSave.do")
	public void contactSave( ModelMap model
			            , HttpServletRequest request
			            , HttpServletResponse response
			            , @RequestParam LinkedHashMap BindMap
						
						) throws Exception {
			System.out.println("----------------ContactSave Controller-----------------");
			System.out.println("BindMap ::: " + BindMap);
			
			service.insertContact(BindMap);
					
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('문의가 정상적으로 등록되었습니다. 감사합니다.'); location.href='/contact.do';</script>");
			out.flush();
	}
	
	/*@RequestMapping(value="/contactUpdate.do")
	public String contactUpdate( ModelMap model
			            , HttpServletRequest request
			            , HttpServletResponse response
			            , @RequestParam LinkedHashMap BindMap
						
						) throws Exception {
					System.out.println("----------------Contact View Controller 수정 진입4------------------");
					BindMap.put("update_id", SessionUtil.getAttribute("OGMEM_ID"));
					BindMap.put("status", "regit");
					System.out.println("BindMap ::: " + BindMap);
					
					Map comstate_one = service.getComstateOne(BindMap);
					
					System.out.println("comstate_one ::::: " + comstate_one);
					BindMap.put("contact_main_state", comstate_one.get("code"));
					
					service.updateContact(BindMap);
					
					List contactlist= service.getContactlist(BindMap);
					List area2_list = service.getArea2List(BindMap);
					
					model.addAttribute("comlist", contactlist);
					model.addAttribute("menu", BindMap);
					model.addAttribute("login_info", SessionUtil.getAttribute("OGMEM_ID"));
					model.addAttribute("area2_list", area2_list);
					
		return "user/contact/contact_list.user";
	}
	
	@RequestMapping(value="/user/contactSearchPopup.li")
	public String contactSearchPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam LinkedHashMap BindMap) throws Exception {
		System.out.println("----------------Contact Popup View Controller진입------------------");
		Bind bind = new Bind(request);
		Map<String, Object> dto = bind.getDto();
		System.out.println("contactSearchPopup(dto) result : " + dto);
		
		List member_info = service.getMemberInfo(BindMap);
		
		System.out.println("member_info :: " + member_info);
		
		model.addAttribute("member_info", member_info);
		model.addAttribute("dto", dto);
		
		return "user/contact/orgm_list_popup.pop";
	}
	
	@RequestMapping(value="/user/memeberSearch.li")
	public String memberSearchPopup(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam LinkedHashMap BindMap) throws Exception {
		System.out.println("----------------Member Popup View Controller진입------------------");
		System.out.println("BindMap222 :::: " + BindMap);
		
		List member_info_search = service.getMemberInfo(BindMap);
		
		model.addAttribute("member_info", member_info_search);
		model.addAttribute("dto", BindMap);
		
		return "user/contact/orgm_list_popup.pop";
	}*/
}
