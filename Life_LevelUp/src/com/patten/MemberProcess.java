package com.patten;
/* [MVC 패턴] 중, Controller를 맡고 있는 객체 - MemberProcess */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberProcess	extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		// System.out.println("uri = "+uri);
		
		String command = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".kim"));
		
		if (command != null && command.trim().equals("register")){
			request.setCharacterEncoding("UTF-8");
			String ID = request.getParameter("ID");
			String salt = "temp";
			String hash = request.getParameter("hash");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String area = request.getParameter("area");
			
			Member_DTO dto = new Member_DTO();
			dto.setSex(Integer.parseInt(sex));
			dto.setAge(Integer.parseInt(age));
			dto.setID(ID);
			dto.setSalt(salt);
			dto.setHash(hash);
			dto.setName(name);
			dto.setArea(area);
			
			/*Level_DTO dto2 = new Level_DTO();
			dto2.setID(ID);*/
			
			Connect_DAO dao = new Connect_DAO();
			boolean bool = dao.insertMember(dto);
			
			if (bool){
				response.sendRedirect("Success.html");
			}else{
				response.sendRedirect("Fail.html");
			}
			
		}else if(command.trim().equals("uqdate")){
			
		}
	}
}
