package kr.study.loop;

public class ForLoopTest01 {
	public static void main(String[] args) {
		int sum = 0;
		
		for(int i = 1; i <= 100; i++) {
			sum += i;
		}
		
		System.out.println("합 : " + sum);
		
		sum = 0;
		
		for(int i = 100; i > 0; i--) {
			sum += i;
		}
		
		System.out.println("합 : " + sum);
		
		// random함수를 사용하여 for문 10번 돌려 짝수인 것만 더해 합을 구하라
		int sum2 = 0;
		for(int i = 0; i < 10; i++) {
			int randomNum = (int)(Math.random() * 50) + 1;
			
			if(randomNum % 2 == 0) {
				sum2 += randomNum;
				System.out.print(randomNum + " + ");
			}
		}
		
		System.out.println(" = " + sum2);
	}
}
