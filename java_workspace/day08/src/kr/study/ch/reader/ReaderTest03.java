package kr.study.ch.reader;

import java.io.BufferedReader;
import java.io.FileReader;


/* .txt파일만 읽기 가능함, 그래서 잘 쓰지 않는다. */
public class ReaderTest03 {

	public static void main(String[] args) {
		// char 기반 FileReader 생성
		try(FileReader reader = new FileReader("example.txt");
			BufferedReader br = new BufferedReader(reader);) {
			
			int read = 0;
			// char를 읽을 때에는 숫자표현으로 읽는 것이 편하다
			while((read = reader.read()) != -1) {
				// 읽은 내용 출력
				System.out.print((char)read);
			}
			
		} catch (Exception e) {
			System.out.println("읽기 오류");
		}
	}
	
}
