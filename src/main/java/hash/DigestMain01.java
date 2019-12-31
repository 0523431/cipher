package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestMain01 {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		byte[] plain =null;
		byte[] hash =null;
		String[] algo = {"MD5", "SHA-1", "SHA-256", "SHA-512"};
		System.out.println("해쉬값을 구할 문자열을 입력하세요");
		// plain : 입력받은 값
		plain = br.readLine().getBytes();
		System.out.println("평문 : " + new String(plain));
		for(String algorithm : algo) {
			// 아래 두줄이면 암호화 끝!
			// 알고리즘이 java안에서 공개되어있기때문에 넣어서 hash값을 구해?
			MessageDigest md = MessageDigest.getInstance(algorithm);
			hash = md.digest(plain);
			// byte : hash.length
			System.out.println(algorithm + "해쉬값크기 : " + (hash.length*8) + "bits");
			System.out.print("해쉬값 : ");
			for(byte h : hash) {
				System.out.printf("%02X", h); // bits단위인 02X을 16진수값으로 보여줌
			}
			System.out.println();
		}
	}
}
