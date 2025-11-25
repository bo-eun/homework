package kr.study.generic;

public class GenericMain {
	public static void main(String[] args) {
		// int 타입밖에 못다루는 클래스
		IntBox intBox = new IntBox();
		intBox.add(10);
		intBox.add(20);
		intBox.add(30);
		intBox.add(40);
		
		for(int i = 0; i < intBox.size(); i++) {
			System.out.println(intBox.get(i));
		}
		
		// 제네릭을 사용하면 클래스에서 다룰 타입을 
		// 클래스에서 사용할 데이터의 wrapper class를 넣어 객체를 만든다.
		
		// Double wrapper class를 내부에서 사용하는 클래스
		Box<Double> doubleBox = new Box<>();
		doubleBox.add(30.11);
		doubleBox.add(10.25);
		doubleBox.add(5.24);
		
		for(int i = 0; i < doubleBox.size(); i++) {
			System.out.println(doubleBox.get(i));
		}
		
		// String
		Box<String> strBox = new Box<>();
		strBox.add("안녕~");
		strBox.add("나는 문자야");
		strBox.add("화이팅");
		
		for(int i = 0; i < strBox.size(); i++) {
			System.out.println(strBox.get(i));
		}
	}
}
