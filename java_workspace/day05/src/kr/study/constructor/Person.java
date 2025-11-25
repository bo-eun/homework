package kr.study.constructor;

public class Person {

	private String myName;
	private String gender;
	
	public Person() {} // 기본 생성자함수
	
	public Person(String myName, String gender) {
		this.setGender(gender);
		this.setMyName(myName);
	}
	
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
