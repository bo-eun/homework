package kr.study.ch.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class FileWriterTest02 {
	public static void main(String[] args) {
		
		// 이어쓰기 기능 있음!
		try(FileWriter fw = new FileWriter("writer.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				Scanner scan = new Scanner(System.in);) {
			
			String str = "";
			System.out.println("키보드 입력(end 입력 시 종료) : ");
			
			// end 입력 시 종료
			while(!(str = scan.nextLine()).equals("end")) {
				// 입력 내용 쓰고 줄바꾸기
				// 한문장씩 입력하기 때문에 배열이 아닌 String값 사용
				bw.write(str + "\n");
				
			}
			
			System.out.println("파일 쓰기 종료");
			
		} catch(Exception e) {
			System.out.println("쓰기 에러!");
		}
		
	}
}
