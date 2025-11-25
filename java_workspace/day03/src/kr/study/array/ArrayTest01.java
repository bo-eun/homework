package kr.study.array;

public class ArrayTest01 {
	public static void main(String[] args) {
		
		int[] arr01 = new int[5]; // {0,0,0,0,0}
		int[] arr02 = {1,2,3,4,5}; // 선언 시에만 가능
		int[] arr03 = null; // 배열은 만들되 공간을 주지 않은 경우. 많지 않음
		// int[] arr04; 배열이 만들어지지 않음. 초기화 단계가 없기 때문...
		
		// 값 넣기
		arr01[0] = 1;
		arr01[1] = 10;
		
		//loop 이용
		for(int i = 0; i < arr01.length; i++ ) {
			arr01[i] = (int)(Math.random() * 30) + 1;
			System.out.print(arr01[i] + ",");
		}
		
		System.out.println("\narr01 = " + arr01);
		
	}
}
