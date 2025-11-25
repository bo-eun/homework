package card.game.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackjackWorld {
	private List<Integer> bankCard;
	private Random rand;
	private Scanner scan;
	
	private Player me;
	private Player you;
	
	private static final int MY_MONEY = 100000;
	private static final int BATTING_MONEY = 10000;
	private static final int BLACK_JACK = 21;
	
	
	public BlackjackWorld() {
		this.bankCard = new ArrayList<>();
		this.rand = new Random();
		this.scan = new Scanner(System.in);
	}
	
	public void initPlayer() {
		System.out.println("대결... 매치 중...");
		this.me = new Player(MY_MONEY);
		this.you = new Player(MY_MONEY);
	}
	
	public void initBank() {
		this.bankCard.clear(); // 기존에 내용이 있다면 삭제
		System.out.println("뱅커 카드를 생성 중입니다...");
		for(int i = 1; i < 12; i++) {
			this.bankCard.add(i);
		}
		// 자기 자신을 한번 더 한다.
		this.bankCard.addAll(bankCard);
		
		// 뱅커가 카드를 섞는다.
		for(int i = 0; i < 5; i ++) {
			Collections.shuffle(bankCard);
		}
	}
	
	public void startGame() {
		System.out.println("-------------------------------------------------");
		System.out.println("|                                                |");
		System.out.println("|                    블랙잭 게임                    |");
		System.out.println("|                                                |");
		System.out.println("-------------------------------------------------");
		
		initPlayer();
		initBank();
		
		while(true) {
			turnCard(); // 2장씩 받기
			openCard();
			
			// 결과가 승리(-1)라면 반복문 종료
			if(compareGame() == -1) {
				System.out.println("프로그램이 종료됩니다.");
				break;
			} else {
				// 결과가 패 라면 플레이어, 카드 초기화
				initPlayer();
				initBank();
			}
		}
		
	}
	
	public void turnCard() {
		int count = 0;
		int cardSize = this.bankCard.size();
		
		while(count < 4) {
			int randIndex = rand.nextInt(cardSize - count);
			int cardNumber = this.bankCard.get(randIndex);
			if(count % 2 == 0) {
				this.me.getCards().add(cardNumber);
			} else {
				this.you.getCards().add(cardNumber);
			}
			
			this.bankCard.remove(randIndex);
			count++;
		}
		
		System.out.println("플레이어와 대전상대에게 2장씩 카드를 받았습니다.");
	}
	
	public void openCard() {
		System.out.println("플레이어 카드 : " + this.me.getCards());
		System.out.println("PC 카드 : " + this.you.getCards());
		
		System.out.println("플레이어는 한 장의 카드를 더 받을 수 있습니다. 받으시겠습니까?(y/n)");
		String answer = scan.next();
		answer = answer.toUpperCase();
		
		if(answer.equals("Y") || answer.equals("YES")) {
			System.out.println("플레이어에게 카드 한 장을 더 줍니다.");
			int randomIndex = rand.nextInt(this.bankCard.size());
			int num = this.bankCard.get(randomIndex);
			this.bankCard.remove(randomIndex);
			
			System.out.println(String.format("플레이어는 %d 카드를 받았습니다.", num));
			this.me.getCards().add(num);
		}
	}
	
	public int compareGame() {
		// 비교
		int meTotal = this.me.myNumber();
		int youTotal = this.you.myNumber();
		
		System.out.println("내 카드 숫자 : " + meTotal + ", 대전상대 숫자 : " + youTotal);
		
		// 일찍 리턴 패턴
		if(meTotal == youTotal) {
			System.out.println("무승부 입니다. 다시!");
			return 1;
		}
		
		if(meTotal > BLACK_JACK && youTotal > BLACK_JACK) {
			// 합이 21를 초과하고, 내 합이 상대 합보다 클 경우 짐.
			if(meTotal - youTotal > 0) {
				System.out.println("대전 상대가 이겼습니다!");
				System.out.println(BATTING_MONEY + "를 잃습니다.");
				me.setMoney(me.getMoney() - BATTING_MONEY);
				you.setMoney(you.getMoney() + BATTING_MONEY);
			} else {
				System.out.println("플레이어가 이겼습니다!");
				System.out.println(BATTING_MONEY + "를 얻습니다.");
				me.setMoney(me.getMoney() + BATTING_MONEY);
				you.setMoney(you.getMoney() - BATTING_MONEY);
			}
		} else if(meTotal > BLACK_JACK || youTotal > BLACK_JACK) {
			
			if(meTotal > BLACK_JACK) {
				System.out.println("대전 상대가 이겼습니다!");
				System.out.println(BATTING_MONEY + "를 잃습니다.");
				me.setMoney(me.getMoney() - BATTING_MONEY);
				you.setMoney(you.getMoney() + BATTING_MONEY);
			} else {
				System.out.println("플레이어가 이겼습니다!");
				System.out.println(BATTING_MONEY + "를 얻습니다.");
				me.setMoney(me.getMoney() + BATTING_MONEY);
				you.setMoney(you.getMoney() - BATTING_MONEY);
			}
			
		} else {
			
			if((BLACK_JACK - meTotal) > (BLACK_JACK - youTotal)) {
				System.out.println("대전 상대가 이겼습니다!");
				System.out.println(BATTING_MONEY + "를 잃습니다.");
				me.setMoney(me.getMoney() - BATTING_MONEY);
				you.setMoney(you.getMoney() + BATTING_MONEY);
			} else if(BLACK_JACK - meTotal < (BLACK_JACK - youTotal)) {
				System.out.println("플레이어가 이겼습니다!");
				System.out.println(BATTING_MONEY + "를 얻습니다.");
				me.setMoney(me.getMoney() + BATTING_MONEY);
				you.setMoney(you.getMoney() - BATTING_MONEY);
			}
			
		}
		
		System.out.println("플레이어 보유 머니 : " + me.getMoney());
		System.out.println("PC 보유 머니 : " + you.getMoney());
		
		if(me.getMoney() <= 0) {
			System.out.println("플레이어 파산!");
			return -1;
		} else if(you.getMoney() <= 0) {
			System.out.println("PC 파산!");
			return -1;
		}
		
		return 1;
	}
}
