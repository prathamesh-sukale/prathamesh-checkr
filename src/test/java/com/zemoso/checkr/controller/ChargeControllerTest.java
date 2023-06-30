package com.zemoso.checkr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.checkr.app.CheckrApplication;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.core.iservice.IChargeService;
import com.zemoso.checkr.model.MCharge;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddOrEditChargeReq;
import com.zemoso.checkr.model.response.GetAllChargesResp;
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
class ChargeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private IChargeService iChargeService;

	@InjectMocks
	private ChargeController ChargeController;

	MCharge mCharge;
	List<MCharge> mCharges;

	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.openMocks(this);

		mCharge = new MCharge();
		mCharge.setJId(1);
		mCharge.setJStatus(1);
		mCharge.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
		mCharge.setSName("Charge1");

		mCharges = new ArrayList<>();
		mCharges.add(mCharge);
	}

	@Test
	void testGetCharges() throws Exception {

		GetAllChargesResp expectedResponse = new GetAllChargesResp();
		expectedResponse.setMCharges(mCharges);
		expectedResponse.setApiResult(ApiResult.ok());

		when(iChargeService.getAll()).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/charges").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetChargesById() throws Exception {

		GetAllChargesResp expectedResponse = new GetAllChargesResp();
		expectedResponse.setMCharges(mCharges);
		expectedResponse.setApiResult(ApiResult.ok());

		when(iChargeService.get(mCharge.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/charges/{id}",mCharge.getJId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testAddCharge() throws Exception {
		AddOrEditChargeReq request = new AddOrEditChargeReq();
		request.setMCharge(mCharge);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iChargeService.add(request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/charges")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testEditCharge() throws Exception {
		mCharge.setSName("Charge2");

		AddOrEditChargeReq request = new AddOrEditChargeReq();
		request.setMCharge(mCharge);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iChargeService.edit(mCharge.getJId(), request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.patch("/api/charges/{id}", mCharge.getJId())
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void testDeleteCharge() throws Exception {

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iChargeService.delete(mCharge.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/charges/{id}", mCharge.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
