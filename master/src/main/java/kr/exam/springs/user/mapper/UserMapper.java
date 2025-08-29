package kr.exam.springs.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.exam.springs.user.vo.Users;

@Mapper
public interface UserMapper {
	
	// 유저 전체 개수
	int getUserTotal();
	// 모든 유저 정보 가져오기
	List<Users.UserInfo> getUsers(Map<String, Object> param);
	// 회원가입
	int addUser(Users.UserInfo param);
	// 아이디 체크
	int checkId(@Param("userId") String userId);
	
	/**
	 * 로그인 사용자 가져오기
	 * @param param
	 * @return
	 */
	Users.LoginUser getLoginUser(Map<String, Object> param);
}
