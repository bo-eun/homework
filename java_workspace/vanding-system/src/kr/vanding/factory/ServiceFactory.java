package kr.vanding.factory;

import java.util.List;
import java.util.Scanner;

import kr.vanding.data.Item;
import kr.vanding.data.ItemStore;
import kr.vanding.service.AdminService;
import kr.vanding.service.CommonService;
import kr.vanding.service.CustomerService;

/*
 * factory pattern
 * 조건에 의해서 원하는 객체를 만들어주는 공장 클래스를 만든다.
 * 사용자는 생성되는 객체에 대해서 신경쓰지 않는다.
 * 
 * 공용 인터페이스가 있어야 하며, 해당 인터페이스를 상속하는 클래스가 두개 있어야 한다.
 * 
 * */
public class ServiceFactory {
	public static CommonService getService(int choice, List<Item> itemList, Scanner sc, ItemStore store) {
		// case value -> .. break문 필요 없음, return문 필요 없음
		
		return switch(choice) {
		case 1 -> new CustomerService(itemList, sc, store);
		case 2 -> new AdminService(itemList, sc, store);
		default -> null;
		};
	}
}
