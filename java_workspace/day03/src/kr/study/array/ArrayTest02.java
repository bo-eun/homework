package kr.study.array;

import java.util.Arrays;

public class ArrayTest02 {
	public static void main(String[] args) {
		
		int max = Integer.MIN_VALUE; // int 형 값의 최소값
		int min = Integer.MAX_VALUE; // int 형 값의 최대값
		
		int[] arr = new int[10];
		
		// 랜덤함수를 이용하여 배열 값 넣기
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 100) + 1;
			
			if(max < arr[i]) {
				max = arr[i];
			};
			
			if(min > arr[i]) {
				min = arr[i];
			}
			
			System.out.println(max + ", " + min);
		}
		
		// Arrays 는 배열의 도우미 클래스 여러 기능들이 있다.
		System.out.println(Arrays.toString(arr));
		System.out.println("최소 : " + min + ", 최대 : " + max);
		
	}
}
