package kr.study.loop2;

public class ContinueTest {
	public static void main(String[] args) {
		
		int sum = 0;
		int count = 0;
		for(int i = 0; i < 10; i++) {
			int random = (int)(Math.random() * 20) + 1;
			count++;
			
			if(random % 2 == 1) {
				i--;
				continue; // 여기서 실행문 종료
			}
			System.out.print(random + "\t");
			sum += random;
		}
		
		System.out.println();
		System.out.println("시도횟수 : " + count + "\t합 :" + sum);
	}
}
