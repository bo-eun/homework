package kr.study.ch.reader;

import java.io.BufferedReader;
import java.io.FileReader;


/* .txt파일만 읽기 가능함, 그래서 잘 쓰지 않는다. */
public class ReaderTest05 {

	public static void main(String[] args) {
		// char 기반 FileReader 생성
		try(FileReader reader = new FileReader("example.txt");
			BufferedReader br = new BufferedReader(reader);) {
			
			String read = "";
			// char를 읽을 때에는 배열보다 숫자표현으로 읽는 것이 편하다
			while((read = br.readLine()) != null) {
				// 라인단위로 읽는데 엔터 전까지만 가져옴 그래서 즐바꿈이 필요
				System.out.println(read);
			}
			
		} catch (Exception e) {
			System.out.println("읽기 오류");
		}
	}
	
}
