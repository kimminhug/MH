package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class board_Servlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
		// doPost와 같은 과정 수행
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		
		System.out.println("uri = "+ uri);
		// 요청받은 URI
		
		//************** 게시판 리스트 *****************************************************
		if (uri.indexOf("board_list.do") != -1){
			board_DAO dao = new board_DAO();
			
			ArrayList<board_VO> board_Notice = dao.select_Notice();
			ArrayList<board_VO> board_List = new ArrayList<>();
			paging_VO paging = new paging_VO();
			int pageNo = 0;
			
			try{
				pageNo = Integer.parseInt(request.getParameter("page").toString());
			}catch(Exception ex){}
			
			// 페이징을 위해 컨트롤에서 정해줘야 할 것 : 현재 페이지 번호, 페이지당 글 수(디폴트 :10), 글 수 총합
			paging.setPageNo(pageNo);
	        paging.setPageSize(10);
	        paging.setTotalCount(dao.select_Board_cnt());
	        
	        board_List = dao.select_Board(paging.getLimit_start(), paging.getPageSize());
	        // limit로 페이징된 쿼리로 리스트 받음.
	        	        
			request.getServletContext().setAttribute("board_List", board_List);
			request.getServletContext().setAttribute("board_Notice", board_Notice);
			request.getServletContext().setAttribute("paging", paging);
			request.getRequestDispatcher("BoardList.jsp").forward(request, response);
		}
		
		//************** 게시판 작성이동 *****************************************************
		if (uri.indexOf("board_creat.do") != -1){
			response.sendRedirect("BoardCreat.jsp");
		}
		
		//************** 게시판 작성완료 *****************************************************
		if (uri.indexOf("board_creat_comp.do") != -1){
			board_DAO dao = new board_DAO();
			board_VO b_obj = new board_VO();
			
			MultipartRequest multi;
			String upload_Path = request.getSession().getServletContext().getRealPath("/")+"uploaded/";
			int size = 1024 * 1024 * 10;
			
			try{
				multi = new MultipartRequest(request, upload_Path, size, "UTF-8", new DefaultFileRenamePolicy());
				// 다중 업로드 소스 : 전 페이지에서 요청한 모든 파일 업로드 요청 수행
				b_obj.setFile(upload_Path + multi.getFilesystemName("upload"));
				// 예외 없을 때 파일 업로드경로 저장
				System.out.println("업로드 파일경로 : "+upload_Path + multi.getFilesystemName("upload"));
				
			}catch(Exception ex){
				System.out.println("파일 저장 없음!");
			}
			
			int notice_check = 0;
			try{
				notice_check = Integer.parseInt(request.getParameter("notice"));
			}catch(Exception ex){}
			b_obj.setNotice(notice_check);
			
			b_obj.setSubject((String)request.getParameter("subject"));
			b_obj.setName((String)request.getParameter("name"));
			b_obj.setContent((String)request.getParameter("content").replace("\r\n", "<br>").replace(" ", "&nbsp;"));
			// 내용 부분의 엔터키(개행)은 <br> 태그로, 공백은 &nbsp; 태그로 치환하여 DB에 저장!!
			
			b_obj.setStep(0);
			b_obj.setRef_order(0);
			// 새글 등록의 경우 0단계 답글, 답글 그룹중 최상위 순서
			
			boolean insert_ok = dao.insert_Board(b_obj, -1);
			
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
			board_VO b_obj = dao.view_Board(num);
			
			int rowNum = dao.get_RowNum(num);
			int[] numSet = dao.get_Prev_Next(rowNum);
			int prevNum = numSet[1], nextNum = numSet[0];		// 전글 번호와 다음글 번호 획득(없을시 -1 반환)
			
			if (b_obj != null){
				request.setAttribute("b_obj", b_obj);
				request.setAttribute("prevNum", prevNum);
				request.setAttribute("nextNum", nextNum);
				if (prevNum != -1){
					board_VO prev_obj = dao.view_Board(prevNum);
					request.setAttribute("prevSubject", prev_obj.getSubject());
				}
				if (nextNum != -1){
					board_VO next_obj = dao.view_Board(nextNum);
					request.setAttribute("nextSubject", next_obj.getSubject());
				}
				
				request.getRequestDispatcher("BoardContent.jsp?num="+num).forward(request, response);
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
		//************** 게시판 수정 ********************************************************
		if (uri.indexOf("board_modify.do") != -1){
			board_DAO dao = new board_DAO();
			int num = Integer.parseInt(request.getParameter("num"));
			board_VO b_obj = dao.view_Board(num);
			
			if (b_obj != null){
				b_obj.setContent(b_obj.getContent().replace("<br>", "\r\n").replace("&nbsp;", " "));
				// 내용 수정 시, DB에 저장된 <br>과 &nbsp; 태그를 실제 개행, 띄어쓰기로 다시 치환
				
				request.setAttribute("b_obj", b_obj);
				request.getRequestDispatcher("BoardModify.jsp?num="+num).forward(request, response);
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
		//************** 게시글 수정완료 ********************************************************
		if (uri.indexOf("board_modify_comp.do") != -1){
			board_DAO dao = new board_DAO();
			int num = Integer.parseInt(request.getParameter("num"));
			board_VO b_obj = new board_VO();
			int notice_check = 0;
			
			try{
				notice_check = Integer.parseInt(request.getParameter("notice"));
			}catch(Exception ex){}
			
			b_obj.setNotice(notice_check);
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
		//************** 답글작성 이동 ******************************************************
		if (uri.indexOf("board_reply.do") != -1){
			board_DAO dao = new board_DAO();
			int num = Integer.parseInt(request.getParameter("num"));
			board_VO b_obj = dao.view_Board(num);
			// 답글달려는 부모글의 정보를 입력받음.
			
			if (b_obj != null){
				b_obj.setContent(b_obj.getContent().replace("<br>", "\r\n").replace("&nbsp;", " "));
				request.setAttribute("b_obj", b_obj);
				request.getRequestDispatcher("BoardReply.jsp").forward(request, response);
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
		//************** 답글작성 완료 ********************************************************
		if (uri.indexOf("board_reply_comp.do") != -1){
			board_DAO dao = new board_DAO();
			board_VO b_obj = new board_VO();
			System.out.println("넘겨받은 ref = "+request.getParameter("ref"));
			int ref = Integer.parseInt(request.getParameter("ref"));
			
			b_obj.setRef(ref);
			b_obj.setStep(Integer.parseInt(request.getParameter("step")) + 1);
			b_obj.setRef_order(dao.cal_Max_Ref_order(ref));
			
			b_obj.setSubject((String)request.getParameter("subject"));
			b_obj.setName((String)request.getParameter("name"));
			b_obj.setContent((String)request.getParameter("content").replace("\r\n", "<br>").replace(" ", "&nbsp;"));
			
			boolean reply_ok = dao.insert_Board(b_obj, ref);
			// 답글 -> DB 등록과정은 일반 글등록과 동일함.
			
			if (reply_ok){
				response.sendRedirect("BoardCreat_Comp.html");
			}else{
				response.sendRedirect("Fail.jsp");
			}
		}
	}
}
