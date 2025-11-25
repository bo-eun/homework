package kr.study;

public class StudentMain {

	public static void main(String[] args) {
		
		Student st = new Student();
		st.myName = "김영희";
		st.kor = 90;
		st.eng = 88;
		st.math = 73;
		
		System.out.print("이름 : " + st.myName);
		System.out.print(", 국어 : " + st.kor);
		System.out.print(", 영어 : " + st.eng);
		System.out.print(", 수학 : " + st.math);
		System.out.println(", 총점 : " + st.getTotal() + ", 평균 : " + st.getAvg());

	}

}
