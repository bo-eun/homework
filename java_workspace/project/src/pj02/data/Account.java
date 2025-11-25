package pj02.data;

public class Account {
	private String myNumber; // 계좌번호
	private int balance; // 계좌 잔액
	
	// 계좌 구하는 getter메서드
	public String getMyNumber() {
		return myNumber;
	}
	// 계좌 설정하는 setter메서드
	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}
	// 잔액 구하는 getter메서드
	public int getBalance() {
		return balance;
	}
	// 잔액 설정하는 setter메서드
	public void setBalance(int balance) {
		this.balance = balance;
	}	
	
	// 입금했을 때 실행되는 메서드, 잔액 업데이트
	public void deposit(int money) {
		this.balance += money;
	}
	
	// 출금했을 때 실행되는 메서드, 잔액 업데이트
	public void withdraw(int money) {
		this.balance -= money;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("계좌번호 : " + this.getMyNumber() + ", 잔액 : " + this.getBalance());
		return sb.toString();
	}
}
