package kr.study.sorting;

import java.util.Arrays;

public class StdArraySortMain2 {
	public static void main(String[] args) {
		
		// Array의 정렬 방법 2
		
		// 객체 생성
		Student2 st1 = new Student2("김철수", 90);
		Student2 st2 = new Student2("박민수", 97);
		Student2 st3 = new Student2("이정민", 88);
		
		Student2[] strArray = new Student2[3];
		
		strArray[0] = st1;
		strArray[1] = st2;
		strArray[2] = st3;

		// 오름차순 정렬
		Arrays.sort(strArray, new StdAscending());
		for(Student2 st : strArray) {
			System.out.println("이름 : " + st.getMyName() + ", 점수 : " + st.getScore());
		}
		
	}
}
