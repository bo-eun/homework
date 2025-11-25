package kr.study.overloading;

public class Employee {
	
	private String myName;
	private String company;
	private String salary;
	
	
	
	/*
	 * 기본 생성자만 있으면 getter와 setter가 필요하다.
	 * 둘 다 있으면 원하는 방법으로 객체의 데이터를 줄 수 있다.
	 * 그래서 보통 기본 생성자와 생성자 오버라이딩으로 사용자 생성자를 만들어 둔다. 
	 * 기본 생성자와 사용자생성자로 데이터를 주는 방법은 EmployeeMain파일에서 확인
	 * */
	
	// 기본 생성자
	public Employee() {}
	
	// 생성자 오버로딩
	// 생성자를 통해서 객체의 데이터를 받는다.
	public Employee(String myName, String company, String salary) {
		this.setMyName(myName);
		this.setCompany(company);
		this.setSalary(salary);
	}

	public String getMyName() {
		return myName;
	}
	
	public void setMyName(String myName) {
		this.myName = myName;
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
	};

}
