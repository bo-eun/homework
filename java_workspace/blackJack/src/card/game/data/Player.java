package card.game.data;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	public Player(int money) {
		this.money = money;
	}
	
	private int money;
	private List<Integer> cards;
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public List<Integer> getCards() {
		if(this.cards == null) {
			this.cards = new ArrayList<>();
		}
		return cards;
	}
	public void setCards(List<Integer> cards) {
		this.cards = cards;
	}
	
	public int myNumber() {
		// mapToInt() 요소를 int로 형변환 해줌
		// sum()을 사용하려면 mapToInt()를 사용해야 해서 이렇게 함...
		int result = cards.stream().mapToInt(x -> x).sum();
		
		return result;
	}
	
}
