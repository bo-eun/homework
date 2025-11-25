package day02;

import java.util.Scanner;

public class ScannerTest01 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// scan.nextInt() 숫자 입력을 받는 함수
		// 함수가 실행되면 입력 숫자를 받고 엔터를 누르면 입력이 종료됨
		System.out.println("숫자 입력 : ");
		int num01 = scan.nextInt();
		System.out.println(num01);
		
		// scan.next() 문자 입력을 받는 함수
		// 함수가 실행되면 입력 문자를 받고 엔터를 누르면 입력이 종료됨
		// 띄어쓰기와 엔터를 구분하지 못하며 띄어쓰기가 있을 경우 띄어쓰기 전 문자까지만 출력됨
		// 나머지 문자는 버퍼에 저장됨...
		System.out.println("문자 입력 : ");
		String str01 = scan.next();
		System.out.println(str01);
		
		
		// scan.nextLine() 문자 입력을 받는 함수
		// scan.next()와 달리 띄어쓰기를 구분하여 띄어쓰기 포함한 문자 입력을 받을 수 있음
		// 함수 실행 시 이전에 버퍼에 저장된 키보드 입력 정보가 남아 있을 경우 해당 정보를 출력함
		// 남아있는 키보드 입력 정보가 없을 경우 엔터 입력 이전의 문자 출력
		/* 만약 남아있는 키보드 입력 정보가 있을 경우를 대비하여 
		 * scan.nextLine()을 한번 실행해 이전 키보드 입력 정보를 지운다.
		 */
		
		// 남은 내용 버리기 용도
		scan.nextLine();
		System.out.println("문자 입력2 : ");
		String str02 = scan.nextLine();
		System.out.println(str02);
		
		// 스캐너 닫기
		scan.close();
	}

}
