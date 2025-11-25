package kr.study.array;

import java.util.Arrays;
import java.util.Scanner;

public class DoubleArrayTest02 {
	public static void main(String[] args) {
		
		//로또 2차원배열로 만들기
		int[][] lotto = { 
					{2, 11, 16, 20, 25, 30},
					{1, 6, 17, 22, 24, 33},
					{11, 27, 32, 34, 43, 46},
					{7, 16, 24, 33, 42, 44},
					{2, 17, 19, 24, 33, 45}
				};
		
		// 키보드 입력을 받을 scanner
		Scanner scan = new Scanner(System.in);
		
		// 입력 번호 저장 배열
		int[] user = new int[6];
		
		// 번호 입력
		for(int i = 0; i < user.length; i++) {
			System.out.println((i+1) + "번째 로또번호 입력 : ");
			user[i] = scan.nextInt();
			
			// 버퍼 비우기
			scan.nextLine();
			
			// 중복 제거 체크
			// 이전 입력 값이랑 현재 입력 값이랑 중복 체크
			// 중복일 경우 현재 값 다시 입력 받기
			for(int j = 0; j < i; j++) {
				if(user[i] == user[j]) {
					System.out.println(user[i] + "번호는 이미 존재함.");
					i--; // i for문에서 증가할테니 여기서 빼면 제자리
					break; // 찾았으니 멈추기
				}
			}
		}
		
		scan.close();
		
		
		// 당첨번호를 저장할 배열
		int[][] winNumbers = new int[5][6];
		
		// 한 줄 당 맞은 숫자 개수 저장
		int[] winCnt = new int[5];
		
		int cnt = 0; // 한줄에서 일치하는 로또번호 개수
		
		for(int i = 0; i < lotto.length; i++) {
			cnt = 0;
			for(int j = 0; j < lotto[i].length; j++) {
				// 사용자가 선택한 번호와 로또 번호를 비교
				if(user[j] == lotto[i][j]) {
					winNumbers[i][cnt++] = lotto[i][j];
				}
			}
			
			winCnt[i] = cnt;
		}
		
		
		System.out.println("----- 로또 결과 ------");
		// 출력
		for(int i = 0; i < winNumbers.length; i++) {
			System.out.println((i + 1) + "번째 결과 : ");
			
			for(int j = 0; j < winCnt[i]; j++) {
				System.out.print(winNumbers[i][j] + ",");
			}
			
			if(winCnt[i] == 6) {
				System.out.println("\t1등");
			} else if(winCnt[i] == 5) {
				System.out.println("\t2등");
			} else if(winCnt[i] == 4) {
				System.out.println("\t3등");
			} else if(winCnt[i] == 3) {
				System.out.println("\t4등");
			} else if(winCnt[i] == 2) {
				System.out.println("\t5등");
			} else if(winCnt[i] == 1) {
				System.out.println("\t꽝");
			} else {
				System.out.println("낙첨...");
			}
		}
		
	}
}
