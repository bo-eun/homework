package kr.study.constructor;

public class Employee extends Person {
	
	private String company;
	private String salary;
	
	// 기본 생성자함수
	public Employee() {
		// 안보이지만 자동으로 부모클래스 기본 생성자 호출됨 super();
	}; 
	
	public Employee(String myName, String gender, String company, String salary) {
		// 부모클래스 생성자에 매개변수로 넣을 수 있는 애들
		// 1. 생성자가 가진 매개변수
		// 2. static 변수 및 메서드 return 값
		super(myName, gender); // 부모클래스 생성자 호출
		
		this.setCompany(company);
		this.setSalary(salary);
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
	public String toString() {
		String str = "이름 : " + this.getMyName() + ", ";
		str += "성별 : " + this.getGender() + ", ";
		str += "회사 : " + this.getCompany() + ", ";
		str += "월급 : " + this.getSalary();
		
		return str;
	}
	
}
