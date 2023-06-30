package com.zemoso.checkr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.checkr.app.CheckrApplication;
import com.zemoso.checkr.common.enums.EAdjudicationStatus;
import com.zemoso.checkr.common.enums.ECourtSearchStatus;
import com.zemoso.checkr.common.enums.EReportStatus;
import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.core.iservice.IReportService;
import com.zemoso.checkr.model.*;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddReportReq;
import com.zemoso.checkr.model.response.GetReportResp;
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
class ReportControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private IReportService iReportService;

	@InjectMocks
	private ReportController ReportController;

	MCandidate mCandidate;
	MReport mReport;
	List<MReport> mReports;

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

		mReport = new MReport();
		mReport.setJId(1);
		mReport.setJStatus(1);
		mReport.setDtCreate(DateTimeUtils.getDtCurrentInUtc());

		mReport.setMCandidate(mCandidate);
		mReport.setMReportCourtSearches(new ArrayList<>());

		//1
		MCourtSearch mCourtSearch1 = new MCourtSearch();
		mCourtSearch1.setJId(1);
		mCourtSearch1.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
		mCourtSearch1.setJStatus(ERowStatus.ACTIVE.getJValue());
		mCourtSearch1.setSName("Court Search 1");

		MReportCourtSearch mReportCourtSearch1 = new MReportCourtSearch();
		mReportCourtSearch1.setJId(1);
		mReportCourtSearch1.setMCourtSearch(mCourtSearch1);
		mReportCourtSearch1.setJCourtSearchStatus(ECourtSearchStatus.CLEAR.getJValue());
		mReportCourtSearch1.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
		mReportCourtSearch1.setJStatus(ERowStatus.ACTIVE.getJValue());
		mReport.getMReportCourtSearches().add(mReportCourtSearch1);

		//2
		MCourtSearch mCourtSearch2 = new MCourtSearch();
		mCourtSearch2.setJId(1);
		mCourtSearch2.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
		mCourtSearch2.setJStatus(ERowStatus.ACTIVE.getJValue());
		mCourtSearch2.setSName("Court Search 2");

		MReportCourtSearch mReportCourtSearch2 = new MReportCourtSearch();
		mReportCourtSearch2.setJId(1);
		mReportCourtSearch2.setMCourtSearch(mCourtSearch2);
		mReportCourtSearch2.setJCourtSearchStatus(ECourtSearchStatus.CONSIDER.getJValue());
		mReportCourtSearch2.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
		mReportCourtSearch2.setJStatus(ERowStatus.ACTIVE.getJValue());
		mReport.getMReportCourtSearches().add(mReportCourtSearch2);

		mReport.setMPreAdverseActions(new ArrayList<>());

		MCharge mCharge = new MCharge();
		mCharge.setJId(1);
		mCharge.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
		mCharge.setJStatus(ERowStatus.ACTIVE.getJValue());
		mCharge.setSName("Charge1");

		MPreAdverseCharge mPreAdverseCharge = new MPreAdverseCharge();
		mPreAdverseCharge.setJId(1);
		mPreAdverseCharge.setDtCreate(DateTimeUtils.getDtCurrentInUtc());
		mPreAdverseCharge.setJStatus(ERowStatus.ACTIVE.getJValue());
		mPreAdverseCharge.setJChargeStatus(ERowStatus.ACTIVE.getJValue());
		mPreAdverseCharge.setMCharge(mCharge);

		MPreAdverseAction mPreAdverseAction = new MPreAdverseAction();
		mPreAdverseAction.setMPreAdverseCharges(new ArrayList<>());
		mPreAdverseAction.getMPreAdverseCharges().add(mPreAdverseCharge);
		mPreAdverseAction.setDtAction(DateTimeUtils.getDtCurrentInUtc());
		mPreAdverseAction.setJDays(7);

		mReport.getMPreAdverseActions().add(mPreAdverseAction);

		mReports = new ArrayList<>();
		mReports.add(mReport);
	}

	@Test
	void testGetReportById() throws Exception {

		GetReportResp expectedResponse = new GetReportResp();
		expectedResponse.setMReport(mReport);
		expectedResponse.setApiResult(ApiResult.ok());

		when(iReportService.get(mCandidate.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/reports/candidate/{id}",mCandidate.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testAddReport() throws Exception {
		AddReportReq request = new AddReportReq();
		request.setMReport(mReport);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iReportService.add(request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/reports")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testEngageReport() throws Exception {

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iReportService.engage(mCandidate.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.patch("/api/reports/candidate/{jId}/engage", mCandidate.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void testAddPreAdverseActionReport() throws Exception {

		AddReportReq request = new AddReportReq();
		request.setMReport(mReport);

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iReportService.addPreAdverseAction(request)).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/reports/pre-adverse-action")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testDeleteReport() throws Exception {

		ApiResponse expectedResponse = new ApiResponse();
		expectedResponse.setApiResult(ApiResult.ok());

		when(iReportService.delete(mReport.getJId())).thenReturn(expectedResponse);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/reports/{id}", mReport.getJId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
