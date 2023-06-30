package com.zemoso.checkr.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.checkr.app.CheckrApplication;
import com.zemoso.checkr.common.enums.ERole;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.controller.UserController;
import com.zemoso.checkr.core.iservice.IUserService;
import com.zemoso.checkr.db.contract.IUser;
import com.zemoso.checkr.db.datastore.DataStore;
import com.zemoso.checkr.db.datastore.IDataStore;
import com.zemoso.checkr.db.domain.TUser;
import com.zemoso.checkr.db.irepository.IUserRepository;
import com.zemoso.checkr.db.repository.UserRepository;
import com.zemoso.checkr.db.wrapper.OUser;
import com.zemoso.checkr.model.MUser;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddOrEditUserReq;
import com.zemoso.checkr.model.response.GetAllUsersResp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CheckrApplication.class})
@AutoConfigureMockMvc
class UserServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Mock
	private Logger logger= LogManager.getLogger(UserService.class);

	@Mock
	private DataStore iDataStore;

	@Mock
	UserRepository userRepository = new UserRepository();

	@InjectMocks
	private UserService iUserService;
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

		List<IUser> iUsers = Arrays.asList(OUser.create(new TUser()),OUser.create(new TUser()));

		GetAllUsersResp expectedResponse = new GetAllUsersResp();
		expectedResponse.setMUsers(mUsers);
		expectedResponse.setApiResult(ApiResult.ok());

		//when(iDataStore.getUserRepository()).thenReturn(new UserRepository());
		when(iDataStore.getUserRepository().getAll()).thenReturn(iUsers);

		GetAllUsersResp response = iUserService.getAll();

		assertThat(response).isNotNull();
		Assertions.assertEquals(response.getApiResult().getJCode(),expectedResponse.getApiResult().getJCode());
		assertThat(response.getMUsers()).isNotEmpty();

	}

	/*@Test
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

	}*/

	@Test
	void testDeleteUser() throws Exception {

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		/*when(iUserService.delete(mUser.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", mUser.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());*/

		ApiResponse response = iUserService.delete(mUser.getJId());

		assertThat(response).isNotNull();
		Assertions.assertEquals(response.getApiResult().getJCode(),expectedResponse.getApiResult().getJCode());

	}

}