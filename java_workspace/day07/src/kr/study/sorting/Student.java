package kr.study.sorting;

import java.util.Collections;

public class Student implements Comparable<Student>{

	private String myName;
	private int score;
	
	public Student(String myName, int score) {
		this.setMyName(myName);
		this.setScore(score);
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	// 내 뒤에 있는 객체를 매개변수로 받는다.
	// Collections.sort(List<T> list)메서드는 리스트에 들어 있는 객체들이 Comparable 인터페이스를 구현하고 있어야 작동
	@Override
	public int compareTo(Student o) {
		return this.getScore() > o.getScore() ? -1 : 1;
	}
}
