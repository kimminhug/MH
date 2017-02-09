package com.patten;
/* [MVC 패턴] 중, Controller를 맡고 있는 객체 - MemberProcess */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberProcess	extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		// System.out.println("uri = "+uri);
		
		String command = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".kim"));
		
		/************************* < 회원가입 -> 가입 확인 폼 > ***************************/
		if (command != null && command.trim().equals("register")){
			String[] member = new String[14];
			member[0] = request.getParameter("ID");
			member[1] = "temp";
			member[2] = request.getParameter("hash");
			member[3] = request.getParameter("name");
			member[4] = request.getParameter("sex");
			member[5] = request.getParameter("age");
			member[6] = request.getParameter("height");
			member[7] = request.getParameter("weight");
			member[8] = request.getParameter("area");
			member[9] = request.getParameter("intro");
			member[10] = request.getParameter("sex_vis");
			member[11] = request.getParameter("age_vis");
			member[12] = request.getParameter("hei_vis");
			member[13] = request.getParameter("wei_vis");
			
			for (int i=10; i<=13; i++){
				if (member[i] == null)
					member[i] = "0";
			}
			
			int sex = Integer.parseInt(member[4]);
			int age = Integer.parseInt(member[5]);
			int height = Integer.parseInt(member[6]);
			int weight = Integer.parseInt(member[7]);
			int sex_vis = Integer.parseInt(member[10]);
			int age_vis = Integer.parseInt(member[11]);
			int hei_vis = Integer.parseInt(member[12]);
			int wei_vis = Integer.parseInt(member[13]);
			
			if (member[0].length() != 0){
				HttpSession session = request.getSession();
				session.setAttribute("ID", member[0]);
				session.setAttribute("salt", member[1]);
				session.setAttribute("hash", member[2]);
				session.setAttribute("name", member[3]);
				session.setAttribute("sex", sex);
				session.setAttribute("age", age);
				session.setAttribute("height", height);
				session.setAttribute("weight", weight);
				session.setAttribute("area", member[8]);
				session.setAttribute("intro", member[9]);
				session.setAttribute("sex_vis", sex_vis);
				session.setAttribute("age_vis", age_vis);
				session.setAttribute("hei_vis", hei_vis);
				session.setAttribute("wei_vis", wei_vis);
				
				Level_DTO l_dto = new Level_DTO();
				l_dto.setBMI(height, weight);
				l_dto.setBMR(sex, age, height, weight);
				l_dto.setAverage(sex, height);
				l_dto.setObesity(weight, l_dto.getAverage());
				l_dto.setLevel(1);
				
				session.setAttribute("BMI", l_dto.getBMI());
				session.setAttribute("BMR", l_dto.getBMR());
				session.setAttribute("obesity", l_dto.getObesity());
				session.setAttribute("average", l_dto.getAverage());
				session.setAttribute("level", l_dto.getLevel());
				
				session.setMaxInactiveInterval(60 * 60 * 3);
				// 세선 유지시간 : 3시간
				
				response.sendRedirect("register_Retry.jsp");
			}else{
				response.sendRedirect("Fail.html");
			}
			
		/************************* < 가입 확인 -> 가입 완료 폼 > ***************************/
		}else if (command.trim().equals("register_Retry")){	
			HttpSession session = request.getSession();
			String ID = (String)session.getAttribute("ID");
			String salt = (String)session.getAttribute("salt");
			String hash = (String)session.getAttribute("hash");
			String name = (String)session.getAttribute("name");
			int sex = (int)session.getAttribute("sex");
			int age = (int)session.getAttribute("age");
			int height = (int)session.getAttribute("height");
			int weight = (int)session.getAttribute("weight");
			String area = (String)session.getAttribute("area");
			String intro = (String)session.getAttribute("intro");
			int sex_vis = (int)session.getAttribute("sex_vis");
			int age_vis = (int)session.getAttribute("age_vis");
			int hei_vis = (int)session.getAttribute("hei_vis");
			int wei_vis = (int)session.getAttribute("wei_vis");
			
			Member_DTO m_dto = new Member_DTO();
			m_dto.setID(ID);
			m_dto.setSalt(salt);
			m_dto.setHash(hash);
			m_dto.setName(name);
			m_dto.setSex(sex);
			m_dto.setAge(age);
			m_dto.setHeight(height);
			m_dto.setWeight(weight);
			m_dto.setArea(area);
			m_dto.setIntro(intro);
			m_dto.setSex_vis(sex_vis);
			m_dto.setAge_vis(age_vis);
			m_dto.setHei_vis(hei_vis);
			m_dto.setWei_vis(wei_vis);
			
			Level_DTO l_dto = new Level_DTO();
			l_dto.setID(ID);
			l_dto.setLevel(1);
			l_dto.setExp(1000);
			l_dto.setReq_exp(1500);
			l_dto.setBMI(height, weight);
			l_dto.setBMR(sex, age, height, weight);
			l_dto.setAverage(sex, height);
			l_dto.setObesity(weight, l_dto.getAverage());
			
			/*Level_DTO dto2 = new Level_DTO();
			dto2.setID(ID);*/
			
			Connect_DAO dao = new Connect_DAO();
			boolean m_check = dao.insertMember(m_dto);
			boolean l_check = dao.insertLevel(l_dto);
			
			if (m_check && l_check){
				response.sendRedirect("register_Comp.jsp");
			}else{
				response.sendRedirect("Fail.html");
			}

		}else if(command.trim().equals("uqdate")){
			
		}
	}
}
