package com.marondal.memo.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.marondal.memo.user.domain.User;

import jakarta.transaction.Transactional;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	@Transactional
	public void joinLoginTest() {
		
		// 회원가입 과정 진행
		// 회원가입 한 사용자 정보로 로그인 수행
		
		// given  - 준비
		String loginId = "asdf";
		String password = "asdf";
		String name = "김인규";
		String email = "asdf@gmail.com";
		
//		// when - 실행
		int count = userService.addUser(loginId, password, name, email);
		User user = userService.getUser(loginId, password);
//		
//		logger.warn("로그인 결과 " + loginId);
//		
		// then - 검증
		assertEquals(count, 1);
		assertNotNull(user);
		assertEquals(user.getEmail(), email);
		
	}

}
