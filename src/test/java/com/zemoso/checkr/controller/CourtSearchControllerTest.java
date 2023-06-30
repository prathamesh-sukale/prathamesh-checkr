package com.zemoso.checkr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.checkr.app.CheckrApplication;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.core.iservice.ICourtSearchService;
import com.zemoso.checkr.model.MCourtSearch;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddOrEditCourtSearchReq;
import com.zemoso.checkr.model.response.GetAllCourtSearchesResp;
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
class CourtSearchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private ICourtSearchService iCourtSearchService;

	@InjectMocks
	private CourtSearchController CourtSearchController;

	MCourtSearch mCourtSearch;
	List<MCourtSearch> mCourtSearches;

	@BeforeEach
	void setup() throws Exception {
		MockitoAnnotations.openMocks(this);

		mCourtSearch = new MCourtSearch();
		mCourtSearch.setJId(1);
		mCourtSearch.setJStatus(1);
		mCourtSearch.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
		mCourtSearch.setSName("CourtSearch1");

		mCourtSearches = new ArrayList<>();
		mCourtSearches.add(mCourtSearch);
	}

	@Test
	void testGetCourtSearches() throws Exception {

		GetAllCourtSearchesResp expectedResponse = new GetAllCourtSearchesResp();
		expectedResponse.setMCourtSearches(mCourtSearches);

		when(iCourtSearchService.getAll()).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/court-searches")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetCourtSearchesById() throws Exception {

		GetAllCourtSearchesResp expectedResponse = new GetAllCourtSearchesResp();
		expectedResponse.setMCourtSearches(mCourtSearches);
		expectedResponse.setApiResult(ApiResult.ok());

		when(iCourtSearchService.get(mCourtSearch.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/court-searches/{id}",mCourtSearch.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testAddCourtSearch() throws Exception {
		AddOrEditCourtSearchReq request = new AddOrEditCourtSearchReq();
		request.setMCourtSearch(mCourtSearch);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iCourtSearchService.add(request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/court-searches")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testEditCourtSearch() throws Exception {
		mCourtSearch.setSName("CourtSearch2");

		AddOrEditCourtSearchReq request = new AddOrEditCourtSearchReq();
		request.setMCourtSearch(mCourtSearch);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iCourtSearchService.edit(mCourtSearch.getJId(), request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.patch("/api/court-searches/{id}", mCourtSearch.getJId())
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void testDeleteCourtSearch() throws Exception {

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iCourtSearchService.delete(mCourtSearch.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/court-searches/{id}", mCourtSearch.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
