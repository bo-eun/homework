package day02;

public class OperatorTest02 {

	public static void main(String[] args) {
		
		int num = 10;
		int sum = 0;
		
		// sum에 값을 대입
		sum = num++;
		
		// %d = 정수, %s = 문자, %f = 실수
		System.out.println(String.format("sum = %d, num=%d",sum, num));
		
		
		sum = ++num;
		
		// %d = 정수, %s = 문자, %f = 실수
		System.out.println(String.format("sum = %d, num=%d",sum, num));
		
		boolean isTrue = true;
		
		System.out.println(!isTrue);
		System.out.println(isTrue);
	}

}
