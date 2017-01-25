package com.patten;
/* [MVC 패턴] 중, Controller를 맡고 있는 객체 - MemberProcess */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberProcess	extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		// System.out.println("uri = "+uri);
		
		String command = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".kim"));
		
		/* < 회원가입 -> 완료 진행 폼 > */
		if (command != null && command.trim().equals("register")){
			request.setCharacterEncoding("UTF-8");
			String[] param = new String[14];
			param[0] = request.getParameter("ID");
			param[1] = "temp";
			param[2] = request.getParameter("hash");
			param[3] = request.getParameter("name");
			param[4] = request.getParameter("sex");
			param[5] = request.getParameter("age");
			param[6] = request.getParameter("height");
			param[7] = request.getParameter("weight");
			param[8] = request.getParameter("area");
			param[9] = request.getParameter("intro");
			param[10] = request.getParameter("sex_vis");
			param[11] = request.getParameter("age_vis");
			param[12] = request.getParameter("hei_vis");
			param[13] = request.getParameter("wei_vis");
			
			for (int i=10; i <=13; i++){
				if (param[i] == null)
					param[i] = "0";
			}
			
			Member_DTO dto = new Member_DTO();
			dto.setID(param[0]);
			dto.setSalt(param[1]);
			dto.setHash(param[2]);
			dto.setName(param[3]);
			dto.setSex(Integer.parseInt(param[4]));
			dto.setAge(Integer.parseInt(param[5]));
			dto.setHeight(Integer.parseInt(param[6]));
			dto.setWeight(Integer.parseInt(param[7]));
			dto.setArea(param[8]);
			dto.setIntro(param[9]);
			dto.setSex_vis(Integer.parseInt(param[10]));
			dto.setAge_vis(Integer.parseInt(param[11]));
			dto.setHei_vis(Integer.parseInt(param[12]));
			dto.setWei_vis(Integer.parseInt(param[13]));
			
			/*Level_DTO dto2 = new Level_DTO();
			dto2.setID(ID);*/
			
			
			Connect_DAO dao = new Connect_DAO();
			boolean reg = dao.insertMember(dto);
			
			if (reg){
				HttpSession session = request.getSession();
				session.setAttribute("ID", param[0]);
				session.setAttribute("name", param[3]);
				session.setAttribute("sex", Integer.parseInt(param[4]));
				session.setAttribute("age", Integer.parseInt(param[5]));
				session.setAttribute("height", Integer.parseInt(param[6]));
				session.setAttribute("weight", Integer.parseInt(param[7]));
				session.setAttribute("area", param[8]);
				session.setAttribute("intro", param[9]);
				session.setAttribute("sex_vis", Integer.parseInt(param[10]));
				session.setAttribute("age_vis", Integer.parseInt(param[11]));
				session.setAttribute("hei_vis", Integer.parseInt(param[12]));
				session.setAttribute("wei_vis", Integer.parseInt(param[13]));
				
				session.setMaxInactiveInterval(60 * 60);
				// 세선 유지시간 : 1시간
				
				response.sendRedirect("Register_Retry.jsp");
			}else{
				response.sendRedirect("Fail.html");
			}
			
		}else if(command.trim().equals("uqdate")){
			
		}
	}
}
