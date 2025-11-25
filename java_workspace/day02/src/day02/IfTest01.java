package day02;

public class IfTest01 {

	public static void main(String[] args) {
		
		// 랜덤함수 만들기
		// 최소 50에서 최대 100 사이 값 랜덤 출력
		int score = (int)(Math.random() * (100-50 +1)) + 50;
		
		if(score >= 65) {
			System.out.println("합격, 점수 : " + score);
		} else {
			System.out.println("불합격, 점수 : " + score);
		}
	}
}
