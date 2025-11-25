package kr.study.loop;

public class DoubleLoopTest01 {
	public static void main(String[] args) {
		// 구구단을 2 ~ 9단까지 만들어보자
		for(int i = 2; i < 10; i++) {
			for(int j = 1; j < 10; j++) {
				System.out.print(i + " X " + j + " = " + i * j + "\t");
			}
		
			// 단이 바뀌면 줄바꿈
			System.out.println();
		}
	}
}
