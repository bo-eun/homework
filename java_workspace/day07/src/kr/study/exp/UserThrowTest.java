package kr.study.exp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserThrowTest {
	public static void main(String[] args) {
		// 스캐너 생성
		Scanner scan = new Scanner(System.in);
		// 데이터 저장할 리스트
		List<Integer> list = new ArrayList<>();
		int count = 0;
		// 숫자 입력 5번 될 때 까지 반복
		while(count < 5) {
			try {
				System.out.println((count + 1) + " 번째 숫자 입력(1 ~ 30)");
				int val = scan.nextInt();	
				// 1보다 작거나 30을 넘으면 예외처리
				if(val < 1 || val > 30) {
					// 임의의 예외처리
					throw new InputMismatchException("1 ~ 30 사이만 입력하세요");
				}
				// count 증가하면서 리스트에 값을 넣기
				// add(index, value); index자리에 value 넣기
				list.add(count++, val);
				
			} catch(Exception e) {
				scan.nextLine(); // 20번째 줄에서 받은 오류난 값을 지운다. 버퍼 비우기
				System.out.println(e.getMessage() != null ? e.getMessage() : "키보드 입력 오류!");
			}
			
		}
		
		scan.close();
		System.out.println(list);
		
	}
}
