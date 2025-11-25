package kr.study.game.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import kr.study.game.data.Hands;
import kr.study.game.data.Player;

public class PlayGameService {
	
	// 코딩에서 숫자를 직접 쓰는 것은 피하는 것이 좋다.
	private static final int GAWI = 1; // 가위
	private static final int BAWI = 2; // 바위
	private static final int BO = 3; // 보
	
	private List<Player> players;
	private Random rand;
	
	public PlayGameService() {
		players = new ArrayList<>();
		rand = new Random();
	}
	
	private void initPlayers() {
		players.add(new Player("철수"));
		players.add(new Player("영수"));
		players.add(new Player("민수"));
	}
	
	public void startGame() {
		System.out.println("=====================================================");
		System.out.println("=====================================================");
		System.out.println("=====================================================");
		
		// 초기화
		this.initPlayers();
		// 플레이 게임
		this.playGame();
		// 결과보기
		this.gameResult();
	}
	
	private void playGame() {
		// 가위 바위 보
		// 철수 VS 민수, 철수 VS 영수, 민수 VS 영수
		
		for(int i = 0; i < players.size(); i++) {
			for(int j = i + 1; j < players.size(); j++) {
				// 실제 게임 실행
				this.matchGames(players.get(i), players.get(j));
			}
		}
	}
	
	private void matchGames(Player p1, Player p2) {
		System.out.println(" [" + p1.getName() + " vs " + p2.getName() + "] ");
		
		int count = 0;
		
		do {
			
			int p1Choice = rand.nextInt(3) + 1;
			int p2Choice = rand.nextInt(3) + 1;
			
			String PlayGameText = String.format("%2d회차 > %s(%s) vs %s(%s)  ",
					count + 1, 
					p1.getName(), Hands.getHand(p1Choice),
					p2.getName(), Hands.getHand(p2Choice));
			
			System.out.println(PlayGameText);
			
			// 비교 결과에 따른 카운트 증가
			int result = compare(p1Choice, p2Choice);
			
			if(result == 0) {
				continue;
			}
			
			if(result == 1) {
				p1.setWinCount(p1.getWinCount() + 1);
			} else {
				p2.setWinCount(p2.getWinCount() + 1);
			}
			count ++;
			
		} while(count < 10);
	}
	
	// 결과 보기
	private void gameResult() {
		System.out.println("=================================================");
		System.out.println("게임 결과 : ");
		for(Player p : players) {
			System.out.println(p);
		}
		
		// 내림차순 정렬
		Collections.sort(players);
		
		System.out.println("=================================================");
		System.out.print("제일 많이 이긴 사람 : ");
		System.out.println(players.get(0).getName());
	}
	
	// 비교
	private int compare(int p1Choice, int p2Choice) {
		int result = 0;
		if((p1Choice == GAWI && p2Choice == BO) ||
				(p1Choice == BAWI && p2Choice == GAWI) ||
				(p1Choice == BO && p2Choice == BAWI)) {
			result = 1;
		} else if(p1Choice == p2Choice) {
			result = 0;
		} else {
			result = 2;
		}
		
		return result;
	}
}
