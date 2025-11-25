package kr.study.abs;

public class Pikachu extends Pokemon {

	@Override
	public void attack() {
		System.out.println(this.getMyName() + "가 백만볼트 공격!!");
	}
	
}
