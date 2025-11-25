package kr.study.exp;

public class ExceptionTest02 {
	public static void main(String[] args) {
		int num = 10;
		int result = 0;

		// 예외 발생 가능성이 있는 코드를 try{...} 안에 작성
		try {
			
			// 오류 발생. 수학적 오류
			result = num / 0;
			System.out.println(result);	
			
		} catch(ArithmeticException e) {
			
			// 예외처리 내용
			System.out.println("0으로 나누기 안됨!!");
			
		}
		
		System.out.println("종료");

	}
}
