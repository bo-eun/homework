package kr.exam.springs.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.exam.springs.user.service.UserService;
import kr.exam.springs.user.vo.Users;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService service;
	
	
	@GetMapping("/list.do")
	public ModelAndView boardUserListView(@RequestParam(name="currentPage", defaultValue = "0") int currentPage) {
		ModelAndView view = new ModelAndView("user/userList");
		view.addObject("currentPage", currentPage);
		
		return view;
	}
	
	@ResponseBody
	@GetMapping("/list/data.do")
	public Map<String, Object> getUsersData(@RequestParam(name="currentPage", defaultValue="0")int currentPage) {
		Map<String, Object> dataMap = new HashMap<>();
		
		try {
			dataMap.put("currentPage", currentPage);
			dataMap = service.getUserList(dataMap);
		} catch(Exception e ) {
			e.printStackTrace();
		}
			
		return dataMap;
	}
	
	
	@ResponseBody
	@GetMapping("/search.do")
	public Map<String, Object> searchUser(@RequestParam(name="type") String type,
											@RequestParam(name="searchText") String searchText) {

		Map<String, Object> map = new HashMap<>();
		
		try {
			// 검색 후에는 무조건 첫페이지
			map.put("currentPage", 0);
			map.put("type", type);
			map.put("searchText", searchText);
			map = service.getUserList(map);
		} catch(Exception e ) {
			e.printStackTrace();
		}
			
		return map;
	}
	
	
	// 주소가 같아도 method가 달라 다른 경로로 인식함
	@GetMapping("/login.do")
	public ModelAndView loginView() {
		ModelAndView view = new ModelAndView("user/loginForm");
		
		return view;
	}
	
	@PostMapping("/login.do")
	@ResponseBody // controller에서 ajax 콜 응답을 위해 사용
	public Map<String, Object> login(@RequestParam("userId") String userId,
									@RequestParam("passwd") String passwd,
									HttpServletRequest req) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("passwd", passwd);
		
		int result = 0;
		
		try {
			
			result = service.userLogin(map, req);
			
			if(result < 0) {
				throw new Exception("로그인 실패");
			}
			
			// 로그인 성공 시 200
			map.put("resultCode", 200);
			
		} catch(Exception e) {
			map.put("resultCode", 500);
			e.printStackTrace();
		}
		
		return map;
	}
	
	// 회원가입 페이지 연결
	@GetMapping("/register.do")
	public ModelAndView registView() {
		return new ModelAndView("user/register");
	}
	
	
	// 회원가입
	@PostMapping("/register.do")
	@ResponseBody
	public Map<String, Object> addUser(Users.UserInfo user) {
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			service.addUser(user);
			resultMap.put("resultCode", 200);
		} catch(Exception e) {
			resultMap.put("resultCode", 500);
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	
}
