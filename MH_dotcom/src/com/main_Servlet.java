package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class main_Servlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		
		System.out.println("uri = "+ uri);
		// 요청받은 URI
		
		String command = uri.substring(uri.lastIndexOf("/") + 1);
		System.out.println("commend = "+ command);
		// 요청받은 사이트 이름
		
		/************************* < 메인 폼  > ***************************/
		if (command.trim().equals("admin")){
			request.getRequestDispatcher("admin.html").forward(request, response);
		}
	}
}
