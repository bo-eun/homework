package kr.vanding.data;

public class Item {
	private String name;
	private int quantity;
	private int price;
	private int benefit;
	
	public Item() {}
	
	public Item(String name, int quantity, int price, int benefit) {
		this.setBenefit(benefit);
		this.setName(name);
		this.setQuantity(quantity);
		this.setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBenefit() {
		return benefit;
	}

	public void setBenefit(int benefit) {
		this.benefit = benefit;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	// 기능 세머드 > 개수 뺴기
	public void salesItem(int count) {
		this.quantity -= count;
	}
	
	// 기능 메서드2 > 금액 더하기
	public void addBenefit(int money) {
		this.benefit += money;
	}
	
	// 기능 메서드3 > 재고 추가
	public void addItem(int count) {
		this.quantity += count;
	}
	
	// 기본 출력
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.getQuantity() == 0) {
			sb.append(String.format("%s (재고없음)", this.getName()));
		} else {
			sb.append(String.format("%s (%d원)", this.getName(), this.getPrice()));
		}
		
		return sb.toString();
	}
}
