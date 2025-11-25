package pj01;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
/*
 * 
 * 1부터 100이하의 정수 중에서 소수를 구하여 모두 출력해보세요.
	소수는 배열에 담아서 출력하십시오. (20점)
	소수는 1과 자기자신으로만 나눌 수 있는 자연수를 의미 합니다.
	(실행결과)
	2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
 * 
 * */
	public static void main(String[] args) {
		List<Integer> primeList = new ArrayList<>();
		
		for(int i = 2; i < 101; i++) {
			boolean isPrime = false;
			for(int j = 2; j < i; j++) {
				if(i % j != 0) {
					isPrime = true;
				} else {
					isPrime = false;
					break;
				}
			}
			
			if(isPrime) {
				primeList.add(i);
			}
			
		}	
		
		System.out.println(primeList);
	}

}
