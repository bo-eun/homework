package kr.study.constructor;

public class MultiCastingExam {
	public static void main(String[] args) {
		// 다형성을 이용한 객체 선언, 생성
		// 클래스 상속에서는 잘 사용하진 않지만 가끔 쓴다.
		// 부모타입으로 자식클래스를 선언
		// 어떤 객체를 본인 말고 다른 타입으로 선언하면
		// 선언할 때 지정한 클래스가 가진 메서드 또는 변수만 사용 가능
		// 예를 들어 emp가 가진 salary나 company는 사용 불가
		// 이유는 본질은 직장인이지만 대표 타입은 Person이기 때문에 부모의 변수, 메서드만 사용 가능함
		// 이 인스턴스의 본질은 Employee 생성자이다.
		// 업캐스팅이라고 함
		Person em01 = new Employee();
		
		em01.setMyName("김땡땡");
		em01.setGender("남자");
		
		// em01의 본질은 Employee이기 떄문에(Employee 생성자함수로 인스턴스 생성했기 때문에)
		// Employee에서 메서드 오버라이딩한 toString()이 실행된다.
		System.out.println(em01);
		
		// 강제 형변환을 통한 타입 변경
		// 본인이 가진 타입만 가능
		Employee me = (Employee)em01;
		me.setCompany("코리아IT");
		me.setSalary("400만원");
		
		System.out.println(me);
	}
}
