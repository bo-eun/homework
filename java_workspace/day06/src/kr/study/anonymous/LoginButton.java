package kr.study.anonymous;

public class LoginButton implements ButtonClickEvent {
	// 인터페이스 - 클래스 : 동작구현을 위한 관계
	// 기능을 위한 관계이므로 다중 상속도 가능
	@Override
	public void click() {
		System.out.println("로그인");
	}
}
