package sh.manage;

import sh.manage.service.ManageService;

/* main 클래스는 객체 만들고 실행하기만 하는 공간 */
public class ManagerMain {
	public static void main(String[] args) {
		ManageService service = new ManageService();
		service.start();
	}
}
