package com.patten;
/* [MVC 패턴] 중, Controller를 맡고 있는 객체 - MemberProcess */

import com.beans.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

public class MemberProcess	extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		// System.out.println("uri = "+uri);
		
		String command = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".kim"));
		
		/************************* < 회원가입 -> 가입 확인 폼 > ***************************/
		if (command != null && command.trim().equals("register")){
			String ID = request.getParameter("ID");
			String hash = request.getParameter("hash");
			String name = request.getParameter("name");
			int sex = Integer.parseInt(request.getParameter("sex"));
			int age = Integer.parseInt(request.getParameter("age"));
			int height = Integer.parseInt(request.getParameter("height"));
			double weight = Double.parseDouble(request.getParameter("weight"));
			String job = request.getParameter("job");			
			String area = request.getParameter("area");
			String intro = request.getParameter("intro");
			String[] vis = new String[4];
			vis[0] = request.getParameter("sex_vis");
			vis[1] = request.getParameter("age_vis");
			vis[2] = request.getParameter("hei_vis");
			vis[3] = request.getParameter("wei_vis");
			
			for (int i=0; i<=3; i++){
				if (vis[i] == null)
					vis[i] = "0";
			}
			
			int sex_vis = Integer.parseInt(vis[0]);
			int age_vis = Integer.parseInt(vis[1]);
			int hei_vis = Integer.parseInt(vis[2]);
			int wei_vis = Integer.parseInt(vis[3]);
			
			if (ID.length() != 0){
				/* 회원가입 정보확인 편의를 위해 세션에 정보 저장 */
				HttpSession session = request.getSession();
				session.setAttribute("ID", ID);
				session.setAttribute("hash", hash);
				session.setAttribute("name", name);
				session.setAttribute("sex", sex);
				session.setAttribute("age", age);
				session.setAttribute("height", height);
				session.setAttribute("weight", weight);
				session.setAttribute("job", job);
				session.setAttribute("area", area);
				session.setAttribute("intro", intro);
				session.setAttribute("sex_vis", sex_vis);
				session.setAttribute("age_vis", age_vis);
				session.setAttribute("hei_vis", hei_vis);
				session.setAttribute("wei_vis", wei_vis);
				
				Level_DTO l_dto = new Level_DTO();
				l_dto.setID(ID);
				l_dto.setBMI(height, weight);
				l_dto.setBMR(sex, age, height, weight);
				l_dto.setAverage(sex, height);
				l_dto.setObesity(weight, l_dto.getAverage());
				
				Cal_Level cal = new Cal_Level();
				if (!cal.cal_B_level(l_dto)){
					response.sendRedirect("Fail.html");
				}
				
				session.setAttribute("level", l_dto.getLevel());
				session.setAttribute("b_level", l_dto.getB_level());
				session.setAttribute("b_exp", l_dto.getB_exp());
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
			String hash = (String)session.getAttribute("hash");
			String name = (String)session.getAttribute("name");
			int sex = (int)session.getAttribute("sex");
			int age = (int)session.getAttribute("age");
			int height = (int)session.getAttribute("height");
			double weight = (double)session.getAttribute("weight");
			String job = (String)session.getAttribute("job");
			String area = (String)session.getAttribute("area");
			String intro = (String)session.getAttribute("intro");
			int sex_vis = (int)session.getAttribute("sex_vis");
			int age_vis = (int)session.getAttribute("age_vis");
			int hei_vis = (int)session.getAttribute("hei_vis");
			int wei_vis = (int)session.getAttribute("wei_vis");
			
			int b_level = (int)session.getAttribute("b_level");
			int b_exp = (int)session.getAttribute("b_exp");
			
			Member_DTO m_dto = new Member_DTO();
			m_dto.setID(ID);
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
			l_dto.setLevel(b_level+1);
			l_dto.setB_level(b_level);
			l_dto.setB_exp(b_exp);
			l_dto.setE_level(1);
			l_dto.setE_exp(0);
			l_dto.setAverage(sex, height);
			l_dto.setBMI(height, weight);
			l_dto.setBMR(sex, age, height, weight);
			l_dto.setObesity(weight, l_dto.getAverage());
			
			/*Level_DTO dto2 = new Level_DTO();
			dto2.setID(ID);*/
			Cal_Hash cal = new Cal_Hash();
			
			try {
				cal.Set_Hash(m_dto);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
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
			Cal_Hash cal_hash = new Cal_Hash();
			
			boolean DAO_check = dao.login_LevelUp(m_dto, l_dto, ID);
			
			if (DAO_check){
				boolean hash_check = cal_hash.Test_Hash(m_dto, hash);
				
				if (hash_check){
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
					session.setAttribute("e_exp", l_dto.getE_exp());
					session.setAttribute("BMI", l_dto.getBMI());
					session.setAttribute("BMR", l_dto.getBMR());
					session.setAttribute("obesity", l_dto.getObesity());
					session.setAttribute("average", l_dto.getAverage());
					
					Cal_Level cal = new Cal_Level();
					
					boolean cal_b_check = cal.cal_B_level(l_dto);
					boolean cal_e_check = cal.cal_E_level(l_dto);
					
					if (!cal_b_check && !cal_e_check){
						session.invalidate();
						response.sendRedirect("Fail.html");
					}
					
					session.setAttribute("b_level", l_dto.getB_level());
					session.setAttribute("b_exp", l_dto.getB_exp());
					session.setAttribute("e_req_exp", l_dto.getE_req_exp());
					session.setAttribute("e_level", l_dto.getE_level());
					session.setAttribute("e_rate", l_dto.getE_rate());
					
					session.setMaxInactiveInterval(60 * 60 * 2);
					// 세선 유지시간 : 2시간
					
					response.sendRedirect("main.html");
				}else{
					response.sendRedirect("Fail.html");
				}
			}else{
				response.sendRedirect("Fail.html");
			}
			
		/************************* < 경험치 입력 폼  > ***************************/
		}else if (command.trim().equals("exp_input")){	
			// < 경험치 입력 과정 >
			// ------------1) 이전 레벨,경험치와 입력된 몸무게(경험치)를 받는다.-----------
			HttpSession session = request.getSession();
			int b_level_before = (int)session.getAttribute("b_level");
			int b_exp_before = (int)session.getAttribute("b_exp");
			int e_level_before = (int)session.getAttribute("e_level");
			int e_exp_before = (int)session.getAttribute("e_exp");
			
			double weight = Double.parseDouble(request.getParameter("weight"));
			int e_exp = Integer.parseInt(request.getParameter("e_exp"));
			e_exp += e_exp_before;
			
			// ------------2) DB와 경험치 증감처리에 필요한 변수들을 정리한다.--------------------
			Member_DTO m_dto = new Member_DTO();
			Level_DTO l_dto = new Level_DTO();
			
			String ID = (String)session.getAttribute("ID");
			int age = (int)session.getAttribute("age");
			int sex = (int)session.getAttribute("sex");
			int height = (int)session.getAttribute("height");
			
			// DAO 갱신에 필요한 정보(ID 제외) : 신체 - 1건 / 레벨 - 9건
			m_dto.setID(ID);
			m_dto.setWeight(weight);
			
			l_dto.setID(ID);
			l_dto.setE_exp(e_exp);
			l_dto.setBMI(height, weight);
			l_dto.setBMR(sex, age, height, weight);
			l_dto.setAverage(sex, height);
			double average = l_dto.getAverage();
			l_dto.setObesity(weight, average);
			
			Cal_Level cal = new Cal_Level();
			
			// [setB_level], [setB_exp], [setE_level], setE_bas_exp, setE_req_exp, setE_rate 처리
			boolean cal_b_check = cal.cal_B_level(l_dto);
			boolean cal_e_check = cal.cal_E_level(l_dto);
			if (!cal_b_check && !cal_e_check){
				response.sendRedirect("Fail.html");
			}
			l_dto.setLevel(l_dto.getB_level() + l_dto.getE_level());
			
			// -----------3) 계산된 모든 정보를 DAO를 통해 DB에 저장한다.-----------------------
			Connect_DAO dao = new Connect_DAO();
			dao.updateWeight(m_dto);
			dao.updateEXP(l_dto);
			
			// -----------4) 필요한 정보를 경험치획득 이벤트 페이지로 넘긴다.----------------------
			if (l_dto.getB_level() >= b_level_before){
				//1. B레벨이 전보다 올라간 경우
				
			}else{
				//2. B레벨이 전보다 내려간 경우
				
			}
			if (l_dto.getE_level() >= e_level_before){
				//1. E레벨이 전보다 올라간 경우
				
			}else{
				//2. E레벨이 전보다 내려간 경우
				
			}
			
			// 변경된 정보 다시 세션에 입력
			session.setAttribute("weight", m_dto.getWeight());
			
			session.setAttribute("level", l_dto.getLevel());
			session.setAttribute("b_level", l_dto.getB_level());
			session.setAttribute("b_exp", l_dto.getB_exp());
			session.setAttribute("e_level", l_dto.getE_level());
			session.setAttribute("e_exp", l_dto.getE_exp());
			session.setAttribute("BMI", l_dto.getBMI());
			session.setAttribute("BMR", l_dto.getBMR());
			session.setAttribute("obesity", l_dto.getObesity());
			session.setAttribute("average", l_dto.getAverage());
			
			// 마이페이지 그래프에 필요한 정보 다시 세션에 입력
			session.setAttribute("e_bas_exp", l_dto.getE_req_exp());
			session.setAttribute("e_req_exp", l_dto.getE_req_exp());
			session.setAttribute("e_rate", l_dto.getE_rate());			
			
			session.setMaxInactiveInterval(60 * 60 * 2);
			// 세선 유지시간 : 2시간

			response.sendRedirect("mypage.jsp");

		}else if(command.trim().equals("uqdate")){
			
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		// System.out.println("uri = "+uri);

		String command = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".kim"));
		
		/************************* < 메인 폼  > ***************************/
		if (command.trim().equals("main")){
			Connect_DAO dao = new Connect_DAO();
			ArrayList<Ranking> rank_list = dao.select_Ranking();
			
			if (rank_list != null){
				request.getServletContext().setAttribute("rank_list", rank_list);
			}else{
				System.out.println("서블릿 : 리스트가 널입니다");
			}
			response.sendRedirect("main.jsp");
		}
		/************************* < 랭킹 폼  > ***************************/
		if (command.trim().equals("ranking")){
			// < 랭킹 출력 과정 >
			// 1. DAO를 통해 member 테이블을 레벨순으로 가져온다
			// 2. ResultSet을 받아 랭킹 jsp로 전달한다
			Connect_DAO dao = new Connect_DAO();
			ArrayList<Ranking> rank_list = dao.select_Ranking();

			if (rank_list != null){
				request.getServletContext().setAttribute("rank_list", rank_list);
				response.sendRedirect("ranking_best.jsp");
			}else{
				response.sendRedirect("Fail.html");
			}
		}
		/************************* < 게시판 폼  > ***************************/
		if (command.trim().equals("board")){
			Connect_DAO dao = new Connect_DAO();
			ArrayList<Board> board_list = dao.select_Board();

			if (board_list != null){
				request.getServletContext().setAttribute("board_list", board_list);
				response.sendRedirect("board.jsp");
			}else{
				response.sendRedirect("Fail.html");
			}
		}
	}
}
