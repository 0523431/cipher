package aes;

// 키를 설정해서 AES 암호화 하기 (암호화한 사람이 키를 알고 있음)
public class CipherMain02 {
	public static void main(String[] args) {
		System.out.println("키 abc1234567를 이용해서 암호화, 복호화 하기");
		
		String plain1 = "안녕하세요. 돌배차입니다.";
		String key = "abc1234567";
		String cipher1 = CipherUnit.encrypt(plain1, key);
		System.out.println("암호문 : " + cipher1); // 정해진 키를 사용하기때문에 항상 동일한 암호문이 출력됨
		
		String plain2 = CipherUnit.decrypt(cipher1, key);
		System.out.println("복호문 : " + plain2);
	}
}
