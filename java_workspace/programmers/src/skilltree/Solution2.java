package skilltree;

public class Solution2 {
	public static int solution(String skill, String[] skill_trees) {
		int answer = 0;
		
		for(String s : skill_trees) {
			int pos = 0;
			
			for(int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				
				// 선행스킬에 스킬트리 문자가 없을 경우 건너띄기
				if(skill.indexOf(ch) == -1) continue;
				
				// 선행스킬에 스킬트리 문자가 
				if(skill.indexOf(ch) == pos) {
					pos++;
				} else {
					pos = -1;
					break;
				}					
			}
			if(pos >= 0) answer++;
		}
		
		System.out.println(answer);
		return answer;

	}
	
	
	public static void main(String[] args) {
		solution("CBD", new String[] {"BACDE", "CBADF", "AECB", "BDA"});
	}
}
