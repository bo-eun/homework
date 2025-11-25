package kr.study.io;

import java.io.File;

public class inputTest01 {
	public static void main(String[] args) {
		
		// File 객체에 대해 배워봅시다!
		// 실제 File을 객체화 해 클래스로 다룰 수 있게 함
		/*
		 * 절대경로 / 상대경로
		 * 절대경로 : c:\test\......> 물리적 경로
		 * 상대경로 : 내 위치 기준 경로
		 * 			/day08/kr/study/io/InputTest01.java
		 * 			example.txt - root 바로 밑에 있는 경우 파일명만 써도 됨
		 * 
		 * */
		// example.txt 파일을 읽어 정보를 객체화한 것
		File file = new File("example.txt");
		
		System.out.println("파일 이름 : " + file.getName());
		System.out.println("파일 크기(byte) : " + file.length() + "byte");
		System.out.println("파일 경로(절대경로) : " + file.getAbsolutePath());
		System.out.println("파일 경로(상대경로) : " + file.getParent());
		System.out.println("파일 여부 : " + file.isFile());
		System.out.println("폴더 여부 : " + file.isDirectory());
		System.out.println("쓰기권한 : " + file.canWrite());
		System.out.println("읽기권한 : " + file.canRead());
		System.out.println("존재하는가 : " + file.exists()); // 가장 많이 씀...
		
		
	}
}
