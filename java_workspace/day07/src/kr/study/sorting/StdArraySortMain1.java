package kr.study.sorting;

import java.util.Arrays;

public class StdArraySortMain1 {
	public static void main(String[] args) {
		
		// Array의 정렬 방법 1
		
		// 객체 생성
		Student st1 = new Student("김철수", 90);
		Student st2 = new Student("박민수", 97);
		Student st3 = new Student("이정민", 88);
		
		Student[] strArray = new Student[3];
		
		strArray[0] = st1;
		strArray[1] = st2;
		strArray[2] = st3;

		// 내림차순 정렬
		Arrays.sort(strArray);
		for(Student st : strArray) {
			System.out.println("이름 : " + st.getMyName() + ", 점수 : " + st.getScore());
		}
		
	}
}
