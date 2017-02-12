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
			String[] member = new String[15];
			member[0] = request.getParameter("ID");
			member[1] = "temp";
			member[2] = request.getParameter("hash");
			member[3] = request.getParameter("name");
			member[4] = request.getParameter("sex");
			member[5] = request.getParameter("age");
			member[6] = request.getParameter("height");
			member[7] = request.getParameter("weight");
			member[8] = request.getParameter("job");			
			member[9] = request.getParameter("area");
			member[10] = request.getParameter("intro");
			member[11] = request.getParameter("sex_vis");
			member[12] = request.getParameter("age_vis");
			member[13] = request.getParameter("hei_vis");
			member[14] = request.getParameter("wei_vis");
			
			for (int i=11; i<=14; i++){
				if (member[i] == null)
					member[i] = "0";
			}
			
			int sex = Integer.parseInt(member[4]);
			int age = Integer.parseInt(member[5]);
			int height = Integer.parseInt(member[6]);
			int weight = Integer.parseInt(member[7]);
			int sex_vis = Integer.parseInt(member[11]);
			int age_vis = Integer.parseInt(member[12]);
			int hei_vis = Integer.parseInt(member[13]);
			int wei_vis = Integer.parseInt(member[14]);
			
			if (member[0].length() != 0){
				/* 회원가입 정보확인 폼을 위해 일단 세션에 모든 정보를 담음 */
				HttpSession session = request.getSession();
				session.setAttribute("ID", member[0]);
				session.setAttribute("salt", member[1]);
				session.setAttribute("hash", member[2]);
				session.setAttribute("name", member[3]);
				session.setAttribute("sex", sex);
				session.setAttribute("age", age);
				session.setAttribute("height", height);
				session.setAttribute("weight", weight);
				session.setAttribute("job", member[8]);
				session.setAttribute("area", member[9]);
				session.setAttribute("intro", member[10]);
				session.setAttribute("sex_vis", sex_vis);
				session.setAttribute("age_vis", age_vis);
				session.setAttribute("hei_vis", hei_vis);
				session.setAttribute("wei_vis", wei_vis);
				
				Level_DTO l_dto = new Level_DTO();
				l_dto.setID(member[0]);
				l_dto.setBMI(height, weight);
				l_dto.setBMR(sex, age, height, weight);
				l_dto.setAverage(sex, height);
				l_dto.setObesity(weight, l_dto.getAverage());
				
				Cal_Level cal = new Cal_Level();
				if (!cal.cal_b_level(l_dto)){
					response.sendRedirect("Fail.html");
				}
				
				session.setAttribute("level", l_dto.getLevel());
				session.setAttribute("b_level", l_dto.getB_level());
				session.setAttribute("b_exp", l_dto.getB_exp());
				session.setAttribute("e_level", l_dto.getE_level());
				session.setAttribute("e_exp", l_dto.getE_exp());
				session.setAttribute("BMI", l_dto.getBMI());
				session.setAttribute("BMR", l_dto.getBMR());
				session.setAttribute("obesity", l_dto.getObesity());
				session.setAttribute("average", l_dto.getAverage());
				
				session.setMaxInactiveInterval(60 * 60 * 2);
				// 세선 유지시간 : 2시간
				
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
			String job = (String)session.getAttribute("job");
			String area = (String)session.getAttribute("area");
			String intro = (String)session.getAttribute("intro");
			int sex_vis = (int)session.getAttribute("sex_vis");
			int age_vis = (int)session.getAttribute("age_vis");
			int hei_vis = (int)session.getAttribute("hei_vis");
			int wei_vis = (int)session.getAttribute("wei_vis");
			
			int b_level = (int)session.getAttribute("b_level");
			double b_exp = (double)session.getAttribute("b_exp");
			
			Member_DTO m_dto = new Member_DTO();
			m_dto.setID(ID);
			m_dto.setSalt(salt);
			m_dto.setHash(hash);
			m_dto.setName(name);
			m_dto.setSex(sex);
			m_dto.setAge(age);
			m_dto.setHeight(height);
			m_dto.setWeight(weight);
			m_dto.setJob(job);
			m_dto.setArea(area);
			m_dto.setIntro(intro);
			m_dto.setSex_vis(sex_vis);
			m_dto.setAge_vis(age_vis);
			m_dto.setHei_vis(hei_vis);
			m_dto.setWei_vis(wei_vis);
			
			Level_DTO l_dto = new Level_DTO();
			l_dto.setID(ID);
			l_dto.setLevel(b_level);
			l_dto.setB_level(b_level);
			l_dto.setB_exp(b_exp);
			l_dto.setE_level(0);
			l_dto.setE_exp(0);
			l_dto.setAverage(sex, height);
			l_dto.setBMI(height, weight);
			l_dto.setBMR(sex, age, height, weight);
			l_dto.setObesity(weight, l_dto.getAverage());
			
			/*Level_DTO dto2 = new Level_DTO();
			dto2.setID(ID);*/
			
			Connect_DAO dao = new Connect_DAO();
			boolean m_check = dao.insertMember(m_dto);
			boolean l_check = dao.insertLevel(l_dto);
			
			if (m_check && l_check){
				response.sendRedirect("register_Comp.jsp");
			}else{
				session.invalidate();
				response.sendRedirect("Fail.html");
			}

		/************************* < 가입 완료 -> 홈이동 폼 > ***************************/
		}else if (command.trim().equals("register_Comp")){	
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect("reg_complet.html");

		/************************* < 로그인 폼 > ***************************/
		}else if (command.trim().equals("login")){	
			String ID = request.getParameter("ID");
			String hash = request.getParameter("hash");
			
			Member_DTO m_dto = new Member_DTO();
			Level_DTO l_dto = new Level_DTO();
			
			Connect_DAO dao = new Connect_DAO();
			boolean check = dao.login_LevelUp(m_dto, l_dto, ID, hash);
			
			if (check){
				HttpSession session = request.getSession();
				session.setAttribute("ID", m_dto.getID());
				session.setAttribute("salt", m_dto.getSalt());
				session.setAttribute("hash", m_dto.getHash());
				session.setAttribute("name", m_dto.getName());
				session.setAttribute("sex", m_dto.getSex());
				session.setAttribute("age", m_dto.getAge());
				session.setAttribute("height", m_dto.getHeight());
				session.setAttribute("weight", m_dto.getWeight());
				session.setAttribute("job", m_dto.getJob());
				session.setAttribute("area", m_dto.getArea());
				session.setAttribute("intro", m_dto.getIntro());
				session.setAttribute("sex_vis", m_dto.getSex_vis());
				session.setAttribute("age_vis", m_dto.getAge_vis());
				session.setAttribute("hei_vis", m_dto.getHei_vis());
				session.setAttribute("wei_vis", m_dto.getWei_vis());
				
				session.setAttribute("level", l_dto.getLevel());
				session.setAttribute("e_level", l_dto.getE_level());
				session.setAttribute("e_exp", l_dto.getE_exp());
				session.setAttribute("BMI", l_dto.getBMI());
				session.setAttribute("BMR", l_dto.getBMR());
				session.setAttribute("obesity", l_dto.getObesity());
				session.setAttribute("average", l_dto.getAverage());
				
				Cal_Level cal = new Cal_Level();
				if (!cal.cal_b_level(l_dto)){
					session.invalidate();
					response.sendRedirect("Fail.html");
				}
				session.setAttribute("b_level", l_dto.getB_level());
				session.setAttribute("b_exp", l_dto.getB_exp());
				
				session.setMaxInactiveInterval(60 * 60 * 2);
				// 세선 유지시간 : 2시간
				
				response.sendRedirect("main.jsp");
			}else{
				response.sendRedirect("Fail.html");
			}
			
		}else if(command.trim().equals("uqdate")){
			
		}
	}
}
