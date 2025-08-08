package apple;
import java.util.*;

public class Solution {
    public static int solution(int k, int m, int[] score) {
        int answer = 0;
        
        Arrays.sort(score); // 내림차순 정렬
        int[] sortedScore = new int[score.length]; // 오름차순 정렬을 저장하기 위한 배열
        
        // 배열에 오름차순으로 저장
        for(int i = 0; i < score.length; i++) {
            int reverseNum = score[score.length - 1 - i];
            sortedScore[i] = reverseNum;
        }

        int minNum = 0; // 최소값 저장
        
        // 오름차순으로 저장된 배열 비교하여 
        for(int i = 0; i < sortedScore.length; i++) {
            // 최소값이 초기값(0)일 경우 현재값을 최소값으로, 아닐경우 최소값
            minNum = minNum == 0 ? sortedScore[i] : Math.min(sortedScore[i], minNum);
            
            // 한 상자 됐을 때
            if((i + 1) % m  == 0) {
                answer += minNum * m; // answer에 상자 계산값 누적
                minNum = 0; // 다른 상자의 최소값 구하기 위해 최소값 초기화
            }
        }
        
        return answer;
    }
    
	public static void main(String[] args) {
		System.out.println(solution(3, 4, new int[] {1, 2, 3, 1, 2, 3, 1}));	
		System.out.println(solution(4, 3, new int[] {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2}));
	}

}
