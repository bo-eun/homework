package pj02;

import pj02.service.AccountService;

public class AccountMain {
	public static void main(String[] args) {
		AccountService service = new AccountService();
		service.start();
	}
}
