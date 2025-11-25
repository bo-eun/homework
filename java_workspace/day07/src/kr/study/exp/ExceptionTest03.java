package kr.study.exp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest03 {
	public static void main(String[] args) {
		
		// 스캐너 객체 선언
		Scanner scan = new Scanner(System.in);
		
		try {
			System.out.println("값 입력 : ");
			int number = scan.nextInt();
			
			System.out.println("출력 : " + number);
			
		} catch(InputMismatchException e) {
			// 예외 발생
			System.out.println("키보드 입력 오류");
		} finally {
			// 특징 : 예외 발생 여부와 상관없이 마지막에 무조건 실행됨
			// finally 영역에 안쓰고 밖에 코드를 써도 똑같이 마지막에 실행됨
			// 그렇지만 try catch문 사용 시 문맥상...보기 편하게...finally문 사용하는 것 같음...
			// 스캐너 닫기
			if(scan != null) {
				scan.close();
			}
			System.out.println("finally 실행");
		}
		
		System.out.println("종료");
		
	}
}
