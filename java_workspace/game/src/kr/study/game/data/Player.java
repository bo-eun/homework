package kr.study.game.data;

public class Player implements Comparable<Player>{
	private String name;
	private int winCount;
	
	public Player(String name) {
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWinCount() {
		return winCount;
	}
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	
	// 출력을 편하게 하기
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("이름 : ").append(this.getName());
		sb.append(", 이긴 수 : ").append(this.getWinCount());
		
		return sb.toString();
	}

	@Override
	public int compareTo(Player p) {
		// TODO Auto-generated method stub
		return this.getWinCount() < p.getWinCount() ? 1 : -1;
	}
}
