package aes;

// AES 암호화 (키를 모르는 상태)
public class CipherMain01 {
	public static void main(String[] args) {
		
		String plain1 = "안녕하세요. 돌배차입니다.";
		String cipher1 = CipherUnit.encrypt(plain1);
		System.out.println("암호문 : " + cipher1); // 랜덤키를 사용하기때문에, 매번 암호는 바뀜
		
		String plain2 = CipherUnit.decrypt(cipher1);
		System.out.println("복호문 : " + plain2);
	}
}
