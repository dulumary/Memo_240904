package com.marondal.memo.user.repository;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.marondal.memo.common.MD5HashingEncoder;
import com.marondal.memo.user.domain.User;

@SpringBootTest
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void selectUserListTest() {
		
		// given
		List<String> loginIdList = new ArrayList<>();
		loginIdList.add("dulumary");
		loginIdList.add("asdf");
		loginIdList.add("marondal");
		
		// when
		
		List<User> userList = userRepository.selectUserList(loginIdList);
		int count = userList.size();
		// then
		
		assertNotEquals(count, 0);
		
		
	}

	@Test
	public void selectUserTest() {
		// given
		
		String loginId = "dulumary";
		String password = "asdf";
		
		
		// when
		
		User user1 = userRepository.selectUser(loginId, MD5HashingEncoder.encode(password));
		User user2 = userRepository.selectUser(loginId, null);
		
		// then
		assertNotNull(user1);
		assertNotNull(user2);
		
	}
	
}
