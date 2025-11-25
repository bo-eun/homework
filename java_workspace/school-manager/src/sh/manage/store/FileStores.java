package sh.manage.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import sh.manage.data.Student;

public class FileStores {
	// 학생 정보가 담긴 파일
	private final String FILE_NAME = "student.txt";
	
	// txt파일을 읽은 후 그 정보를 학생 객체에 저장하여 학생 리스트를 리턴하는 함수
	public List<Student> getAllList() throws Exception {
		// 학생객체를 담을 리스트 만들기
		List<Student> list = new ArrayList<>();
		
		// 파일 객체를 만든다.
		if(!isFileCheck()) {
			throw new FileNotFoundException("파일 없음");
		}
		
		// 학생 정보 읽어오기 Reader 사용
		try(FileReader r = new FileReader(FILE_NAME);
				BufferedReader br = new BufferedReader(r);) {
			
			// 읽어온 텍스트를 담을 공간 만듦
			String line = "";
			
			// 파일 다 읽을 때 까지 실행
			while((line = br.readLine()) != null) { // line은 한 줄이 담김
				// 한 줄에 띄어쓰기로 구분하여 넣어져 있을 것. 그래서 띄어쓰기로 자른다.
				// 순서는 이름 국어 영어 수학 총점 평균 순으로 들어있다고 가정하자.
				String[] arr = line.split(" ");
				
				// student.txt에 있던 내용을 학생 객체에 넣음
				Student st = new Student();
				st.setMyName(arr[0]);
				// String -> 정수타입 int로 변환
				st.setKor(Integer.parseInt(arr[1]));
				st.setEng(Integer.parseInt(arr[2]));
				st.setMath(Integer.parseInt(arr[3]));
				
				// 학생 객체들을 list에 넣기
				list.add(st);
			}
			
		} catch(Exception e) {
			System.out.println("파일 읽기 실패!");
		}
		
		return list;
	} 
	
	
	public void writeStudent(List<Student> list) {
		
		if(list == null) {
			// 이 오류는 어디서 처리하지...??
			throw new NullPointerException("학생 리스트가 존재하지 않음");
		}
		
		try(FileWriter w = new FileWriter(FILE_NAME);
				BufferedWriter bw = new BufferedWriter(w);) {
			
			for(Student st : list) {
				bw.write(st.getMyName() + " ");
				bw.write(st.getKor() + " ");
				bw.write(st.getEng() + " ");
				bw.write(st.getMath() + "\n");
			}
			
			System.out.println("학생정보 저장이 완료되었습니다...");
			
		} catch(Exception e) {
			System.out.println("파일 쓰기 실패!");
		}
		
	}
	
	
	/*
	 * 파일이 존재하는지 확인하는 메서드
	 * 해당 메서드는 외부노출이 필요없기 때문에(클래스에서만 쓰기 때문에) private
	 * */
	private boolean isFileCheck() {
		return new File(FILE_NAME).exists();
	}
	
}
