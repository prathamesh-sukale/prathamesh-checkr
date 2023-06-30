package com.zemoso.checkr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.checkr.app.CheckrApplication;
import com.zemoso.checkr.common.enums.ERole;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.core.iservice.IUserService;
import com.zemoso.checkr.model.MUser;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddOrEditUserReq;
import com.zemoso.checkr.model.response.GetAllUsersResp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CheckrApplication.class})
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private IUserService iUserService;

	@InjectMocks
	private UserController userController;

	MUser mUser;
	List<MUser> mUsers;

	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.openMocks(this);

		mUser = new MUser();
		mUser.setJId(1);
		mUser.setJStatus(1);
		mUser.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
		mUser.setJRole(ERole.ADMIN.getJValue());
		mUser.setSEmail("test@test.com");
		mUser.setSPassword("123456");

		mUsers = new ArrayList<>();
		mUsers.add(mUser);
	}

	@Test
	void testGetUsers() throws Exception {

		GetAllUsersResp expectedResponse = new GetAllUsersResp();
		expectedResponse.setMUsers(mUsers);
		expectedResponse.setApiResult(ApiResult.ok());

		when(iUserService.getAll()).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetUsersById() throws Exception {

		GetAllUsersResp expectedResponse = new GetAllUsersResp();
		expectedResponse.setMUsers(mUsers);
		expectedResponse.setApiResult(ApiResult.ok());

		when(iUserService.get(mUser.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}",mUser.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testAddUser() throws Exception {

		AddOrEditUserReq request = new AddOrEditUserReq();
		request.setMUser(mUser);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iUserService.add(request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testEditUser() throws Exception {
		mUser.setSPassword("12345678");

		AddOrEditUserReq request = new AddOrEditUserReq();
		request.setMUser(mUser);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iUserService.edit(mUser.getJId(), request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.patch("/api/users/{id}", mUser.getJId())
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void testDeleteUser() throws Exception {

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iUserService.delete(mUser.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", mUser.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
