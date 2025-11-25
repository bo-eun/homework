package kr.study;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Student {

	String myName;
	int kor;
	int eng;
	int math;
	
	// 사용자가 생성자 지정
	// System은 별도의 생성자를 만들지 않고 그대로 사용함.
	public Student() {
		
	};
	
	// 총점
	public int getTotal() {
		return kor + eng + math;
	}
	
	// 평균
	public double getAvg() {
		double avg = getTotal() / 3.0;
		
		// 실수를 소수점 둘째자리까지 반올림되어 보여지게 작업
		// 실수를 다룰 떄 BigDecimal 클래스 사용
		// setScale(자릿수, 처리할 내용)
		// doubleValue() 리턴할 타입 처리 메서드
		avg = new BigDecimal(avg).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return avg;
	}
}
