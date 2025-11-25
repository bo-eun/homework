package kr.study.inter2;

// Unit interface를 상속한 GroundUnit 추상화 클래스
// Unit interface의 추상화 메서드를 구현하지 않아 GroundUnit은 추상화 클래스가 된다.
public abstract class GroundUnit implements Unit{
	
	private String unitName;
	private int hp;
	private int x;
	private int y;
	
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
