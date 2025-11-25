package kr.study.sorting;

import java.util.Comparator;

// Comparator<비교대상> 남한테 줄 때 사용..?
public class StdAscending implements Comparator<Student2>{

	// 오름차순
	// 현재 나와 다음 거를 받아 비교
	// 버블정렬 아님, sort는 버블정렬 사용하지 않음...
	@Override
	public int compare(Student2 me, Student2 next) {
		return me.getScore() > next.getScore() ? -1 : 1;
	}

}
