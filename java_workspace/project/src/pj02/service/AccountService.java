package pj02.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import pj02.data.Account;
import pj02.store.FileStores;

public class AccountService {
	private Scanner scan;
	private FileStores store;
	private List<Account> list;
	
	public AccountService() {
		scan = new Scanner(System.in);
		store = new FileStores();
		list = new ArrayList<>();
	}
	
	public void start() {
		try {
			int menu;
			getAllList();
			
			// label 사용하면 밖의 루프를 종료할 수 있다.
			// loop문의 이름을 붙여 제어할 수 있음
			loop:
			while(true) {
				try {
					// 메뉴 선택 입력값 받기
					menu = this.getMenu();
					
					switch(menu) {
					case 1 : // 계좌 개설
						createAccount();
						break;
					case 2 : // 입금
						desposit();
						break;
					case 3 : // 출금
						withdraw();
						break;
					case 4 : // 잔액확인
						getMyBalance();
						break;
					case 5 : // 계좌 해지
						deleteAcount();
						break;
					case 6 : // 종료
						System.out.println("프로그램이 종료되었습니다");
						break loop;
					default : 
						System.out.println("메뉴 선택이 잘못되었습니다");
					}
					
				}catch(Exception e) {
					scan.nextLine(); // 키보드 비우기
					System.out.println(e.getMessage() == null ? "에러!" : e.getMessage());
				}
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage() == null ? "에러!" : e.getMessage());
		}
	}
	
	
	// 메뉴 선택 실행
	private int getMenu() throws Exception {
		int menu = 0;
		System.out.println("=============================================================");
		System.out.println("1. 계좌개설   2. 입금   3. 출금   4. 잔액확인   5. 계좌 해지  6. 종료");
		System.out.println("=============================================================");
		System.out.println("메뉴 선택 > ");
		menu = scan.nextInt();
		scan.nextLine(); // 버퍼 비우기
		
		return menu;
	};
	
	// 계좌 정보가 담긴 전체 리스트 출력해서 배열 리턴
	private List<Account> getAllList() {
		try {
			list = store.getAccountList();			
		} catch(Exception e) {
			System.out.println("에러!");
		}
		return list;
	}
	
	
	// 계좌 개설
	// 계좌 번호 중복 확인 한 후 입금하기
	public void createAccount() throws Exception {
		Account ac = new Account();
		System.out.println("개설 할 계좌번호를 입력하세요.");
		String userNumber = scan.nextLine();
		
		// 전체 계좌 중 입력받은 계좌번호가 있다면 이미 등록된 계좌라고 안내
		for(Account account : list) {
			if(account.getMyNumber().equals(userNumber)) {
				System.out.println("이미 등록된 계좌입니다.");
				return;
			}
		}
		ac.setMyNumber(userNumber);

		// 입금할 금액 입력받아 계좌 객체에 넣음
		System.out.println("입금할 금액을 입력하세요.");
		int money = scan.nextInt();
		ac.setBalance(money);
		
		// 계좌 리스트에 저장
		list.add(ac);
		
		// 파일에 쓰기
		store.writeAccount(list);
		
		System.out.println("신규 계좌가 개설되었습니다.");
	}
	
	// 입금
	public void desposit() {
		System.out.println("입금 할 계좌번호를 입력하세요.");
		String userNumber = scan.nextLine();
		// 검색한 계좌객체 담는 변수
		Account searchedAc = null;

		for(Account ac : list) {
			if(ac.getMyNumber().equals(userNumber)) {
				searchedAc = ac;
				System.out.println("입금 할 금액을 입력하세요.");
				int money = scan.nextInt();
				
				// 입금하기 실행, 내 객체의 잔액이 업데이트 됨
				ac.deposit(money);
				// txt파일 업데이트
				store.writeAccount(list);
				break;
			}
		}
		
		if(searchedAc == null) {
			System.out.println("해당 계좌번호가 존재하지 않습니다.");
		}
	}
	
	// 출금
	public void withdraw() {
		System.out.println("출금 할 계좌번호를 입력하세요.");
		String userNumber = scan.nextLine();
		// 검색한 계좌객체 담는 변수
		Account searchedAc = null;
		
		// 계좌 리스트 중에서
		for(Account ac : list) {
			// 만약 입력받은 계좌번호와 계좌번호가 같은게 있다면 출금 받기
			if(ac.getMyNumber().equals(userNumber)) {
				searchedAc = ac;
				System.out.println("출금 할 금액을 입력하세요.");
				int money = scan.nextInt();
				
				if(money > ac.getBalance()) {
					System.out.println("출금할 금액이 충분하지 않습니다. 확인해주세요.");
					return;
				}
				
				ac.withdraw(money);
				// txt파일 업데이트
				store.writeAccount(list);
				break;
			}
		}
		
		if(searchedAc == null) {
			System.out.println("해당 계좌번호가 존재하지 않습니다.");
		}
		
	}
	
	// 잔액 확인
	public void getMyBalance() {
		System.out.println("확인 할 계좌번호를 입력하세요.");
		String userNumber = scan.nextLine();
		Account searchedAc = null;
		
		// 계좌 리스트 중에서
		for(Account ac : list) {
			if(ac.getMyNumber().equals(userNumber)) {
				searchedAc = ac;
				System.out.println(ac);
				break;	
			}
		}
		
		if(searchedAc == null) {
			System.out.println("해당 계좌번호가 존재하지 않습니다.");
		}
	}
	
	// 계좌 해지
	public void deleteAcount() {
		System.out.println("해지 할 계좌번호를 입력하세요.");
		String userNumber = scan.nextLine();
		
		// 전체 리스트에 있는 계좌 정보와 삭제할 계좌 번호를 비교하여 같은 경우 리스트에 저장
		List<Account> searchAc =
				list.stream()
				.filter(ac -> ac.getMyNumber().equals(userNumber))
				.collect(Collectors.toList()); // 고칠 수 있음. .toList()는 못고침
		
		// 검색된게 없으면 출력 후 종료한다.
		if(searchAc == null || searchAc.size() == 0) {
			System.out.println("삭제할 계좌번호가 없습니다.");
			return;
		}
		
		list.removeAll(searchAc);
		// txt파일 업데이트
		store.writeAccount(list);
		System.out.println(userNumber + " 계좌가 해지 되었습니다.");
		
	}
	
	// 종료
}
