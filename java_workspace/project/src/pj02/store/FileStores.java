package pj02.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import pj02.data.Account;

public class FileStores {
	// 계좌 정보가 담긴 파일
	private final String FILE_NAME = "customerAccount.txt";
	
	// 계좌 정보 파일에서 읽어서 배열에 저장
	public List<Account> getAccountList() throws Exception {
		// 계좌객체를 담는 리스트
		List<Account> list = new ArrayList<>();
		
		// 파일이 없을 경우 에러 던지기
		if(!isFileCheck()) {
			throw new FileNotFoundException("파일 없음");
		}
		
		try(FileReader r = new FileReader(FILE_NAME);
				BufferedReader br = new BufferedReader(r);) {
				
			// 읽어온 텍스트를 담을 공간 만듦
			String line = "";
			
			// 파일 다 읽을 때 까지 실행
			while((line = br.readLine()) != null) { // line은 한 줄이 담김
				// 한 줄에 띄어쓰기로 구분하여 배열에 넣기
				String[] arr = line.split(" ");
				
				// customerAccount.txt에 있던 내용을 계좌 객체에 넣음
				Account ac = new Account();
				ac.setMyNumber(arr[0]);
				ac.setBalance(Integer.parseInt(arr[1]));
				
				// 계좌 객체들을 list에 넣기
				list.add(ac);
			}
			
		} catch(Exception e) {
			System.out.println("파일 읽기 실패!");
		}
		
		
		return list;
	}
	
	// 받은 계좌 정보 txt파일에 쓰기
	public void writeAccount(List<Account> list) {
		try(FileWriter w = new FileWriter(FILE_NAME);
				BufferedWriter bw = new BufferedWriter(w);) {
			// customerAccount.txt 파일에 입력받은 계좌 넣기
			for(Account ac : list) {
			// System.out.println(ac);
				bw.write(ac.getMyNumber() + " ");
				bw.write(ac.getBalance() + "\n");
			}
			
			
		} catch(Exception e) {
			System.out.println("파일 쓰기 실패!");
		}
	}
	
	// 수정된 계좌 정보 파일에 업데이트하기
	
	
	
	// 파일 여부 체크용 메서드
	private boolean isFileCheck() {
		return new File(FILE_NAME).exists();
	}
}
