package aes;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* 
	useraccount테이블의 email값을 읽어서 userbackup테이블의 email을 암호화 하기
	(key값은 userid값을 hash코드값으로 만들어서 16자리로 정한다)
 */

public class CipherMain03 {
	public static void main(String[] args) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		PreparedStatement pstmt = conn.prepareStatement("select userid, email from useraccount");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String userid = rs.getString(1);
			// String userid = rs.getString("userid"); : column명으로 값을 가져올 수 있음
			
			String email = rs.getString(2);
			System.out.println(userid +" / "+ email);
			
			// 이건 내가 임의로 ABCD..를 붙여서 16자리로 만들어서 키를 만드는거고.. ㅋㅋㅋㅋㅋㅋ
			// String key = userid;
			
			// userid의 해쉬값중 16자리를 key값으로 쓸거야
			String key = CipherUnit.makehash(userid).substring(0,16);
			String cipherEmail = CipherUnit.encrypt(email, key);
			System.out.println("암호화 : "+cipherEmail);
			
//			String restoreEmail = CipherUnit.decrypt(cipherEmail, key);
//			System.out.println("복호화 : "+restoreEmail);
			
			pstmt.close();
			pstmt = conn.prepareStatement("update userbackup set email=? where userid=?");
			pstmt.setString(1, cipherEmail);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		}
	}
}
