package kr.study.statics;

public class CalMain {
	public static void main(String[] args) {
		
		// Calculator.getCircleWidth() - 클래스 메서드로 인스턴스 생성 없이 바로 호출 가능
		double width = Calculator.getCircleWidth(5);
		System.out.println(width);
		
	}
}
