package com.mh.admin.board.service;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.mh.admin.board.vo.BoardMngtVO;

public interface BoardMngtService {

	public void boardListServiceCall(HttpServletRequest request, HttpServletResponse response, BoardMngtVO vo, ModelMap model, Writer out)throws Exception;
	public void boardSaveServiceCall(HttpServletRequest request, HttpServletResponse response, ModelMap model, Writer out)throws Exception;
	public void boardInfoServiceCall(HttpServletRequest request, HttpServletResponse response, ModelMap model, Writer out)throws Exception;
	public void boardInfoSaveServiceCall(HttpServletRequest request, HttpServletResponse response, ModelMap model, Writer out)throws Exception;

}
