package ch10;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.sql.*;

public class BoardServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	// doGet(), doPost() 오버라이딩 할 것.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		
		// super.doGet(req, resp);
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		// super.doGet(req, resp);
		process(req, resp);
	}
	
	void process(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		// 수신 데이터 한글 처리
		req.setCharacterEncoding("UTF-8");
		
		// 데이터베이스 연결 -> (커넥션 풀 이용) DBCPConn 효율성 좋음.
		Connection conn = DBCPConn.getConnection();
		
		// 페이징 처리를 위한 준비
		MyUtil myUtil = new MyUtil();
		
		// 데이터베이스 엑세스 준비
		BoardDAO dao = new BoardDAO(conn);
		
		// 주소줄의 앞 부분 - 절대주소 지정시 필요
		String cp = req.getContextPath();
		
		// 주소줄의 뒷 부분 - 하나의 서블릿으로 여러개의 서블릿 주소 연결시 필요
		String uri = req.getRequestURI();
		String url;
		
		// 주소줄의 서블릿 이름 분석후 URL 이동
		if (uri.indexOf("list.do") != -1){
			// 페이징 처리 부분 추가
			
			// 페이징 번호 받기
			String pageNum = req.getParameter("pageNum");
			int current_page = 1;
			if (pageNum != null)
				current_page = Integer.parseInt(pageNum);
			
			// 전체 데이터 수 확인
			int total_dataCount = 0;
			total_dataCount = dao.getDataCount();
			
			// 전체 페이지 수 확인
			int numPerPage = 5;
			int total_page = myUtil.getPageCount(numPerPage, total_dataCount);
			if (current_page > total_page)
				current_page = total_page;
			
			// 데이터베이스에서 가져올 데이터의 시작과 끝 계산
			int start = (current_page - 1) * numPerPage + 1;
			int end = current_page * numPerPage;
			
			// 특정 페이지 자료 읽어오는 SELECT 쿼리 메소드 호출
			List<BoardDTO> lists = dao.getListsData(start, end);
			
			// 페이지 번호 출력을 위한 준비
			String list_url = "list.do";
			String pageIndexList = myUtil.pageIndexList(current_page, total_page, list_url);
			
			// 데이터베이스의 자료를 출력 페이지로 넘기기 위한 준비 -> setAttribute()
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList", pageIndexList);
			
			// 자유게시판 리스트 페이지 이동
			url = "/BoardList.jsp";
			
			// 페이지 이동 전용 메소드 호출
			forward(req, resp, url);
			
		}else if (uri.indexOf("created.do") != -1) {
			// 자유게시판 글쓰기 화면 페이지 이동
			url = "BoardInsert.jsp";
			
			// 페이지 이동 전용 메소드 호출
			forward(req, resp, url);
			
		}else if (uri.indexOf("content.do") != -1) {
			// 글번호 수신
			int num = Integer.parseInt(req.getParameter("num"));
			
			// 데이터베이스에서 특정 글번호를 가진 자료 읽어오기 -> getReadData(글번호)
			BoardDTO dto = dao.getReadData(num);
			
			// 데이터베이스에서 읽어온 자료를 출력 전용 페이지(BoardContent.jsp)로 넘기기 위한 준비 -> setAttribute()
			req.setAttribute("dto", dto);
			
			// 자유게시판 내용보기 페이지 이동
			url = "BoardContent.jsp";
			
			// 페이지 이동 전용 메소드 호출
			forward(req, resp, url);
			
		}else if (uri.indexOf("delete.do") != -1){
			// 글번호 수신
			String num = req.getParameter("num");
			
			// 패스워드 입력 받는 화면으로 이동 -> 포워딩
			// 글번호 재전송 -> setAttribute()
			req.setAttribute("num", num);
			url = "BoardDelete.jsp";
			forward(req, resp, url);
			
		}else if (uri.indexOf("delete_ok.do") != -1){
			// 글번호, 패스워드 수신 -> getParameter();
			int num = Integer.parseInt(req.getParameter("num"));
			String pwd = req.getParameter("pwd");
			
			// DELETE 쿼리 실행 메소드 호출 -> deleteData(글번호, 패스워드)
			dao.deleteData(num, pwd);
			
			// 리스트 페이지 이동 -> sendRedirect();
			resp.sendRedirect("list.do");
			
		}else if (uri.indexOf("update.do") != -1){
			// 글번호 수신
			int num = Integer.parseInt(req.getParameter("num"));
			
			// 글번호, 패스워드 입력 받는 화면으로 이동 -> 포워딩
			// 글번호 재전송 -> setAttribute()
			BoardDTO dto = dao.getReadData(num);
			req.setAttribute("dto", dto);
			url = "BoardUpdate.jsp";
			forward(req, resp, url);
			
		}else if (uri.indexOf("update_ok.do") != -1) {
			BoardDTO dto = new BoardDTO();
			
			// 수정 내용 및 패스워드 입력 후 수정 버튼 클릭 -> 수정 항목 전
			// 수정 버튼 클릭시 URL 지정, 서블릿 주소 지정, update_ok.do
			// 수정 항목 수신
			dto.setName(req.getParameter("name"));
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content").replace("\n", "<br>"));
			dto.setNum(Integer.parseInt(req.getParameter("pwd")));
			dto.setPwd(req.getParameter("pwd"));
			dto.setIpaddr(req.getRemoteAddr());
			
			// update 쿼리 메소드 호출
			dao.updateData(dto);
			
			// 리스트 페이지 이동
			resp.sendRedirect("list.do");
			
		}else if (uri.indexOf("adminLogin.do") != -1){
			url = "BoardLogin.jsp";
			forward(req, resp, url);
			
		}else if (uri.indexOf("adminLogin_ok.do") != -1){
			// 아이디, 패스워드 수신
			String uid = req.getParameter("uid");
			String upw = req.getParameter("upw");
			
			// 아이디, 패스워드 검사
			if (uid.equals("admin") && upw.equals("1234")) {
				// ① 맞는 경우는 세션 생성 후 BoardList.jsp로 이동
				
				// 세션 생성
				HttpSession session = req.getSession(true);
				session.setAttribute("login", "true");
				
				resp.sendRedirect("list.do");
				
			}else {
				// ② 틀린 경우는 BoardLogin.jsp로 이동
				req.setAttribute("errMsg", "아이디 또는 패스워드가 틀렸습니다!");
				url = "BoardLgoin.jsp";
				forward(req, resp, url);
			}
			
		}else if (uri.indexOf("adminLogout.do") != -1){
			HttpSession session = req.getSession();
			session.invalidate();
			resp.sendRedirect("list.do");
			
		}else if (uri.indexOf("adminDelete.do") != -1){
			req.setAttribute("errMsg", "아이디 또는 패스워드가 틀렸습니다!");
			int num = Integer.parseInt(req.getParameter("num"));
			dao.deleteData(num);
			resp.sendRedirect("list.do");
		}
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url)
		throws ServletException, IOException {
		// 외부에서 전달된 url 변수의 값을 가지고, 페이지 이동을 위한 포워딩 실행!
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
}
