package beverageVendingMachine.store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beverageVendingMachine.data.Beverage;

public class FileStores {
	private final String FILE_NAME = "src/beverageVendingMachine/beverageMachine.txt"; 
	
	private List<Beverage> beverageList = new ArrayList<>();
	
	// 파일 읽어서 음료 정보가 담긴 Beverage객체를 beverageList 리스트에 담아 리턴
	public List<Beverage> getAllList() throws Exception {
		// 음료 객체를 담는 리스트
		
		// 파일이 없을 경우 에러 던지기
		if(!isFileCheck()) {
			throw new FileNotFoundException("파일 없음");
		}
		
		
		try(FileReader r = new FileReader(FILE_NAME);
			BufferedReader br = new BufferedReader(r);) {
			
			boolean isEmpty = true; //읽는 파일이 비었는지 확인하는 변수
			
			String line = "";
			
			// 파일 한줄 한줄 읽어 한 줄에 있는 정보 음료 리스트에 넣기
			while((line = br.readLine()) != null) {
				isEmpty = false; // 파일 한줄이 있으면 false
				
				String[] arr = line.split(" "); // "(이름) (가격) (수량) (해당 상품 총매출)"
				
				// 음료 객체 만들기
				Beverage beverage = new Beverage(
						arr[0], 
						Integer.parseInt(arr[1]), 
						Integer.parseInt(arr[2])
						);

				// 음료 객체 리스트에 넣기
				beverageList.add(beverage);
			}
			
			// 파일이 비어 있으면 파일에 기본 내용 추가하는 메서드 실행
			if(isEmpty) {
				writeInitList();
			}
			
		} catch(Exception e) {
			System.out.println("파일 읽기 실패!");
		}
		
		
		return beverageList;
	};
	
	// Beverage 객체의 내용을 파일에 쓰기
	public void writeBeverageInfo() {
		try(FileWriter w = new FileWriter(FILE_NAME);
				BufferedWriter bw = new BufferedWriter(w);) {
			
			 // "(이름) (가격) (수량) (총매출)" 형태로 text 넣기
			for(Beverage b : beverageList) {
				
				bw.write(b.getName() + " ");
				bw.write(b.getPrice() + " ");
				bw.write(b.getCount() + " ");
				bw.write(Beverage.totalAmount + "\n");
			}
			
		} catch(Exception e) {
			System.out.println("파일 쓰기 실패!");
		}
	}
	
	// 파일에 내용이 없을 경우 기본 내용 넣기
	private void writeInitList() {
		Beverage bg1 = new Beverage("콜라", 1500, 30);
		Beverage bg2 = new Beverage("사이다", 1300, 24);
		Beverage bg3 = new Beverage("웰치스포도", 1100, 35);
		Beverage bg4 = new Beverage("핫식스", 1600, 18);
		Beverage bg5 = new Beverage("헛개수", 1800, 22);

		beverageList = new ArrayList<>(Arrays.asList(bg1, bg2, bg3, bg4, bg5));
		// 초기화한 리스트 파일에 쓰기
		writeBeverageInfo();
	}
	
	private boolean isFileCheck() {
		return new File(FILE_NAME).exists();
	}
}
