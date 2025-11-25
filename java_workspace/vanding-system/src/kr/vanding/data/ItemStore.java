package kr.vanding.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ItemStore {
	private final String ITEM_FILE_NAME = "itemList.txt";
	private List<Item> itemList = new ArrayList<>();
	private int totalBenefit = 0;
	
	
	public ItemStore() {
		itemList = new ArrayList<>();
	}
	
	public List<Item> getItemList() {
		File f = this.getFile();
		
		if(!f.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			return itemList;
		};
			
		try(FileReader r = new FileReader(f);
				BufferedReader bf = new BufferedReader(r)) {
					
			String readLine = "";
			while( (readLine = bf.readLine()) != null ) {
				String[] attr = readLine.split(",");
				
				if(attr.length == 4) {
					// 이름, 개수, 가격, 수익
					Item item = new Item();
					item.setName(attr[0]);
					item.setQuantity(Integer.parseInt(attr[1]));
					item.setPrice(Integer.parseInt(attr[2]));
					item.setBenefit(Integer.parseInt(attr[3]));
					itemList.add(item);
				} else {
					totalBenefit = Integer.parseInt(attr[0]);
				}

			}
			
			System.out.println("===================데이터 로드 완료===================");
		} catch(Exception e) {
			System.out.println(e.getMessage() != null ? e.getMessage() : "File Load Error");
			e.printStackTrace();
		
		}
		
		return itemList;
	}
	
	// 정수는 업데이트가 필요
	public void addTotalBenefit(int money) {
		this.totalBenefit += money;
	}
	
	// 총액 따로 반환하도록 메서드 작성
	public int getTotalBenefit() {
		return this.totalBenefit;
	}
	
	
	public void writeItemList() {
		try(FileWriter w = new FileWriter(ITEM_FILE_NAME);
				BufferedWriter bw = new BufferedWriter(w);) {
			
			for(Item item : itemList) {
				bw.write(item.getName()+",");
				bw.write(item.getQuantity()+",");
				bw.write(item.getPrice()+",");
				bw.write(item.getBenefit()+"\n");
			}
			
			// 숫자를 text문서에 넣으면 문서를 열 때 숫지값이 아스키표 때문에 문자로 치환되는 경우가 있어
			// text파일을 만들 때 인코딩을 utf-8로 변경해주거나, 숫자를 문자타입으로 변경하여 삽입하는게 좋다.
			bw.write(totalBenefit + "");
			
		} catch(Exception e) {
			System.out.println(e.getMessage() != null ? e.getMessage() : "File Write Error");
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return new File(ITEM_FILE_NAME);
	}
}
