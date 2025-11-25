package beverageVendingMachine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beverageVendingMachine.data.Beverage;
import beverageVendingMachine.store.FileStores;


public class BeverageMachineService {
	private Scanner scan;
	private FileStores store;
	private List<Beverage> beverageList;
	
	
	// 메서드 여러 곳에서 사용하기 때문에 밖으로 빼냄
	private int mode;

	public BeverageMachineService() {
		scan = new Scanner(System.in);
		store = new FileStores();
		beverageList = new ArrayList<Beverage>();
		mode = 0;
	}
	
	public void start() {
		
		try {
			getAllList();
			
			System.out.println("===========================");
			System.out.println("모드선택 : 1.사용자 2.관리자 3.종료");
			System.out.println("===========================");
			
			mode = scan.nextInt();
			
			loop:
			while(true) {
				switch(mode) {
				case 1 :
					userMode();
					break;
				case 2 :
					adminMode();
					break;
				case 3 : 
					System.out.println("프로그램이 종료되었습니다");
					// 루프 밖으로 나와서 종료
					break loop;
				default : 
					System.out.println("1, 2, 3 중 하나를 입력해 주세요.");
					start();
					// 루프 밖으로 나와서 종료
					break loop;
				}
			};			
		} catch(Exception e) {
			System.out.println("올바른 숫자를 입력해 주세요.");
		}


	}
	
	public List<Beverage> getAllList() {
		try {
			// 이전 리스트 비우기
			beverageList.clear();
			beverageList = store.getAllList();		
		} catch(Exception e) {
			System.out.println("리스트 가져오기 실패!");
		}
		
		return beverageList;
	}
	
	public void userMode() {
		// txt에 있는 음료 이름 가져오기
		for(int i = 0; i < beverageList.size(); i++) {
			System.out.print((i + 1)+"."+beverageList.get(i).getName() + " ");
		}
		
		System.out.print("6.메인메뉴(모드선택)  7.취소(종료)");
		int menu = scan.nextInt();
		
		// 메인메뉴 선택
		if(menu == 6) {
			start();
			return;
		}
		// 프로그램 종료
		if(menu == 7) {
			mode = 3; 
			return;
		}
		Beverage selectedBeverage = beverageList.get(menu - 1);
		
		// 선택한 메뉴가 재고가 없을 때, 재고 없음 안내 후 다시 선택할 수 있게 함
		if(selectedBeverage.getCount() == 0) {
			System.out.println(selectedBeverage.getName()+"는 현재 재고가 없습니다. 다른 메뉴를 선택하세요.");
			userMode();
			
			return;
		}
		
		
		System.out.println(selectedBeverage.getName()+"의 개수를 선택해주세요.");
		int count = scan.nextInt();	
		
		// 입력한 개수가 현재 재고보다 많을 때
		while(count > selectedBeverage.getCount()) {
			System.out.println(selectedBeverage.getName()+"는 " + selectedBeverage.getCount() + "개 이상 구매하실 수 없습니다. 다시 입력해 주세요.");
			count = scan.nextInt();	
		}

		
		
		int totalPrice = count * selectedBeverage.getPrice();
		
		// 기존 수량 - 선택한 수량
		selectedBeverage.setCount(selectedBeverage.getCount() - count);
		// 총 매출 저장
		Beverage.totalAmount += totalPrice;
		
		System.out.println("---------------------------------");
		System.out.println(beverageList.get(menu - 1).getName() + "가 " + count + "개 판매되었습니다. (" + totalPrice + ")");
		System.out.println("---------------------------------");
		
		updateList();
		
	}
	
	public void adminMode() {
		System.out.println("=========================================================");
		System.out.println("1. 재고변경(개수변경) 2.물건변경(제품변경) 3.수익보기(판매금) 4.메인메뉴");
		System.out.println("=========================================================");
		
		int menu = scan.nextInt();
		
		switch(menu) {
		
		case 1 :
			System.out.println("재고 변경을 위한 상품을 선택해주세요.");
			for(int i = 0; i < beverageList.size(); i++) {
				System.out.print((i + 1)+"."+beverageList.get(i).getName() + " ");
			}
			int index = scan.nextInt() - 1;
			Beverage selectedBeverage = beverageList.get(index);
			
			System.out.println(selectedBeverage.getName()+" 남은 재고 : " + selectedBeverage.getCount());
			System.out.println("변경할 재고를 입력해 주세요.");
			int updateCount = scan.nextInt();
			
			scan.nextLine(); // 버퍼 비우기
			
			selectedBeverage.setCount(updateCount);
			System.out.println("변경된 내용 : " + selectedBeverage.getName() + " 재고 " + selectedBeverage.getCount());
			
			break;
			
		case 2  :
			System.out.println("변경할 상품을 선택해주세요.");
			for(int i = 0; i < beverageList.size(); i++) {
				System.out.print((i + 1)+"."+beverageList.get(i).getName() + " ");
			}
			index = scan.nextInt() - 1;
			selectedBeverage = beverageList.get(index);
			
			scan.nextLine(); // 버퍼 비우기
			
			System.out.println("새 상품 정보를 입력해주세요");
			System.out.println("상품 이름 : ");
			String name = scan.nextLine();
			
			System.out.println("상품 가격 : ");
			int price = scan.nextInt();
			
			System.out.println("상품 수량 : ");
			int count = scan.nextInt();
			
			Beverage newBeverage = new Beverage(name, price, count);
			
			beverageList.remove(index);
			beverageList.add(index, newBeverage);
			updateList();
			
			System.out.print(selectedBeverage.getName() + " 가격: " + selectedBeverage.getPrice() + " 재고: " + selectedBeverage.getCount() + " >>> ");
			System.out.println(newBeverage.getName() + " 가격: " + newBeverage.getPrice() + " 재고: " + newBeverage.getCount());
			
			break;
			
		case 3 :
			int totalAmount = Beverage.totalAmount;
			System.out.println("현재까지 판매한 금액은 총 " + totalAmount + "입니다.");
			
			break;
			
		case 4 :
			start();
			break;
		}
	}
	
	public void updateList() {
		store.writeBeverageInfo();			
	}
}
