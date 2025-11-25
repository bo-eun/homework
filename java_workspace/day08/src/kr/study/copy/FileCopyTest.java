package kr.study.copy;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/* read할 때 배열과 보조스트림 같이 사용한 경우 */
/* 파일 카피 */
public class FileCopyTest {
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
			
			int available = bf.available(); // 기다리지 않고 읽을 수 있는 크기
			
			// 블럭킹 당하지 않을 크기가 1kb(1024)보다 크면 그걸쓰고 아니면 1kb로 배열을 만든다.
			int size = available > 1024 ? available : 1024; 
			byte[] buffer = new byte[size];
			
			// 코드
			int read = 0; // 읽어올 값 저장
			
			long start = System.currentTimeMillis(); // 현재시간을 초단위(ms)로 나타냄
			
			while((read = bf.read(buffer)) != -1) {
				
			}
			
			long end = System.currentTimeMillis();
			
			// 이미지 파일 읽어오는 시간 비교
			System.out.println(end - start);
			
		} catch(Exception e) { // IOException 에러 클래스가 있지만 IO관련 에러는 Exception으로 퉁치는게 좋다...
			System.out.println("에러");
		}
	}
}
