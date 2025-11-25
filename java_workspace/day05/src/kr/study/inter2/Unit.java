package kr.study.inter2;

// interface Attack과 Move를 상속한 Unit interface
public interface Unit extends Attack, Move {
	void attack(Unit enermy);
}
