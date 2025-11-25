package kr.study.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StdSortMain1 {
	public static void main(String[] args) {
		
		// ArrayList의 정렬 방법 1
		
		// 객체 생성
		Student st1 = new Student("김철수", 90);
		Student st2 = new Student("박민수", 97);
		Student st3 = new Student("이정민", 88);
		
		// ArrayList 생성
		List<Student> list = new ArrayList<>();
		
		// ArrayList에 객체 넣기
		list.add(st1);
		list.add(st2);
		list.add(st3);
		
		// 리스트 정렬
		// Collections.sort() 시 compareTo메서드가 자동으로 실행됨
		// 오름차순 or 내림차순 가능. 동시에 둘 다 사용 불가능함
		Collections.sort(list);
		
		for(Student st : list) {
			System.out.println("이름 : " + st.getMyName() + ", 점수 : " + st.getScore());
		}
		
	}
}
