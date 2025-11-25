package apple;

import java.util.Arrays;
import java.util.Comparator;

public class Solution2 {
	public static int solution(int k, int m, int[] score) {
		// score 내림차순 정렬
		Integer[] apples = Arrays.stream(score) // int[] 를 stream으로 변환 >> IntStream
				.boxed() // 기본형 스트림을 참조형 스트림으로 변환할 때 사용, int를 Integer로 변환 >> Stream<Integer>
				.sorted(Comparator.reverseOrder()) // 내림차순 정렬, Comparator를 쓰기 위해 int를 Integer로 변환
				.toArray(Integer[]::new); // Integer[]::new  >> (size -> new Integer[size]), 콜백함수의 인자는 stream 요소의 개수 
		
		int answer = 0;
		
		for(int i = m - 1; i < apples.length; i += m) {
			answer += apples[i] * m;
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		System.out.println(solution(3, 4, new int[]{1, 2, 3, 1, 2, 3, 1}));
		System.out.println(solution(4, 3, new int[]{4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2}));
	}
}
