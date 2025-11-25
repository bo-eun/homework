package box;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution2 {
	
	public static int solution(int n, int w, int num) {
		int answer = 0;
		int totalRow = (int) Math.ceil((double) n / w);
		
		List<List<Integer>> box = IntStream.range(0, totalRow) // 이중배열의 행 만들기
									.mapToObj(i -> IntStream.range(0, w) // 이중배열의 열 만들기
									.mapToObj(j -> 0)
									.collect(Collectors.toList()) // 일반 toList()는 불변객체로 만들어져 수정이 가능한 collect(Collectors.toList())를 쓴다.
									).collect(Collectors.toList());
		int count = 1;
		int targetRow = 0;
		int targetCol = 0;
		
		// 역순으로 loop
		for(int i = totalRow - 1; i >= 0; i--) {
			List<Integer> row = new ArrayList<>();
			for(int j = 0; j < w; j++) {
				if(count > n) {
					row.add(0);
					continue;
				}
				row.add(count++);
			}
			// if((totalRow - 1 - i) % 2 == 1) Collections.reverse(row);
			// 전체 높이가 홀수와 짝수일 때 뒤집히는 라인이 다르다.
			int reversCount = totalRow % 2 == 0 ? 0 : 1;
			if(i % 2 == reversCount) Collections.reverse(row);
			box.set(i, row);
			
			// 찾아야 할 숫자 위치 찾기
			if(row.contains(num)) {
				targetRow = i;
				targetCol = row.indexOf(num);
			}
		}
		
		for(int i = 0; i < totalRow; i++) {
			System.out.println(box.get(i));
		}
		
		// 나 포함 내 위치에서 위로 몇개가 있는지 찾기
		for(int i = 0; i <= targetRow; i++) {
			if(box.get(i).get(targetCol) != 0) {
				answer++;
			}
		}
		
		System.out.println(targetRow + ", " + targetCol);
		System.out.println(answer);
		
		return answer;
	}
	
	
	public static void main(String[] args) {
		
		solution(13, 3, 6);
		solution(22, 6, 8);
	}
}
