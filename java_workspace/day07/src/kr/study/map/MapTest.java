package kr.study.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {
	
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		
		// 등록 - put(key, value);
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		map.put("six", 6);
		
		// 중간 삽입 X - index가 없어서 불가능함
		
		// 키 중복에 의한 치환 가능 - 중복된 키가 들어가면 마지막으로 들어간 값이 들어감
		map.put("one", 10);
		
		// 키가 존재하는지 확인 boolean
		System.out.println("is there two in Key : " + map.containsKey("two"));
		System.out.println("is there ten in Key : " + map.containsKey("ten"));
		
		// 값의 유무 확인 boolean
		System.out.println("is there 11 in value : " + map.containsValue(11));
		System.out.println("is there 3 in value : " + map.containsValue(3));
		
		// 출력
		// key를 이용해서 출력
		// map.keySet() - map의 key를 set에 담아서 주는 메서드
		// map에 등록한 순서대로 값이 담기지 않는다.
		Set<String> keySets = map.keySet();
		for(String key : keySets) {
			System.out.println("key : " + key + ", value : " + map.get(key));
		}
	}

}
