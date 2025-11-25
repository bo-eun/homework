package kr.study.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ListTest01 {
	public static void main(String[] args) {
		// int[] arr = new int [4]; 형태로 배열을 사용하는 것을 배열의 길이가 정해졌을 떄만 가능함
		// 많은 경우 new ArrayList<>();형태로 배열을 사용한다. 배열의 길이가 정해져 있지 않아도 배열을 만들고 수정이 가능
		
		// List 계열은 모두 List 인터페이스를 상속하여 구현한 애들
		// 객체를 선언 시에 부모인 List 타입으로 선언하는 것이 일반적
		// 인터페이스 - 클래스 상속관계는 결합이 낮다.
		List<Integer> list = new ArrayList<>();
		
		Random random = new Random();
		// 등록 add(value) / add(index, value) - 특정 위치에 삽입, 좀 느림;
		for(int i = 0; i < 10; i++) {
			int val = random.nextInt(50) + 1;
			list.add(val);
		}
		
		// 출력
		// ArrayList는 toString이 오버라이딩되어 배열이 바로 문자열로 출력되도록 함
		System.out.println("리스트 : " + list + " 크기 : " + list.size());
		
		// 중간 삽입 - 속도는 느림
		list.add(0, 60);
		System.out.println("리스트 : " + list + " 크기 : " + list.size());
		
		// 값 변경
		list.set(4, 44);
		System.out.println("리스트 : " + list + " 크기 : " + list.size());
		
		// 삭제
		list.remove(5);
		list.remove(6);
		System.out.println("리스트 : " + list + " 크기 : " + list.size());
		
		// for문 써서 출력
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}
		
		
		System.out.println();
		
		// 향상된 for문 - foreach < iterator를 이용해 만들어짐
		// map 은 못씀!
		// for(받을대상 : 루프 대상)
		for(int val : list) {
			System.out.print(val + ", ");
		}
		
		// iterator 라는걸 이용
		/*
		 * iterator는 collection framework 계열 내부에 존재하는 커서 같은 것
		 * 자료구조를 순회 하면서 데이터가 있는지 검색하고 있으면 하나씩 반환해줌
		 * 
		 * */
		Iterator<Integer> iter = list.iterator();
		// 현재 위치 다음에 데이터가 존재하는지 확인
		// 있다면 true 없다면 현재 위치가 마지막이고 false 리턴
		System.out.println();
		while(iter.hasNext()) {
			// 다음 위치로 이동하여 값을 반환
			int val = iter.next();
			System.out.print(val + ", ");
		}
		
		/*
		 * 정리
		 * 배열을 출력할 때
		 * 
		 * 1. ArrayList 사용하여 출력
		 * 2. for문으로 배열 요소 반복하여 출력
		 *    - index 사용 가능
		 * 3. Iterator 사용하여 출력
		 *    - 배열 출력 방법 중 가장 빠름
		 * 
		 * */
	}
}
