package kr.study.stream.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MapFunctionTest01 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5,6));
		
		// 리스트 각각의 값에 2씩 더해서 저장하기
		
		// stream api 원본은 건들지 않는다.
		// 데이터의 흐름을 보여주는 api기술이라 원본은 유지된다.
		// 그래서 변형시킨 list를 다른 변수를 만들어서 저장해야 한다.
		
		// Collections.sort(list, "comparator를 상속받아서 compare를 구현한 자식 또는 compare가 구현된 comparator 익명클래스");
		// Collections.sort(list, new Comparator<Integer>() {compare ....});
		// sort(list, 콜백함수)
		Collections.sort(list, (o1, o2) -> (o1.intValue() > o2.intValue() ? 1 : -1) );
		System.out.println(list);
		
		// map 함수 -> for문과 유사
		// 반복을 돌면서 매개변수인 함수형 인터페이스에게 값을 하나씩 전달한다.
		// map이 매개변수로 사용한느 함수형 인터페이스는 
		// 매개변수의 타입과 리턴 타입을 다르게 설정할 수 있다.
		// map 함수를 사용하여 결과를 받는 left value의 데이터 타입에 따라
		// map 함수의 return 타입도 결정된다.
		List<Integer> newList = list.stream()
				.map(val -> val * 2)
				.collect(Collectors.toList());
		System.out.println(newList);
		
		List<String> list2 = new ArrayList<>(List.of("1","2","3","4","5","6"));

		List<Integer> intList = new ArrayList<>();
		
		// 문자 타입의 숫자 데이터를 가지는 리스트의 값들을 int 타입으로 변형하여, int타입의 리스트에 저장하라
		for(String val : list2) {
			intList.add(Integer.parseInt(val));
		}
		
		// stream api의 map 함수를 이용해보자
		intList = list2.stream()
				.map(str -> Integer.parseInt(str))
				.collect(Collectors.toList()); // 리스트로 만듦
		
		// map 함수가 사용하는 람다식이
		// 항상 동일한 메서드를 호출할 경우
		// 메서드 참조문법을 사용 가능하다.
		// 호출되는 메서드의 형태(매서드가 지닌 매개변수)가 항상 동일할 때 가능
		intList = list2.stream()
				.map(Integer::parseInt) // .map(str -> Integer.parseInt(str))
				.collect(Collectors.toList()); // 리스트로 만듦
		
		
	}
}
