package kr.study.inter;

public class RemoteMain {
	public static void main(String[] args) {
		// 인터페이스(기능정의부) - 클래스(기능구현부) 관계의 경우
		// 기능정의 - 기능 구현 관계가 많다.
		// 그래서 객체 선언 시 인터페이스 a = new Class(); 관계로 선언하는 경우가 많다.
		
		// 약한 결합에 의한 상속(인터페이스 - 클래스 간의 상속 관계) <--> 강한 결합에 의한 상속(클래스 - 클래스 간의 상속 관계)
		// 클래스 상속 관계에서는 자식 클래스의 인스턴스를 만들 때 부모 타입을 데이터 타입으로 하면 업캐스팅되어 부모의 멤버변수,메서드만 쓸 수 있는 문제점이 있었음
		// 하지만 인터페이스 - 클래스에서는 인터페이스에서 선언한 추상화 메서드와 상수만을 갖기 때문에 클래스의 데이터타입이 인터페이스가 되어도 된다.
		RemoteControl remote = new Samsung();
		
		remote.turnOn();
		remote.turnOff();
	}
}
