package kr.study.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ListLottoExam {
	public static void main(String[] args) {
		
		List<Integer> lotto = new ArrayList<>();
		List<Integer> balls = new ArrayList<>();
		int bonusNumber = 0;
		Random rand = new Random();
		
		// 로또 공 만들기
		for(int i = 1; i <= 45; i++) {
			balls.add(i);
		}
		
		
		int count = 0;
		
		// 보너스 번호까지 7개 뽑기
		while(count < 7) {
			// 리스트 섞기
			Collections.shuffle(balls);	
			
			// balls.remove()에서 추출된 영역의 공간을 지우기 때문에 count만큼 max값을 줄여야 한다.
			// rand.nextInt(45) 0 ~ 44
			int randIndex = rand.nextInt(45 - count);
			
			// 로또와 보너스 번호 만들기
			if(count < 6) {
				lotto.add(count++, balls.get(randIndex));
				balls.remove(randIndex);	
			} else {
				bonusNumber = balls.get(randIndex);
				break;
			}
			
		}
		
		Collections.sort(lotto);
		System.out.println("로또 : " + lotto + ", 보너스 번호 : " + bonusNumber);
		
		
		// 사용자 만들기
		Scanner scan = new Scanner(System.in);
		List<Integer> user = new ArrayList<>();
		count = 0;
		
		while(count < 6) {
			// 문자가 잘못 입력되었을 건 대비해서 예외처리
			try {
				System.out.println((count + 1) + "번째 로또번호 입력 : ");
				int val = scan.nextInt();
				
				// 입력 범위를 넘은 경우는 예외 처리
				if(val < 1 || val > 45) {
					// 예외처리 날리기
					throw new InputMismatchException("1 ~ 45 사이만 입력가능합니다.");
				}
				
				// 선택한 번호가 이미 있는 경우
				if(user.contains(val)) {
					System.out.println(val + "은 이미 존재하는 번호입니다.");
					continue;
				}
				
				// 유저 입력 번호 저장
				user.add(count++, val);
				
			} catch(Exception e) {
				scan.nextLine(); // 입력 오류 시 내용을 지워야함
				System.out.println(e.getMessage() == null ? "입력오류!" : e.getMessage());
			}

		}
		
		Collections.sort(user);
		System.out.println("사용자 로또 : " + user);
		
		// 비교
		List<Integer> winNumbers = new ArrayList<>(user);
		// 서로 동일한 값만 남는다. - 교집합
		winNumbers.retainAll(lotto);
		
		boolean isBonus = user.contains(bonusNumber);
		
		System.out.print("맞은 번호 : " + winNumbers);
		System.out.println((winNumbers.size() == 5) && isBonus ? ", 보너스 번호 : " + bonusNumber : " 보너스 없음");
		
		// 등수
		if(winNumbers.size() == 6) {
			System.out.println("1등");
		} else if(winNumbers.size() == 5 && isBonus) {
			System.out.println("2등");
		} else if(winNumbers.size() == 5) {
			System.out.println("3등");
		} else if(winNumbers.size() == 3) {
			System.out.println("4등");
		} else if(winNumbers.size() == 4) {
			System.out.println("5등");
		} else {
			System.out.println("낙첨입니다...");
		}
		
	}
}
