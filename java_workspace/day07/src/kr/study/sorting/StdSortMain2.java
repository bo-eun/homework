package kr.study.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StdSortMain2 {
	public static void main(String[] args) {
		
		// ArrayList의 정렬 방법 2
		
		// 객체 생성
		Student2 st1 = new Student2("김철수", 90);
		Student2 st2 = new Student2("박민수", 97);
		Student2 st3 = new Student2("이정민", 88);
		
		// ArrayList 생성
		List<Student2> list = new ArrayList<>();
		
		// ArrayList에 객체 넣기
		list.add(st1);
		list.add(st2);
		list.add(st3);

		Collections.sort(list, new StdAscending());
		
		for(Student2 st : list) {
			System.out.println("이름 : " + st.getMyName() + ", 점수 : " + st.getScore());
		}
		
	}
}

