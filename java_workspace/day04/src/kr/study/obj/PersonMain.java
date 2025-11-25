package kr.study.obj;

public class PersonMain {

	public static void main(String[] args) {

		// 클래스 생성 = 클래스 선언 = 클래스의 객체화 
		Person p01 = new Person();
		
		// 변수, 메서드 호출
		p01.myName = "김철수";
		p01.age = 30;
		p01.info();
		
	}

}
