package kr.study.read;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/* read할 때 보조스트림 사용한 경우 
* 보조스트림은 buffer에 값을 저장한 상태에서 read요청이 들어올 때 buffer에서 값을 꺼내 준다. cpu에 부담을 줄여준다.
* 일반스트림은 read요청이 들어오면 cpu에서 값을 요청해 꺼내주기 때문에 보조스트림을 사용할 때 보다 더 느리다. 
* 보조스트림 + 배열 요청은 buffer에 값을 배열로 저장해서 꺼내주기 때문에 보조스트림만 썼을 때 보다 더 빠르다. cpu + 자바(메모리)공간의 부담을 덜어준다.
* */
public class ImageReadTest04 {
	public static void main(String[] args) {
		File file = new File("testImg.jpg");
		/*
		 * jdk 1.7 이후 파일 읽기 방식
		 *  try -with - resources
		 *  close 가 자동으로 된다.
		 * 	close() 가 있는 io를 쓸 때 아래와 같은 양식으로 작성하면 편하다!
		 * */
		
		// 파일 읽기
		// () 안에 여러개의 선언을 쓸 경우 세미콜론(;) 으로 구분한다
		try(FileInputStream in = new FileInputStream(file);
			BufferedInputStream bf = new BufferedInputStream(in);	) {
			// 코드
			int read = 0; // 읽어올 값 저장
			// 읽어올 값이 없을 때 까지 반복
			
			long start = System.currentTimeMillis(); // 현재시간을 초단위(ms)로 나타냄
			
			while((read = bf.read()) != -1) {
				
			}
			
			long end = System.currentTimeMillis();
			
			// 이미지 파일 읽어오는 시간 비교
			System.out.println(end - start);
			
		} catch(Exception e) { // IOException 에러 클래스가 있지만 IO관련 에러는 Exception으로 퉁치는게 좋다...
			System.out.println("에러");
		}
	}
}
