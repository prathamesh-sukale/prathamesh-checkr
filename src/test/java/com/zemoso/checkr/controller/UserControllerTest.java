package com.zemoso.checkr.controller;

import com.zemoso.checkr.app.CheckrApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;

@SpringBootTest(classes = {CheckrApplication.class})
class UserControllerTest {

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertThat(userController.test()).isNotNull();
	}

}
