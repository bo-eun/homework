package kr.study.inter;

public class Samsung implements RemoteControl {

	@Override
	public void turnOn() {
		System.out.println("삼성 티비 켜짐");
	}

	@Override
	public void turnOff() {
		System.out.println("삼성 티비 꺼짐");
	}
	
}
