package kr.study.override;

public class Student {
	
	private String myName;
	private int score;
	
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	// 부모가 준 메서드를 제정의(내용을 변경)
	// toString()은 최상위 클래스 Object 가 준 것
	// @Override 어노테이션이라는 문법, 메서드나 클래스에다가 기능, 역할, 상태를 부여하는 역할
	// 어노테이션 중 유일하게 @Override 문구 생략 가능함
	// eclipse는 가끔 @Override구문에 오류를 띄워 오류가 나면 주석처리 함
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "이름 : " + this.getMyName() + ", ";
		str += "점수 : " + this.getScore();
		return str;
	}
}
