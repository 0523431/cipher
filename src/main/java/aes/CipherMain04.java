package aes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CipherMain04 {
	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		PreparedStatement pstmt = conn.prepareStatement("select userid, email from userbackup");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String userid = rs.getString("userid");
			// userid의 해쉬값중 16자리를 key값으로 쓸거야
			String key = CipherUnit.makehash(userid).substring(0,16);
			
			String email = rs.getString("email"); // 암호화되어있는 상태
			String restoreEmail = CipherUnit.decrypt(email, key);
			
			System.out.printf("%s : 이메일 = %s\n", userid, restoreEmail);
		}
	}
}
