package kr.study.io;

import java.io.File;
import java.io.FileInputStream;

public class InputTest02 {
	public static void main(String[] args) {
		/*
		 * jdk 1.7 이전 파일 읽기 방식
		 * 
		 * */
		
		File file = new File("example.txt");
		
		// 파일 읽기
		FileInputStream in = null;

		try {
			// 코드
			in = new FileInputStream(file);
			
		} catch(Exception e) {
			
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (Exception e) {
				// close 오류 시 처리
			}
		}
		
	}
}
