package kr.study.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExam {
	public static void main(String[] args) {
		
		// 추가, 수정, 삭제 불가, final로 처리됨
		// List<Integer> list = List.of(1, 10, 11, 23, 14);
		
		// 추가,삭제 안됨(치환은 가능) > Arrays.asList()로 만들면 배열 타입으로 만들어져서 크기 고정
		// List<Integer> list = Arrays.asList(1, 2, 4, 10, 11, 20);
		// ist.add(1);
		
		// 가능한 형태
		// Arrays.asList(데이터 나열) > 데이터 나열 != 배열, 데이터 나열 형태는 1,2,3,4... 이고 배열은 {1,2,3,4}이다
		// Arrays.asList(데이터 나열) = ArrayList
		// new ArrayList<>(Arrays.asList(...)) > ArrayList를 새 ArrayList로 다시 만듦(복사)
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 4, 10, 11, 20));
		
		System.out.println(list);
		
	}
}
