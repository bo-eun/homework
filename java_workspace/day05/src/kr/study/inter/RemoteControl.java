package kr.study.inter;

public interface RemoteControl {
	
	
	// interface의 메서드는 무조건 public
	public abstract void turnOn();
	
	// public abstract 생략 가능
	void turnOff();
}
