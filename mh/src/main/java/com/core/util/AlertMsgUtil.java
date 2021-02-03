package com.core.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class AlertMsgUtil{
	
	public void alertMsg(HttpServletResponse response, String msg, String afterScript) throws Exception{
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		
		PrintWriter out = response.getWriter();
		
		out.print("<script type='text/javascript'>");
		
		if(!msg.equals(""))
			out.print("alert('"+msg+"');");
		
		if(!afterScript.equals(""))
			out.print(afterScript);
		
		out.print("</script>");
		
		out.flush();
		response.flushBuffer();
		
	}
		public void alertMsg(HttpServletResponse response, String afterScript) throws Exception{
		
		this.alertMsg(response, "", afterScript);
		
	}
	
}