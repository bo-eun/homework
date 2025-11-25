package kr.study.game;

import kr.study.game.service.PlayGameService;

public class PlayGame {

	public static void main(String[] args) {
		
		PlayGameService p = new PlayGameService();
		p.startGame();
	}

}
