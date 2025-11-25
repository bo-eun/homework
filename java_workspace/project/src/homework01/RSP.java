package homework01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Person {
	private String name;
	private int winCount;
	private ArrayList<Integer> game;
	
	public Person(String name, int winCount) {
		this.name = name;
		this.winCount = winCount;
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

	public ArrayList<Integer> getGame() {
		return game;
	}

	public void setGame(ArrayList<Integer> game) {
		this.game = game;
	}
	
}

public class RSP {
	public static void main(String[] args) {
		
		Person [] players = new Person [3];
	
		players[0] = new Person("철수", 0);
		players[1] = new Person("영수", 0);
		players[2] = new Person("민수", 0);
		
		// 가위바위보 배열 추가
		for(int i = 0; i < players.length; i++) {
			ArrayList<Integer> randomArr = new ArrayList<>();

			for(int j = 0; j < 10; j++) {
				// 1: 가위, 2: 바위, 3: 보
				int random = (int)(Math.random() * 3) + 1;
				randomArr.add(random);
			}
			players[i].setGame(randomArr);
		}
		
		// 셋이서 같이 한번에 가위바위보 하기 위해 이중배열로 저장
		ArrayList<ArrayList<Integer>> allGames = new ArrayList<>();
		for(int i = 0; i < players[0].getGame().size(); i++) {
			ArrayList<Integer> innerArr = new ArrayList<>();
			
			for(int j = 0; j < players.length; j++) {
				innerArr.add(players[j].getGame().get(i));
				
			}

			// 모두 같거나 아예 다를 경우(비긴경우)를 빼고 배열에 넣는다.
			if(new HashSet<>(innerArr).size() == 1 || new HashSet<>(innerArr).size() == innerArr.size()) {
				continue;
			};
			// 비긴 경우를 제외하고 배열에 넣기
			allGames.add(innerArr);
		}
		
		System.out.println("전체 게임(비긴 경우 제외) : " +allGames);
		
		for(ArrayList<Integer> game : allGames) {
			int min = Collections.min(game);
			int max = Collections.max(game);
			// 1: 가위, 2: 바위, 3: 보, 가위와 보가 만났을 때 가위가 이긴다
			int winNum = (min == 1 && max == 3) ? min : max;
			
			for(int i = 0; i < game.size(); i++) {
				if(game.get(i) == winNum) {
					players[i].setWinCount(players[i].getWinCount() + 1);
				}
			}
		}
		
		// 가장 많이 이긴 횟수
		int maxWin = 0;
		// 가장 많이 이긴 횟수 구하기
		for(Person p : players) {
			System.out.println("이긴 횟수 : " + p.getName() + " " + p.getWinCount());
			if(p.getWinCount() > maxWin) {
				maxWin = p.getWinCount();
			}
		}
		
		// 가장 많이 이긴 사람 출력
		System.out.print("가장 많이 이긴 사람 : ");
		for(Person p : players) {
			if(p.getWinCount() == maxWin) {
				System.out.print(" " + p.getName());
			}
			
		}
	}
}
