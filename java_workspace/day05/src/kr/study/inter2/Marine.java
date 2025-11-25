package kr.study.inter2;

public class Marine extends GroundUnit{
	
	private int power = 50;

	@Override
	// Unit interface의 추상화 메서드를 구현하여 사용
	public void attack(Unit enermy) {
		Marine m = null;
		// 매개변수가 Marine 클래스의 인스턴스일 경우
		if(enermy instanceof Marine) {
			// Marine으로 강제형변환
			m = (Marine)enermy;
		}
		
		if(m.getHp() <= power) {
			System.out.println(m.getUnitName() + "파괴");
			 enermy = null;
		} else {
			System.out.println(m.getUnitName() + " hp 50감소 ");
			m.setHp(m.getHp() - power);
		}
	}

	@Override
	public void move(int x, int y) {
		System.out.println(this.getX() + ", " + this.getY() + "에서 " + x + ", " + y + " 으로 이동");
		
		this.setX(x);
		this.setY(y);
		
	}
	
}
