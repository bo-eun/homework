package kr.exam.springs.user.vo;
import lombok.Data;

public class Users {
	
	// 유저 리스트용
	@Data
	public static class UserInfo{
		private String userId;
		private String passwd;
		private String userName;
		private String birth;
		private String phone;
		private String email;
	}
	
	
	@Data
	// 패스워드는 넣지 않는다.
	public static class LoginUser {
		private String userId;
		private String userName;
		private String birth;
	}
}
