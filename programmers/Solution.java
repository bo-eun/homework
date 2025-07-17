package programmers;

public class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        // {최대 카운트, 최소 카운트}
        int[] count = {0, 0};
        
        for(int num1 : lottos) {
            // 내 번호가 0일 때 맞다고 가정했을 경우 최대 카운트 증가
            if(num1 == 0) {
                count[0]++;
            }
            for(int num2 : win_nums) {
                if(num1 == num2) {
                    count[1]++;
                    count[0]++;
                }
            }
        }  
        
        int[] answer = {result(count[0]), result(count[1])};
        return answer;
    }
    
    public int result(int count) {
        return  switch(count) {
            case 6 -> 1;
            case 5 -> 2;
            case 4 -> 3;
            case 3 -> 4;
            case 2 -> 5;
            default -> 6;
        };   
    }
}
