package sh.manage.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * 학생 클래스
 * 학생 정보를 담는 클래스
 * 
 * */

public class Student {
	
	private String myName;
	private int kor;
	private int eng;
	private int math;
	
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	// 총 성적
	public int getTotal() {
		return this.getKor() + this.getEng() + this.getMath();
	}
	
	public double getAvg() {
		return new BigDecimal(this.getTotal() / 3.0).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
	
	// 고유 코드값 비교
	// 보통 대표값의 hashCode를 이용
	public int hashCode() {
		return this.getMyName().hashCode();
	}
	
	// 대표값 비교
	// 현재 객체와 매개변수로 넘어온 객체의 이름이 같은지 확인하는 함수
	// equals는 매개변수로 Object타입을 받는다.
	public boolean equals(Object o) {
		if( !(o instanceof Student) ) {
			return false;
		}
		
		// 매개변수를 Object타입으로 받아서 아래에서 강제 형변환해준다.
		Student comp = (Student)o;
		return this.getMyName().equals(comp.getMyName());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("이름 : " + this.getMyName() + ", ");
		sb.append("국어 : " + this.getKor() + ", ");
		sb.append("영어 : " + this.getEng() + ", ");
		sb.append("수학 : " + this.getMath() + ", ");
		sb.append("총점 : " + this.getTotal() + ", ");
		sb.append("평균 : " + this.getAvg());
		return sb.toString();
	}
	
}
