package kr.vanding.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

import kr.vanding.data.Item;
import kr.vanding.data.ItemStore;

public class AdminService implements CommonService {

	private List<Item> itemList;
	private Scanner scan;
	private ItemStore store;
	
	public AdminService(List<Item> itemList, Scanner scan, ItemStore store) {
		this.itemList = itemList;
		this.scan = scan;
		this.store = store;
	}
	
	@Override
	public void start() {
		System.out.println("====================== 관리자 모드 시작 ========================");
		
		while(true) {
			try {
				
				System.out.println("===========================================");
				System.out.println("1.재고변경 2.물품변경 3.수익확인 4.메인메뉴");
				System.out.println("===========================================");
				System.out.println("메뉴를 선택 > ");
				int menu = scan.nextInt();
				
				switch(menu) {
				case 1 :
					changeStock();
					break;
				case 2 :
					changeItem();
					break;
				case 3 :
					checkBenefit();
					break;
				case 4 :
					return; // 메서드 종료, break됨
				}
				
			} catch(Exception e) {
				System.out.println(e.getMessage() != null ? e.getMessage() : "Error");
			}

		}
	}
	
	public void changeStock() throws Exception {
		System.out.println("============== 재고를 추가할 메뉴를 선택하세요. ===============");
		
		for(int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			System.out.println((i + 1) + "." + item.getName() + " ");
		}
		
		int index = scan.nextInt() - 1;
		
		// 입력값에서 1개 빼면 index가 된다.
		Item item = itemList.get(index);
		
		System.out.println("추가할 재고의 개수 입력 : ");
		int count = scan.nextInt();
		item.addItem(count);
		// 아이템 갱신
		this.store.writeItemList();
		System.out.println("============ " + item.getName() + "이" + count + " 개 추가되었습니다. ============");
	}
	
	public void changeItem() throws Exception {
		System.out.println("============== 변경할 메뉴를 선택하세요. ===============");
		
		for(int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			System.out.println((i + 1) + "." + item.getName() + " ");
		}
		
		System.out.println("메뉴 번호 : ");
		int index = scan.nextInt() - 1;
		
		// 삭제 안내
		System.out.println("==========" + itemList.get(index).getName() + "상품이 추가되었습니다. ==========");
		// 지우기
		itemList.remove(index);
		
		// 추가 할 제품을 입력
		System.out.println("변경할 제품 명 : ");
		String name = scan.next();
		System.out.println("제품 재고량 : ");
		int quantity = scan.nextInt();
		System.out.println("자품 가격 : ");
		int price =scan.nextInt();
		
		itemList.add(index, new Item(name, quantity, price, 0));
		
		// 아이템 갱신
		this.store.writeItemList();
		System.out.println("==========" + name + "상품이 추가되었습니다. ==========");
	}
	
	public void checkBenefit() throws Exception {
		
		DecimalFormat df = new DecimalFormat("#,###");
		
		System.out.println("상품 총 수입 : " + df.format(store.getTotalBenefit()) + "원 입니다.");
	}
}
