package kr.study.array;

import java.util.Arrays;

public class ArrayCopyTest02 {
	public static void main(String[] args) {
		// 깊은복사
		int[] card = {1,3,4,5,6,10};
		int[] newCard = new int[card.length];
		
		// 복사!(원본배열, 원본시작위치, 복사할 대상, 복사할 대상의 시작위치, 복사할 길이);
		System.arraycopy(card, 0, newCard, 0, card.length);
		newCard[0] = 11;
		
		System.out.println("원본 : " + Arrays.toString(card));
		System.out.println("복사본 : " + Arrays.toString(newCard));
	}
}
