package kr.study.io;

import java.io.File;
import java.io.FileInputStream;

public class InputTest03 {
	public static void main(String[] args) {
		File file = new File("example.txt");
		/*
		 * jdk 1.7 이후 파일 읽기 방식
		 *  try -with - resources
		 *  close 가 자동으로 된다.
		 * 	close() 가 있는 io를 쓸 때 아래와 같은 양식으로 작성하면 편하다!
		 * */
		// 파일 읽기
		// () 안에 여러개의 선언을 쓸 경우 세미콜론(;) 으로 구분한다
		try(FileInputStream in = new FileInputStream(file);) {
			// 코드
			int read = 0; // 읽어올 값 저장
			// 읽어올 값이 없을 때 까지 반복
			while(read != -1) {
				read = in.read();
				// print해도 읽어온 문자에 엔터키가 입력되어 있으면 줄바꿈이 됨
				// 내용을 읽어오면 한글이 깨진다..
				System.out.print((char)read);
			}
			
		} catch(Exception e) { // IOException 에러 클래스가 있지만 IO관련 에러는 Exception으로 퉁치는게 좋다...
			System.out.println("에러");
		}
	}
}
