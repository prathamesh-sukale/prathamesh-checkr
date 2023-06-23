package com.zemoso.checkr.service;

import com.zemoso.checkr.app.CheckrApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CheckrApplication.class})
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertThat(userService.getTestData()).isNotNull();
	}

}
