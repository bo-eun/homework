package kr.study.loop2;

public class BreakTest {
	public static void main(String[] args) {
		// 랜덤함수를 사용하여 합이 50이 넘으면 종료
		int sum = 0;
		for(int i = 0; i < 10; i++) {
			int random = (int)(Math.random() * 20) + 1;
			sum += random;
			System.out.print(random + "\t");
			
			if(sum > 50) {
				break;
			}
		}
		
		System.out.println("\n" + sum);
	}
}
