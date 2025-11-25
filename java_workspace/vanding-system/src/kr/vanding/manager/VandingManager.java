package kr.vanding.manager;

import java.util.List;
import java.util.Scanner;

import kr.vanding.data.Item;
import kr.vanding.data.ItemStore;
import kr.vanding.factory.ServiceFactory;
import kr.vanding.service.CommonService;

public class VandingManager {
	private Scanner scan;
	private ItemStore store;
	private List<Item> itemList;
	
	public VandingManager() {
		this.scan = new Scanner(System.in);
		this.store = new ItemStore();
		this.itemList = this.store.getItemList();
	}
	
	public void getItem() {
		System.out.println("======================================");
		System.out.println("              또와요 자판기");
		System.out.println("======================================");
		while(true) {
			try {
				System.out.println("======================================");
				System.out.println("메뉴선택 : 	1.사용자    2.관리자    3.종료");
				System.out.println("======================================");
				
				int menu = scan.nextInt();
				
				if(menu == 3) {
					System.out.println("======= 또 오세요, 안녕 =======");
					break;
				}
				// 사용자 선택에 의해 서비스가 반환된다.
				CommonService service = ServiceFactory.getService(menu, itemList, scan, store);
				if(service == null) {
					throw new Exception("메뉴를 다시 선택해 주세요");
				}		
			} catch(Exception e) {
				System.out.println(e.getMessage() != null ? e.getMessage() : "시스템 오류!!");
				e.printStackTrace();
			}

		}
	}
}
