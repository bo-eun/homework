package kr.study.homework;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MyLotto {
	public static void main(String[] args) {
		
		// 랜덤 함수
		Random random = new Random();
		
		// 45개 공이 들어있는 배열
		int[] balls = new int[45];
		
		int count = 0;
		
		// 45개 넣기
		for(int i = 0; i < 45; i++) {
			balls[i] = (i + 1);
		}
		
		// 시스템 로또 + 보너스 번호
		int bonusNum = 0;
		int[] lotto = new int[6];
		
		// 45개의 공 중에 로또 번호 뽑기
		while(count < 7) {
			int index = random.nextInt(45); // 0 <= x < 숫자 사이에서 랜덤값 x는 정수
			
			// 해당 위치값이 0이면 이미 추출된 번호
			if(balls[index] == 0) {
				continue;
			}
			
			// count가 6보다 작으면 아직 로또 번호 생성 중
			// count == 6 이면 로또번호는 완성. 보너스번호만 있으면 됨
			if(count < 6) {
				lotto[count++] = balls[index];
				balls[index] = 0;
			} else {
				bonusNum = balls[index];
				break;
			}
		}
		
		// 로또 번호와 보너스 번호 출력해보기
		System.out.println("로또 : " + Arrays.toString(lotto) + "\t보너스 : " + bonusNum);
		
		
		// 사용자 로또 만들기
		int[] users = new int[6];
		Scanner scan = new Scanner(System.in);
		
		// 카운트 재활용
		count = 0;
		
		while(count < 6) {
			System.out.println( (count + 1) + " 번째 번호 : " );
			int ball = scan.nextInt();
			scan.nextLine(); // 버퍼(임시 메모리 공간) 비우기
			
			// 입력 번호 유효체크
			if(ball < 1 || ball > 45) {
				System.out.println("로또 번호는 1에서 45사이여야 합니다.");
				continue;
			}
			
			// 중복처리
			for(int i = 0; i < count; i++) {
				if(ball == users[i]) {
					System.out.println(ball + " 번호는 이미 선택되었습니다.");
					ball = 0;
					break;
				}
			}
			
			// 번호가 0이 아니라면 중복이 아니므로 삽입
			if(ball != 0) {
				users[count++] = ball; 
			}
		}
		
		// 사용자 입력 번호 출력해보기
		System.out.println("User 로또 : " + Arrays.toString(users));
		// 스캐너 닫기
		scan.close();
		
		// 비교
		int[] wins = new int[6];
		// 맞춘 번호 개수
		int winCnt = 0;
		boolean isBonus = false; // 보너스 번호 매치 유무

		for(int i = 0; i < users.length; i++) {
			for(int j = 0; j < lotto.length; j++) {
				if(users[i] == lotto[j]) {
					// 매치되는 로또 번호를 wins 배열에 삽입하고 비교 종료
					wins[winCnt++] = users[i];
					break;
				}
			}
			
			// 보너스 번호 매치 못했을 때 까지만 비교
			if(!isBonus) {
				if(users[i] == bonusNum) {
					isBonus = true;
				}
			}
		}
		
		// 맞은 번호 출력
		for(int i = 0; i < winCnt; i++) {
			System.out.print(wins[i]);
			// 마지막 전까지만 콤마 붙이기 위해
			if(i < winCnt - 1) {
				System.out.print(", ");
			}
		}
		
		// 3항 연산식
		// 조건 ? 참일 때 실행문 : 거짓일 때 실행문
		System.out.println(winCnt == 5 && isBonus ? ", 보너스 번호 : " + bonusNum : "");
		
		// 등수 출력
		if(winCnt == 6) {
			System.out.println("1등 당첨!");
		} else if(winCnt == 5 && isBonus) {
			System.out.println("2등 당첨");
		} else if(winCnt == 5) {
			System.out.println("3등 당첨");
		} else if(winCnt == 4) {
			System.out.println("4등 당첨");
		} else if(winCnt == 3) {
			System.out.println("5등 당첨");
		} else {
			System.out.println("꽝!");
		}
	}
}
