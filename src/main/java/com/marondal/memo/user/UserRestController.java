package com.marondal.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marondal.memo.user.domain.User;
import com.marondal.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/user")
@Validated
public class UserRestController {
	
	private UserService userService;
	
//	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Map<String, String> join(
			@Size(min=4, max=8, message="아이디 길이를 확인하세요") @RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @Email(message="이메일 형식이 맞지 않습니다") @RequestParam("email") String email) {
		
		
		int count = userService.addUser(loginId, password, name, email);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request) {
		
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null) {
			resultMap.put("result", "success");
			
			// HttpServletRequest 객체로 부터 얻어 온다. 
			// 특정 클라이언트에서 사용될 session을 의미 
			HttpSession session = request.getSession();
			// key, value 형태의 데이터 관리 
			// 로그인이 되었다는 정보를 저장 
			// 어떤 페이지에서든 해당 정보를 사용할 수 있다. 
			// 로그인된 사용자 정보를 저장해서 사용자 정보 기반의 페이지를 구성할 수 있다. 
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}

}
