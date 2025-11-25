package kr.study.sorting;

import java.util.Arrays;
import java.util.Collections;

public class ArraySortTest {
	public static void main(String[] args) {
		
		// 만약 배열 Arrays를 사용해서 sort 하려면 wrapper 클래스 타입을 쓰는게 좋다.
		// 이유는 내림차순 때문
		// Arrays는 Collections를 보고 만들어서 오름차순은 괜찮은데 내림차순은 클래스타입 데이터를 사용함
		Integer[] arr = {10, 1, 9, 8, 6, 3, 2, 7, 5, 4};
		
		// Arrays를 이용한 오름차순
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		
		// 내림차순
		Arrays.sort(arr, Collections.reverseOrder());
		// 내림차순 ...wrapper 클래스로 선언해야함
		System.out.println(Arrays.toString(arr));
		
	}
}
