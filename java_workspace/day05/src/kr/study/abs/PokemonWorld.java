package kr.study.abs;

public class PokemonWorld {
	public static void main(String[] args) {
		Ggobuki ggobuk = new Ggobuki();
		
		ggobuk.setMyName("꼬부기");
		ggobuk.attack();
		
		Pikachu pika = new Pikachu();
		
		pika.setMyName("피카츄");
		pika.attack();
	}
}
