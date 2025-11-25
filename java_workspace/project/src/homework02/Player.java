package homework02;

import java.util.List;

public class Player {
	private List<Integer> card;
	private int cash;
	
	public Player(List<Integer> card, int cash) {
		this.card = card;
		this.cash = cash;
	}
	
	public List<Integer> getCard() {
		return card;
	}
	public void setCard(List<Integer> card) {
		this.card = card;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
}
