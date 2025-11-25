package kr.study.io;

import java.io.File;
import java.io.FileInputStream;

public class InputTest04 {
	public static void main(String[] args) {
		File file = new File("example.txt");
		/*
		 * jdk 1.7 이후 파일 읽기 방식
		 *  try -with - resources
		 *  close 가 자동으로 된다.
		 * 	close() 가 있는 io를 쓸 때 아래와 같은 양식으로 작성하면 편하다!
		 * */
		
		byte[] buffer = new byte[100];
		
		// 파일 읽기
		// () 안에 여러개의 선언을 쓸 경우 세미콜론(;) 으로 구분한다
		try(FileInputStream in = new FileInputStream(file);) {
			// 코드
			int read = 0; // 읽어올 값 저장
			// 읽어올 값이 없을 때 까지 반복
			
			// read 메서드가 buffer 배열에다가 읽은 글자를 담는다.
			// read 메서드는 buffer에 담은 개수를 반환해준다.
			// read 변수는 메서드가 읽은 문자의 개수를 가진다.
			while((read = in.read(buffer)) != -1) {
				System.out.write(buffer, 0, read);
			}
			
		} catch(Exception e) { // IOException 에러 클래스가 있지만 IO관련 에러는 Exception으로 퉁치는게 좋다...
			// 코드
			e.printStackTrace();
			System.out.println("에러");
		}
	}
}
