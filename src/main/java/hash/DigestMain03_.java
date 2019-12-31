package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DigestMain03_ {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/classdb","scott","1234");
		PreparedStatement pstmt = conn.prepareStatement("select password from userbackup where userid=?");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("아이디 : ");
		String id = br.readLine();
		System.out.print("비밀번호 : ");
		String inputpass = br.readLine();
		
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			System.out.println("아이디 확인");
			// 내가 입력한 비밀번호를 암호화하는 과정
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String hashpass = "";
			byte[] plain = inputpass.getBytes();
			byte[] hash = md.digest(plain);
			for(byte h : hash) {
				hashpass += String.format("%02X", h);
			}
			if(rs.getString(1).equals(hashpass)) {
				System.out.print("반갑습니다 ^_^ "+id+"님 로그인되었습니다");
			} else {
				System.out.println("비밀번호 확인(비밀번호 틀림)");
			}
		} else {
			System.out.println("해당 아이디 없음");
		}
	}
}
