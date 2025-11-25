package day02;

public class VariableTest01 {

	public static void main(String[] args) {
		// 변수 선언
		// 데이터 타입   변수명   =   값;
		// 초기값을 부여하여 만드는 것을 추천
		// 초기값으로 만들 수 있는 가장 작은 값 넣음
		int num01 = 0;
		char ch = '\0';
		long num02 = 0;
		String str = "";
		boolean isTrue = true;
		
		// 문자는 ascii code 표로 인하여 정수와 대응 가능
		char alpha = 'A';
		int alphaValue = alpha;
		
		System.out.println(alpha + " : " + alphaValue);
		// 강제 형변환의 실제 쓰임 중 하나
		// 다른 변수를 만들어 형변환하지 않아도 되는 장점이 있다.
		System.out.println(alpha + " : " + (int)alpha);

	}

}
