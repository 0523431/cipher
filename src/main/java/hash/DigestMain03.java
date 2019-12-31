package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* 	화면에서 아이디와 비밀번호를 입력받아서
 * 	1. 해당 아이디가 userbackup테이블에 없으면 "아이디 확인" 출력
 * 	2. 해당 아이디의 비밀버호를 비교해서 맞으면 "반갑습니다. 아이디님" 출력
 * 	3. 해당 아이디의 비밀버호를 비교해서 틀리면 "비밀번호 확인" 출력
 */

public class DigestMain03 {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("아이디 : ");
		String id = br.readLine();
		
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		PreparedStatement pstmt = conn.prepareStatement("select * from userbackup where userid=?");
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			System.out.println("아이디 확인");
			
			System.out.print("비밀번호 : ");
			String pass = br.readLine();
			pstmt = conn.prepareStatement("select * from userbackup where password=?");
			pstmt.setString(1, pass);
			rs = pstmt.executeQuery();
			if(rs.getString(1).equals(pass)) {
				System.out.print("반갑습니다 ^_^ "+id+"님");
			} else {
				System.out.println("비밀번호 확인(비밀번호 틀림)");
			}
		} else {
			System.out.println("해당 아이디 없음");
		}
		
	}
}
