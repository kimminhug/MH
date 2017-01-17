package ch10;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class DBCPConn {
	private static Connection conn = null;
	
//	< 컨넥션풀 jar 파일 연동 >----------------------------------------------------------------------------
//	해당 프로젝트 - WebContent - WEB-INF - lib 폴더에 collection, dbcp, pool 3가지 jar 파일 필요.
//	context 객체 선언 DataSource 객체에 해당 리소스이름(mysql)을 연결시킴.
// --------------------------------------------------------------------------------------------------------
	public static Connection getConnection(){
		if (conn == null) {
			try {
				Context ctx = new InitialContext();
				Context evt = (Context)ctx.lookup("java:/comp/env");
				DataSource ds = (DataSource)evt.lookup("jdbc/mysql");	// 리소스명 : mysql
				
				conn = ds.getConnection();
				// 얻어진 풀로 커넥션을 얻는다!
				
			}catch (Exception e){
				System.out.println(e.toString());
			}
		}
		
		return conn;
	}
	
	public static void close() {
		if (conn != null){
			try {
				if (!conn.isClosed())
					conn.close();
				
			}catch (Exception e){
				System.out.println(e.toString());
			}
		}
		
		conn = null;
	}
}
