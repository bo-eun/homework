package kr.study.array;

public class ArrayCopyTest01 {
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		int num = 10;
		
		int num02 = num;
		int[] arr02 = arr;
		
		num02 = num02 + 10;
		arr02[0] = 20;
		
		System.out.println("num = " + num + " num02 = " + num02);
		System.out.println("arr[0] = " + arr[0] + " arr02[0] = " + arr02[0]);
	}
}
