package kr.vanding.service;

import java.util.List;
import java.util.Scanner;

import kr.vanding.data.Item;
import kr.vanding.data.ItemStore;

public class CustomerService implements CommonService {

	private List<Item> itemList;
	private Scanner scan;
	private ItemStore store;
	
	public CustomerService(List<Item> itemList, Scanner scan, ItemStore store) {
		this.itemList = itemList;
		this.scan = scan;
		this.store = store;
	}
	
	@Override
	public void start() {
		System.out.println("====================== 사용자 모드 시작 ========================");
		
		while(true) {
			try {
				for(int i = 0; i < itemList.size(); i++) {
					Item item = itemList.get(i);
					System.out.print((i + 1) + "." + item + " ");
				}
				
				System.out.println((itemList.size() + 1) + ".메인메뉴  ");
				System.out.println("===========================================");
				System.out.println("메뉴를 선택 > ");
				int menu = scan.nextInt();
				
				if((itemList.size() + 1) == menu) {
					break; // 사용자 모드 종료하고 돌아감
				} else {
					processVanding(menu);
				}
				
			} catch(Exception e) {
				System.out.println(e.getMessage() != null ? e.getMessage() : "Error");
			}
		}
		
	}
	
	public void processVanding(int menu) throws Exception {
		int index = menu - 1;
		// 선택된 아이템 객체 가져오기
		Item item = itemList.get(index);
		
		if(item.getQuantity() == 0) {
			System.out.println(item.getName() + "은 재고가 없습니다.");
			return;
		}
		
		System.out.println("몇개 구매하시겠습니까?(최소 1개)");
		int count = scan.nextInt();
		
		if(count == 0) {
			System.out.println("최소 1개 이상 구매해야 합니다.");
			return;
		}
		
		int money = item.getPrice() * count;
		
		item.salesItem(count); // 재고 빼기
		item.addBenefit(money); // 금액 증가
		
		System.out.println(item.getName() + ", " + money + "원 구매하였습니다.");
		System.out.println("===================================================");
		
		// 전체 수익에 입금하기
		this.store.addTotalBenefit(money);
		
		// 아이템 갱신
		this.store.writeItemList();
	}
}
