package kr.exam.springs.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.stereotype.Service;

import kr.exam.springs.common.page.PageVO;
import kr.exam.springs.user.mapper.UserMapper;
import kr.exam.springs.user.vo.Users;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper mapper;
	
	/* 유저 정보 얻기 */
	public Map<String, Object> getUserList(Map<String, Object> param) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		int currentPage = (int) param.get("currentPage");
		
		// 전체 리스트 개수
		int total = mapper.getUserTotal();
		
		// 페이징 객체를 호출
		PageVO pageVO = new PageVO();
		pageVO.setData(currentPage, total);
		
		List<Users.UserInfo> userList = new ArrayList<>();
		
		if(total > 0) {
			// 페이지 처리를 위한 값
			param.put("offSet", pageVO.getOffSet());
			param.put("count", pageVO.getCount());
			
			// 페이지에 보여줄 리스트
			userList = mapper.getUsers(param);
		}
		
		map.put("currentPage", currentPage);
		map.put("userList", userList);
		map.put("total", total);
		map.put("pageHTML", pageVO.pageHTML());
		
		return map;
	}
	
	
	/**
	 * 로그인 성공 여부
	 */
	public int userLogin(Map<String, Object> param, HttpServletRequest req) throws Exception {
		int result = 0;
		
		// 사용자 정보 가져오기
		Users.LoginUser user = mapper.getLoginUser(param);
		if(user != null) {
			result = 1;
			// 세션에 저장
			// 세션 꺼내기
			HttpSession session = req.getSession();
			session.setAttribute("userInfo", user);
		}
				
		return result;
	}
	
	// 회원가입
	public int addUser(Users.UserInfo user) throws Exception {
		
		int existsId = mapper.checkId(user.getUserId());
		
		if(existsId > 0) {
			throw new DuplicateMemberException("이미 사용중인 아이디입니다.");
		}
		
		int result = mapper.addUser(user);
		
		return result;
	}
}
