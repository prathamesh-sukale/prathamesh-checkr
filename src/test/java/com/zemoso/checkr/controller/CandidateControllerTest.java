package com.zemoso.checkr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.checkr.app.CheckrApplication;
import com.zemoso.checkr.common.enums.EAdjudicationStatus;
import com.zemoso.checkr.common.enums.EReportStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.core.iservice.ICandidateService;
import com.zemoso.checkr.model.MCandidate;
import com.zemoso.checkr.model.MUser;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddOrEditCandidateReq;
import com.zemoso.checkr.model.request.GetAllCandidatesReq;
import com.zemoso.checkr.model.response.GetAllCandidatesResp;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CheckrApplication.class})
@AutoConfigureMockMvc
class CandidateControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private ICandidateService iCandidateService;

	@InjectMocks
	private CandidateController CandidateController;

	MCandidate mCandidate;
	List<MCandidate> mCandidates;

	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.openMocks(this);

		mCandidate = new MCandidate();
		mCandidate.setJId(1);
		mCandidate.setJStatus(1);
		mCandidate.setDtCreate(DateTimeUtils.getDtCurrentInUtc());

		mCandidate.setSEmail("test@test.com");
		mCandidate.setSFirstName("Prathamesh");
		mCandidate.setSLastName("Sukale");
		mCandidate.setSLocation("Ratnagiri");
		mCandidate.setDtDob(DateTimeUtils.getDtCurrentInUtc().toLocalDate());
		mCandidate.setJReportStatus(EReportStatus.CLEAR.getJValue());
		mCandidate.setJAdjudicationStatus(EAdjudicationStatus.ENGAGE.getJValue());

		mCandidates = new ArrayList<>();
		mCandidates.add(mCandidate);
	}

	@Test
	void testGetCandidates() throws Exception {

		GetAllCandidatesReq request = new GetAllCandidatesReq();
		request.setMUser(new MUser());
		request.getMUser().setJId(1);
		request.setSSearch("cap");
		request.setJStatuses(Arrays.asList(1,2));
		request.setJAdjudications(Arrays.asList(1,2));

		GetAllCandidatesResp expectedResponse = new GetAllCandidatesResp();
		expectedResponse.setMCandidates(mCandidates);
		expectedResponse.setApiResult(ApiResult.ok());

		when(iCandidateService.getAll(request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/candidates")
						.param("userId","1")
						.param("search","cap")
						.param("adjudications","1,2")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetCandidatesById() throws Exception {

		GetAllCandidatesResp expectedResponse = new GetAllCandidatesResp();
		expectedResponse.setMCandidates(mCandidates);
		expectedResponse.setApiResult(ApiResult.ok());

		when(iCandidateService.get(mCandidate.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/candidates/{id}",mCandidate.getJId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testAddCandidate() throws Exception {
		AddOrEditCandidateReq request = new AddOrEditCandidateReq();
		request.setMCandidate(mCandidate);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iCandidateService.add(request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/candidates")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testEditCandidate() throws Exception {
		mCandidate.setSLocation("Ratnagiri");

		AddOrEditCandidateReq request = new AddOrEditCandidateReq();
		request.setMCandidate(mCandidate);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iCandidateService.edit(mCandidate.getJId(), request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.patch("/api/candidates/{id}", mCandidate.getJId())
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void testDeleteCandidate() throws Exception {

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iCandidateService.delete(mCandidate.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/candidates/{id}", mCandidate.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
