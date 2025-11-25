package kr.study.statics;

public class Calculator {
	
	// 상수
	// 상수는 모든 문자를 대문자로 표현
	// 음절은 언더바(_)를 사용하여 이어 쓴다.
	public static final double PI = 3.14;
	
	

	// 일반변수 또는 메서드는 static 메서드에서 사용하지 못함.
	// 메모리에 값이 저장되는 시점이 다르기 때문
	// private int r;
	
	// 따라서 static 메서드는 매개변수를 받아 사용
	public static double getCircleWidth(int r) {
		return (r * r) * 3.14;
	}
	
}
