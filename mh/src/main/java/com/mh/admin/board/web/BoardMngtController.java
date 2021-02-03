package com.mh.admin.board.web;

import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.model.Bind;
import com.mh.admin.board.service.BoardMngtService;
import com.mh.admin.board.vo.BoardMngtVO;


@Controller
public class BoardMngtController {
	@Autowired
	BoardMngtService service;

	/**
	 * 게시판관리 화면진입
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value={"/admin/board/boardMngt.do"})		
	public String boardberIndex( ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getServerName() + " :::::::::::: " + request.getServletPath());
		
		String rtnType = "";
		
		/** Todo..
		 * 메뉴관리 완성되면 menu_no 로 UI타입 알아서 view return 되도록 처리
		 */
		
		rtnType = "admin/board/boardMngt.admin";
		
		return rtnType;
	}
	
	/**
	 * 게시판관리 목록 조회
	 * @param vo
	 * @param model
	 * @param request
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/board/boardListAjax.do")		
	public void boardList(@ModelAttribute("searchVO") BoardMngtVO vo, ModelMap model, HttpServletRequest request, HttpServletResponse response, Writer out ) throws Exception {
		service.boardListServiceCall(request, response, vo, model, out);
	}
	
	/**
	 * 게시판관리 
	 * @param model
	 * @param request
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/board/boardSaveAjax.do")		
	public void boardSave(ModelMap model, HttpServletRequest request, HttpServletResponse response, Writer out ) throws Exception {
		service.boardSaveServiceCall(request, response, model, out);
	}

	/**
	 * 게시판관리 폼 화면진입
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/board/boardViewAjax.do")		
	public String boardViewAjax( ModelMap model, HttpServletRequest request, HttpServletResponse response, Writer out) throws Exception {
		Bind bind = new Bind(request);
		Map<String, Object> bindMap = bind.getDto();
		
		model.addAttribute("req", bindMap);
		
		System.out.println(request.getServerName() + " boardView :::::::::::: " + request.getHeader("REFERER"));
		/** Todo..
		 * 메뉴관리 완성되면 menu_no 로 UI타입 알아서 view return 되도록 처리
		 */
		
		return "admin/board/boardMngtView.ajax";
	}
	
	/**
	 * 게시판관리 폼 데이터 조회
	 * @param model
	 * @param request
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/board/boardInfoAjax.do")		
	public void boardInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response, Writer out ) throws Exception {
		service.boardInfoServiceCall(request, response, model, out);
	}
	
	
	/**
	 * 게시판관리 폼데이터 저장
	 * @param model
	 * @param request
	 * @param response
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/board/boardInfoSaveAjax.do")		
	public void boardInfoSaveAjax(ModelMap model, HttpServletRequest request, HttpServletResponse response, Writer out ) throws Exception {
		service.boardInfoSaveServiceCall(request, response, model, out);
	}
}
