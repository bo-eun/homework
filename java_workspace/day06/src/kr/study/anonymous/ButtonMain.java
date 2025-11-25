package kr.study.anonymous;

public class ButtonMain {
	public static void main(String[] args) {
		
		Button login = new Button();
		
		//login.click(new LoginButton());
		
		// 익명클래스 사용
		// 클래스를 1회성으로 제공해줌
		ButtonClickEvent loginEvt = new ButtonClickEvent() {
			@Override
			public void click() {
				System.out.println("로그인");
			}
		};
		
		// 함수형 인터페이스는 람다식으로 표현할 수 있다.
		// ButtonClickEvent loginEvt2 = () -> System.out.println("로그인");
		// login.click(loginEvt2);
		
		login.click(() -> System.out.println("로그인"));
		
		login.click(loginEvt);
		
	}
}
