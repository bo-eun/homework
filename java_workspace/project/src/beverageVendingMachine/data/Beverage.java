package beverageVendingMachine.data;

public class Beverage {
	private String name;
	private int price;
	private int count;
	private int saleCount;
	
	public static int totalAmount = 0;
	
	public Beverage(String name, int price, int count) {
		this.name = name;
		this.price = price;
		this.count = count;

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	
}
