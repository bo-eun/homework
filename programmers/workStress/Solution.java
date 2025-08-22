package workStress;
import java.util.Arrays;

public class Solution {
	
    public static long solution(int n, int[] works) {
        long answer = 0;
        int sum = 0;
        
        for(int work : works) {
            sum += work;
        }
        
        int remainTime = sum - n;
        
        // 작업량 없을 경우 야근지수 0
        if(remainTime <= 0) {
            return 0;
        }
        
        Arrays.sort(works);
        int lastIndex = works.length - 1;
        
        while(n > 0) {
            int max = works[lastIndex];
            for(int i = 0; i <= lastIndex; i++) {
                if(max <= works[i]) {
                    works[i] -= 1;
                    n -=1 ;
                }
                
                if(n <= 0) break;
            }
        }
        
        // 야근 지수 계산
        for(int work : works) {
            answer+= Math.pow(work, 2);
        }
        
        return answer;
    }
    
	public static void main(String[] args) {
		solution(4, new int[]{4,3,3});
		solution(1, new int[]{2,1,2});
		solution(3, new int[]{1,1});

	}

}
