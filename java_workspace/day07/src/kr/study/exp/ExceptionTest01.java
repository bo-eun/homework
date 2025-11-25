package kr.study.exp;

public class ExceptionTest01 {
	public static void main(String[] args) {
		int num = 10;
		int result = 0;
		
		// 오류 발생. 수학적 오류
		result = num / 0;
		
		System.out.println(result);
	}
}
