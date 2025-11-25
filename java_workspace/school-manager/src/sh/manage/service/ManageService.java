package sh.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import sh.manage.data.Student;
import sh.manage.store.FileStores;
/*
 * 학생들의 정보를 볼 수 있는 기능을 제공하는 클래스
 * */
public class ManageService {

	private Scanner scan;
	private FileStores store;
	private List<Student> list; // 저장한 학생을 담는 리스트
	
	
	/*
	 * 생성자에서 객체 선언 하는 이유는
	 * service를 사용할 때 메모리에 올리기 위해서
	 * 서비스 사용전에는 정의만 해놓는데 이때는 메모리에
	 * 등록되지 않는다.
	 * */
	
	// 사용자정의 생성자함수
	// 객체가 만들어질 때 scan, store, list 생성
	public ManageService() {
		scan = new Scanner(System.in);
		store = new FileStores();
		list = new ArrayList<>();
	}
	
	private void initData() throws Exception {
		list = store.getAllList();
	}
	
	public void start() {
		try {
			int menu;
			
			System.out.println("=============================================================");
			System.out.println("|                                                            |");
			System.out.println("|                       학 생 관 리 프로그램                      |");
			System.out.println("|                                                            |");
			System.out.println("=============================================================");
			
			// 미리 전체학생 불러오기
			initData(); 
			
			// label 사용하면 밖의 루프를 종료할 수 있다.
			// loop문의 이름을 붙여 제어할 수 있음
			loop:
			while(true) {
				try {
					// 메뉴 선택 입력값 받기
					menu = this.getMenu();
					
					switch(menu) {
					case 1 : // 등록 메뉴를 선택했다면
						this.newStudent();
						break;
					case 2 : // 전체 목록을 선택했다면
						printAllStudent();
						break;
					case 3 : 
						searchStudent();
						break;
					case 4 : 
						// 학생 이름 검색 후 삭제, 업데이트
						deleteStudent();
						break;
					case 5 : // 종료 메뉴를 선택했다면
						System.out.println("프로그램이 종료됩니다!");
						break loop;
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
		System.out.println("1. 등록       2. 전체목록       3. 검색       4. 삭제       5. 종료");
		System.out.println("=============================================================");
		menu = scan.nextInt();
		scan.nextLine(); // 버퍼 비우기
		
		return menu;
	};
	
	private void newStudent() throws Exception {
		Student st = new Student();
		System.out.println("등록 할 학생 이름 : ");
		st.setMyName(scan.nextLine());
		System.out.println("등록할 학생 국어 : ");
		st.setKor(scan.nextInt());
		System.out.println("등록할 학생 수학 : ");
		st.setMath(scan.nextInt());
		System.out.println("등록할 학생 영어 : ");
		st.setEng(scan.nextInt());
		
		list.add(st);
		
		// 파일에 쓰기
		store.writeStudent(list);
	}
	
	private void printAllStudent() throws Exception {
		if(list == null || list.size() == 0) {
			System.out.println("학생 정보가 존재하지 않습니다.");
			return; // 종료 break와 같음
		}
		
		for(Student st : list) {
			System.out.println(st);
		}
	}
	
	private void searchStudent() throws Exception {
		
		if(list == null || list.size() == 0) {
			System.out.println("학생 정보가 존재하지 않습니다.");
			return; // 종료 break와 같음
		}
		
		System.out.println("검색할 학생 이름 : ");
		String searchName = scan.nextLine();
		
		// 동명의 학생이 있을 수 있으니 리스트에 저장한다.
		List<Student> searchStd = new ArrayList<>();
		
		for(Student st : list) {
			// 같은 이름 학생을 저장한다.
			if(st.getMyName().equals(searchName)) {
				searchStd.add(st);
			}
		}
		
		// 검색된게 없으면 출력 후 종료한다.
		if(searchStd.size() == 0) {
			System.out.println("찾는 이름의 학생이 없습니다.");
			return;
		}
		
		// 찾은 학생 출력
		for(Student st : searchStd) {
			System.out.println(st);
		}
	}
	
	private void deleteStudent() throws Exception {
		
		if(list == null || list.size() == 0) {
			System.out.println("학생 정보가 존재하지 않습니다.");
			return; // 종료 break와 같음
		}
		
		System.out.println("삭제할 학생 이름 : ");
		String searchName = scan.nextLine();

		
		// 전체 리스트에 있는 학생 이름과 삭제할 학생 이름을 비교하여 같은 경우 리스트에 저장
		List<Student> searchStd =
				list.stream()
				.filter(std -> std.getMyName().equals(searchName))
				.collect(Collectors.toList()); // 고칠 수 있음. .toList()는 못고침
		
		// 검색된게 없으면 출력 후 종료한다.
		if(searchStd == null || searchStd.size() == 0) {
			System.out.println("삭제할 이름의 학생이 없습니다.");
			return;
		}
		
		list.removeAll(searchStd);
		store.writeStudent(list);
		System.out.println(searchName + " 이름의 학생이 삭제되었습니다.");
		
	}
}
