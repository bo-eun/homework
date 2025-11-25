package kr.study.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class InputTest05 {
	public static void main(String[] args) {
		File file = new File("example.txt");
		/*
		 * jdk 1.7 이후 파일 읽기 방식
		 *  try -with - resources
		 *  close 가 자동으로 된다.
		 * 	close() 가 있는 io를 쓸 때 아래와 같은 양식으로 작성하면 편하다!
		 * */
		
		// 배열을 쓰면 한글을 깨지지 않고 담을 수 있다...
		// 배열 길이를 넉넉히 만듦...
		byte[] buffer = new byte[50];
		
		// 보조 스트림 사용하기!
		// BufferedInputStream() 보조스트림에 매개변수로 input스트림 담아 사용
		// 속도가 매우 빠르다. 하지만 한글이 깨진다... 그래서 이미지를 읽을 때 사용한다!
		
		// () 안에 여러개의 선언을 쓸 경우 세미콜론(;) 으로 구분한다
		try(FileInputStream in = new FileInputStream(file);
			BufferedInputStream bf = new BufferedInputStream(in)	) {
			
			int read = 0; // 읽어올 값 저장
			
			// 읽어올 값이 없을 때 까지 반복
			while((read = bf.read(buffer)) != -1) {
				System.out.write(buffer, 0, read);
			}
			
		} catch(Exception e) { // IOException 에러 클래스가 있지만 IO관련 에러는 Exception으로 퉁치는게 좋다...
			System.out.println("에러");
			
		}
	}
}
