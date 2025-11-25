package kr.study.array;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayTest03 {
	public static void main(String[] args) {
		/*
		 *  스캐너를 이용하여 값을 5개 입력하고
		 *  해당 값들과 그 값의 합을 출력하라
		 *  ex) 3, 10, 32, 23, 44;
		 *  합 : 112
		 *  
		 * */
		
		Scanner scan = new Scanner(System.in);
		
		// 배열 선언
		int[] arr = new int[5];
		// 배열 합을 저장할 변수 초기화
		int sum = 0;
		
		// loop 돌리면서 배열에 값 입력 + 합 구하기
		for(int i = 0; i < arr.length; i++) {
			// 배열에 넣을 값 요청
			System.out.print((i + 1) + "번째 숫자를 입력해주세요.");
			arr[i] = scan.nextInt();
			// 배열 값 더하기
			sum += arr[i];
		}
		
		System.out.println("배열 : " + Arrays.toString(arr));
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}
		
		System.out.print("합 : " + sum);

		scan.close();
	}
}
