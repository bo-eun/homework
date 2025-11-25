package kr.study.array;

import java.util.Arrays;

public class ArraySort01 {
	public static void main(String[] args) {
		// 배열 정렬하기
		// 버블정렬
		int[] arr= {5,1,9,10,4,3,8,6,7,2};
		
		// 임시값 저장할 변수
		int temp;
		
		/*
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < (arr.length - i) - 1; j++) {
				if(arr[j] > arr[j + 1]) {
					// 앞 뒤 값 비교하여 큰 값 바로 뒤로 보내기
					temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		*/
		// 위의 코드 정리
		for(int i = arr.length; i > 0; i--) {
			for(int j = 0; j < i - 1; j++) {
				if(arr[j] > arr[j + 1]) {
					// 앞 뒤 값 비교하여 큰 값 바로 뒤로 보내기
					temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}	
		
		
		System.out.println(Arrays.toString(arr));
	}
}
