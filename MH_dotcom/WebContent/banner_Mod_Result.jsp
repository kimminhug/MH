<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest,
				 com.oreilly.servlet.multipart.DefaultFileRenamePolicy,
				 java.util.*,
				 java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>배너 업데이트</title>
</head>
<body>
<% 
	String upload_Path = request.getSession().getServletContext().getRealPath("/") + "img/banner/";
	System.out.println("업로드 폴더 : "+upload_Path);

	int size = 1024 * 1024 * 10;
	File[] remain_files = new File(upload_Path).listFiles();
	System.out.println("남아있던 파일 수 : "+remain_files.length);

	try{
		MultipartRequest multi = new MultipartRequest(request, upload_Path, size, "UTF-8", new DefaultFileRenamePolicy());
		// 다중 업로드 소스 : 전 페이지에서 요청한 모든 파일 업로드 요청 수행
		
	}catch(Exception ex){
		System.out.println("업로드 사항 없음!");
		response.sendRedirect("banner_Modify.jsp");
	}
	
	int del_cnt = 0;
	for(int i=0;i<remain_files.length;i++){

		if (remain_files[i].delete()){
			del_cnt++;
		}
	}
	System.out.println("지운 파일 수 : "+del_cnt);
	
	String[] comp_List = new File(upload_Path).list();
	System.out.println("갱신된 파일 수 : "+comp_List.length);

	response.sendRedirect("admin_main.jsp");
%>
</body>
</html>