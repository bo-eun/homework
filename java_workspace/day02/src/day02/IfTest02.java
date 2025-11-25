package day02;

public class IfTest02 {

	public static void main(String[] args) {
		
		int score = (int)(Math.random() * 51) + 50;
		
		if(score >= 90) {
			System.out.println("A학점, 점수 : " + score);
		} else if(score >= 80) {
			System.out.println("B학점, 점수 :" + score);
		} else if(score >= 70) {
			System.out.println("C학점, 점수 : " + score);
		} else {
			System.out.println("F학점, 점수 : " + score);
		}
		
		
		
		int randomNum = (int)(Math.random() * 10) + 1;

		if(randomNum % 2 == 0) {
			System.out.println(randomNum + "는 짝수입니다.");
		} else {
			System.out.println(randomNum + "는 홀수입니다.");
		}
		
		

	}

}
