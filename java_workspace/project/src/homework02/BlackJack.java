package homework02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlackJack {
	private List<Integer> allCard;
	private List<Integer> playerCard;
	
	public BlackJack() {
		allCard = new ArrayList<>();
		playerCard = new ArrayList<>();
	}

	public void start() {
		System.out.println("게임 시작!");
		createAllCard();
	}
	
	// 전체 카드 세팅
	private void createAllCard() {
		
		for(int i = 1; i < 12; i++) {
			allCard.add(i);
		}
		allCard.addAll(allCard);
		Collections.shuffle(allCard);
		System.out.println("전체 카드 : " + allCard);
		
	}
	
	public List<Integer> getAllCard() {
		return allCard;
	}

	public List<Integer> getPlayerCard() {
		for(int i = 0; i < 2; i++) {
			int card = openCard();
			playerCard.add(card);
		}
		return playerCard;
	}
	
	public int openCard() {
		Random rand = new Random();
		int randIndex = rand.nextInt(allCard.size()) + 1;
		return allCard.remove(randIndex);	
	}
	
	
	
}
