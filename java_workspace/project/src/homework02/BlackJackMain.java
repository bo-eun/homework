package homework02;

public class BlackJackMain {
	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		game.start();
		
		 Player p1 = new Player(game.getPlayerCard(), 10000);
		 Player pc = new Player(game.getPlayerCard(), 10000);
		 
		 System.out.println(p1.getCard());
		 System.out.println(pc.getCard());

	}
}	
