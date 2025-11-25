package kr.study.ch.reader;

import java.io.FileReader;


/* .txt파일만 읽기 가능함, 그래서 잘 쓰지 않는다. */
/* char[]배열로 읽기 */
public class ReaderTest02 {

	public static void main(String[] args) {
		// char 기반 FileReader 생성
		try(FileReader reader = new FileReader("example.txt")) {
			
			int read = 0;
			char[] buffer = new char[100];
			// char를 읽을 때에는 숫자표현으로 읽는 것이 편하다
			while((read = reader.read(buffer)) != -1) {
				// 읽은 내용 출력
				System.out.print(String.valueOf(buffer, 0, read));
			}
			
		} catch (Exception e) {
			System.out.println("읽기 오류");
		}
	}
	
}
