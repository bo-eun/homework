package kr.study.ch.reader;

import java.io.BufferedReader;
import java.io.FileReader;


/* .txt파일만 읽기 가능함, 그래서 잘 쓰지 않는다. */
/* buffered와 배열 함께 사용 */
public class ReaderTest04 {

	public static void main(String[] args) {
		// char 기반 FileReader 생성
		try(FileReader reader = new FileReader("example.txt");
			BufferedReader br = new BufferedReader(reader);) {
			
			char[] buffer = new char[1024];
			
			int read = 0;
			// char를 읽을 때에는 숫자표현으로 읽는 것이 편하다
			while((read = br.read(buffer)) != -1) {
				// 읽은 내용 출력
				System.out.print(String.valueOf(buffer, 0, read));
			}
			
		} catch (Exception e) {
			System.out.println("읽기 오류");
		}
	}
	
}
