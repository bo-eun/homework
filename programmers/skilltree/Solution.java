package skilltree;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        String[] skillArr = skill.split("");

        loop:
        for(int i = 0 ; i <skill_trees.length; i++) {
            // 스킬트리에 포함된 선행 스킬 위치를 담을 list 선언
            List<Integer> indexList = new ArrayList<>();
                   
            // 선행 스킬 위치 구하기
            for(int j = 0; j < skillArr.length; j++) {
                int index = skill_trees[i].indexOf(skillArr[j]);
                indexList.add(index);
            }
            
            // 선행 스킬의 끝 요소가 연속해서 없을 경우 list에서 삭제 
            while(!indexList.isEmpty() && indexList.get(indexList.size() - 1) == -1) {
                indexList.remove(indexList.size() - 1);
            }
            
            // 선행 스킬의 현재 위치와 다음 위치를 반복하여 비교
            for(int j = 0; j < indexList.size() - 1; j++) {
                int nextIndex = j + 1 > indexList.size() - 1 ? indexList.size() - 1 : j + 1;
                // 첫 선행 스킬이 없을 경우 바깥 반복문 건너뛰기
                if(j == 0 && indexList.get(j) == -1) {
                    continue loop;
                }
                // 현재 선행 스킬이 다음 선행 스킬보다 뒤에 위치했을 경우 바깥 반복문 건너뛰기
                if(indexList.get(j) > indexList.get(nextIndex)) {
                    continue loop;
                }
            }
            // 첫번째 선행 스킬이 스킬트리에 포함되어 있고, 선행 스킬이 스킬트리에 순서대로 포함되어 있을 경우 증가
            answer++;
        }
        return answer;
    }	
	
	
	public static void main(String[] args) {
		System.out.println(solution("CBD", new String[] {"BACDE", "CBADF", "AECB", "BDA"}));

	}

}
