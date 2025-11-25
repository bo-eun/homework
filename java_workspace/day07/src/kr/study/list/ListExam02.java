package kr.study.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListExam02 {
	// 가변인자(...) 예
	public static void add(String title, int... scores) {
		int sum = 0;
		for(int s : scores) {
			sum += s; 
		}
		System.out.println(title + " : " + sum);
	}
	
	public static void main(String[] args) {
		// int[] testArr = ListExam02.add("점수 합계", 1,2,3,4,5);
		
		List<Integer> list = new ArrayList<>();
		Random random = new Random();
		
		for(int i = 0; i < 10; i++) {
			list.add(random.nextInt(50) + 1);
		}
		
		System.out.println("리스트 값 : " + list);
		System.out.println("10이 존재하는가? : " + list.contains(10));
		
		// sort
		// 일반 데이터 sort는 쉽다.
		
		// 랜덤으로 섞기
		Collections.shuffle(list);
		System.out.println("shuffle 후 리스트 값 : " + list);
		
		
		// list에서 최대값
		int max = Collections.max(list);
		System.out.println(max);
		
		
		// list에서 최소값
		int min = Collections.min(list);
		System.out.println(min);
		
		
		// 버블정렬할 때 필요할만한 기능
		// Collections.swap(list, 앞, 뒤 위치) 앞 뒤 값 위치를 바꿈
		Collections.swap(list, 0, 1);
		System.out.println(list);
		
		
		// 오름차순
		Collections.sort(list);
		System.out.println("오름차순 정렬 후 리스트 값 : " + list);
		
		
		// 오름차순 후 reverse(); - 단점 : 원본 유지됨 / 먼저 오름차순 정렬을 해야함
		list = list.reversed(); // 변경된 값 원본에 저장
		System.out.println("오름차순 정렬 후 리스트 값 : " + list.reversed());
		
		
		// 진짜 내림차순
		// sort는 오름차순 정렬이라 추가로 Collections.reverseOrder()을 사용하여 반대로 정렬함
		Collections.sort(list, Collections.reverseOrder());
		System.out.println("내림차순 후 리스트 값 : " + list);
		
		
		List<Integer> list1 = List.of(1, 2, 10, 11, 20, 30, 45);
		List<Integer> list2 = List.of(1, 2, 12, 11, 22, 33, 45);
		
		
		// 교집합 만들기
		// arr1.retainAll(arr2) - arr1 과 arr2 비교하여 같은것만 arr1에 저장
		List<Integer> intersection = new ArrayList<>(list1); // list1 복사하여 새 배열 만듦
		intersection.retainAll(list2);
		
		System.out.println("대상 : 1 " + list1);
		System.out.println("대상 : 2 " + list2);
		System.out.println("교집합 : " + intersection);
		
		
	}
}
