package day02;

import java.util.Scanner;

public class SwitchTest {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("점수 입력 : ");
		int score = scan.nextInt();
		
		switch(score / 10) {
			
		case 10, 9 : 
			System.out.println("A학점, 점수 : " + score);
			break;
		case 8 : 
			System.out.println("B학점, 점수 : " + score);
			break;
		case 7 : 
			System.out.println("C학점, 점수 : " + score);
			break;
		case 6 : 
			System.out.println("D학점, 점수 : " + score);
			break;
		default : 
			System.out.println("F학점, 점수 : " + score);
			
		}
		
		scan.close();
	}

}
