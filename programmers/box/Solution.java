package box;
import java.util.*;

public class Solution {
    public static int solution(int n, int w, int num) {
        
        int row = (int) Math.ceil((double)n / w); // 박스 배열 row
        int[][] boxs = new int[row][w]; // 박스를 놓을 배열 생성
        int currentBox = 1; // 현재 놓을 박스
        int[] numBoxIndex = {0,0}; // 행,열
        
        // 박스 배열에 박스를 밑에서부터 쌓음
        for(int i = row - 1; i >= 0; i--) {
            // boxs의 외부배열의 마지막 행을 기준으로 정배열-역배열-정배열..이 되어야 함
            if((row - 1 - i) % 2 == 0) { // 정배열
                for(int j = 0; j < w; j++) {
                    // num 위치 저장
                    if(num == currentBox) {
                        numBoxIndex[0] = i;
                        numBoxIndex[1] = j;
                    }
                    
                    // currentBox가 주어진n보다 클 때 box배열에 0 담김
                    boxs[i][j] = currentBox <= n ?  currentBox : 0;
                    currentBox++;
                    
                }
            } else { // 역배열
                for(int j = w - 1; j >= 0; j--) {
                    // num 위치 저장
                    if(num == currentBox) {
                        numBoxIndex[0] = i;
                        numBoxIndex[1] = j;
                    }
                    
                    // currentBox가 주어진n보다 클 때 box배열에 0 담김
                    boxs[i][j] = currentBox <= n ?  currentBox : 0;
                    currentBox++;
                }
            }
        }

        // 꺼내야 하는 상자의 총개수에 내가 포함되어야 하므로 answer의 기본값은 1
        int answer = 1;
        // 꺼내야 하는 상자의 위치값 중 행을 반복하여 총 몇개의 상자를 꺼내야 하는지 확인
        for(int i = 0; i < numBoxIndex[0]; i++) {
            // 같은 열의 상자 중 상자가 없는게 아닌(0이 아닌) 경우 꺼내는 횟수 증가
            if(boxs[i][numBoxIndex[1]] != 0) {
                answer++;
            }
        }

        return answer;
    }

	public static void main(String[] args) {
		System.out.println(solution(22, 6, 8));

	}

}
