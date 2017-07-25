package com;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class board_Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		process(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		process(request, response);
	}
	// get이던 post던 같은 과정 수행
	
	void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		
		System.out.println("uri = "+ uri);
		// 요청받은 URI
		
		//************** 게시판 리스트 *****************************************************
		if (uri.indexOf("board_list.do") != -1){
			board_DAO dao = new board_DAO();
			ArrayList<board_DTO> board_List = dao.select_Board();
			
			if (board_List != null){
				request.getServletContext().setAttribute("board_List", board_List);
				request.getRequestDispatcher("BoardList.jsp").forward(request, response);
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
		
		//************** 게시판 작성이동 *****************************************************
		if (uri.indexOf("board_creat.do") != -1){
			response.sendRedirect("BoardCreat.jsp");
		}
		
		//************** 게시판 작성완료 *****************************************************
		if (uri.indexOf("board_creat_comp.do") != -1){
			board_DAO dao = new board_DAO();
			board_DTO b_obj = new board_DTO();
			
			b_obj.setSubject((String)request.getParameter("subject"));
			b_obj.setName((String)request.getParameter("name"));
			b_obj.setContent((String)request.getParameter("content").replace("\r\n", "<br>").replace(" ", "&nbsp;"));
			// 내용 부분의 엔터키(개행)은 <br> 태그로, 공백은 &nbsp; 태그로 치환하여 DB에 저장!!
			
			boolean insert_ok = dao.insert_Board(b_obj);
			
			if (insert_ok){
				response.sendRedirect("BoardCreat_Comp.html");
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
		
		//************** 게시판 뷰 *********************************************************
		if (uri.indexOf("board_content.do") != -1){
			board_DAO dao = new board_DAO();
			int num = Integer.parseInt(request.getParameter("num"));
			board_DTO b_obj = dao.view_Board(num);
			
			if (b_obj != null){
				request.setAttribute("b_obj", b_obj);
				request.getRequestDispatcher("BoardContent.jsp?num="+num).forward(request, response);
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
		//************** 게시판 수정 ********************************************************
		if (uri.indexOf("board_modify.do") != -1){
			board_DAO dao = new board_DAO();
			int num = Integer.parseInt(request.getParameter("num"));
			board_DTO b_obj = dao.view_Board(num);
			
			if (b_obj != null){
				b_obj.setContent(b_obj.getContent().replace("<br>", "\r\n").replace("&nbsp;", " "));
				// 내용 수정 시, DB에 저장된 <br>과 &nbsp; 태그를 실제 개행, 띄어쓰기로 다시 치환
				
				request.setAttribute("b_obj", b_obj);
				request.getRequestDispatcher("BoardModify.jsp?num="+num).forward(request, response);
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
		//************** 게시글 수정 ********************************************************
		if (uri.indexOf("board_modify_comp.do") != -1){
			board_DAO dao = new board_DAO();
			int num = Integer.parseInt(request.getParameter("num"));
			board_DTO b_obj = new board_DTO();
			
			b_obj.setSubject((String)request.getParameter("subject"));
			b_obj.setName((String)request.getParameter("name"));
			b_obj.setContent((String)request.getParameter("content").replace("\r\n", "<br>").replace(" ", "&nbsp;"));
			
			boolean modify_ok = dao.modify_Board(b_obj, num);
			
			if (modify_ok){
				response.sendRedirect("BoardCreat_Comp.html");
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
		//************** 게시글 삭제 ********************************************************
		if (uri.indexOf("board_delete.do") != -1){
			board_DAO dao = new board_DAO();
			int num = Integer.parseInt(request.getParameter("num"));
			
			boolean delete_ok = dao.delete_Board(num);
			
			if (delete_ok){
				response.sendRedirect("board_list.do");
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
	}
}
