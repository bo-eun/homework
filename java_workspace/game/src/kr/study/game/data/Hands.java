package kr.study.game.data;

public enum Hands {
	GAWI(1, "가위"),
	BAWI(2, "바위"),
	BO(3, "보");
	
	private int value;
	private String name;
	
	private Hands(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public static Hands getHand(int value) {
		for(Hands h : Hands.values()) {
			if(h.getValue() == value) {
				return h;
			}
		}
		throw new IllegalArgumentException("No searchType : " + value);
	}
}
