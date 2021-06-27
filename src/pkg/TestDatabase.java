package pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabase {
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String user = "SYSTEM";
	private static final String password = "093410";
	private static final String ipAddress = "localhost";
	private static final String url = "jdbc:oracle:thin:@" + ipAddress + ":1521:xe";
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
			String query = "SELECT * FROM ALL_TABLES";
			
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			System.out.println("<테이블 출력 결과>");
			
			while(rs.next()) {
				System.out.println(rs.getString(1));  // 쿼리문 실행 후 결과값 출력
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}