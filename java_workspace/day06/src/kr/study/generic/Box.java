package kr.study.generic;

public class Box<T> {

	private Object[] box;
	private int size;
	
	// 사용자 정의 생성자
	// 기본 10개 사이즈 배열 생성
	// 어떤 타입의 요소가 담길 줄 아직 모르기 때문에 T로 받는 wrapper class의 최상위 클래스인 Object로 일단 타입을 지정함
	public Box() {
		box = new Object[10];
	}
	
	// 배열에 요소 추가하는 메서드
	public void add(T val) {
		if(box.length == size) {
			Object[] newBox = new Object[box.length * 2];
			// 얕은 복사
			System.arraycopy(box, 0, newBox, 0, box.length);
			// 박스 교체
			box = newBox;
		}
		box[size++] = val;
	}
	
	public T get(int index) {
		// 배열을 내보낼 때 데이터 타입을 받은 제네릭으로 변경함 
		return (T)box[index];
	}
	
	public int size() {
		return size;
	}
	
}
