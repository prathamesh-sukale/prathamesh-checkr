package com.zemoso.checkr.core.service;

import com.zemoso.checkr.app.CheckrApplication;
import com.zemoso.checkr.core.iservice.IUserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CheckrApplication.class})
class UserServiceTest {

	@Autowired
	IUserService iUserService;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertThat(iUserService).isNotNull();
	}

}
