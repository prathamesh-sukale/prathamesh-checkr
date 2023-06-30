package com.zemoso.checkr.core.service;

import com.zemoso.checkr.common.enums.EAdjudicationStatus;
import com.zemoso.checkr.common.enums.EReportStatus;
import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.message.MessageConstants;
import com.zemoso.checkr.common.util.DateTimeUtils;
import com.zemoso.checkr.core.iservice.IReportService;
import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.contract.ICharge;
import com.zemoso.checkr.db.contract.IReport;
import com.zemoso.checkr.dto.CCandidate;
import com.zemoso.checkr.dto.CPreAdverseAction;
import com.zemoso.checkr.dto.CPreAdverseCharge;
import com.zemoso.checkr.dto.CReport;
import com.zemoso.checkr.exception.CandidateNotFoundException;
import com.zemoso.checkr.exception.ReportNotFoundException;
import com.zemoso.checkr.model.MPreAdverseAction;
import com.zemoso.checkr.model.MPreAdverseCharge;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddReportReq;
import com.zemoso.checkr.model.response.GetReportResp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService extends ApplicationService implements IReportService {
    protected ReportService() {
        super(ReportService.class);
    }

    @Override
    public GetReportResp get(int jCandidateId){
        GetReportResp resp = new GetReportResp();

        ICandidate iCandidate = iDataStore.getCandidateRepository().get(jCandidateId);
        if(iCandidate==null){
            throw new CandidateNotFoundException();
        }

        IReport iReport = iDataStore.getReportRepository().getByCandidate(jCandidateId);
        if(iReport==null){
            CCandidate cCandidate = new CCandidate(iCandidate);
            cCandidate.setJReportStatus(EReportStatus.NO_ACTION.getJValue());
            cCandidate.setJAdjudicationStatus(EAdjudicationStatus.NO_ACTION.getJValue());


            CReport cReport = new CReport();
            cReport.setICandidate(iCandidate);

            cReport.setIPreAdverseActions(new ArrayList<>());
            iReport = iDataStore.getReportRepository().create(cCandidate,new ArrayList<>(),new ArrayList<>());
        }
        if(iReport==null){
            throw new ReportNotFoundException();
        }

        resp.setMReport(new CReport(iReport).createModel());
        resp.setApiResult(ApiResult.ok());

        return  resp;
    }

    @Override
    public ApiResponse add(AddReportReq req){
        ApiResponse resp = new ApiResponse();

        int jCandidateId = req.getMReport().getMCandidate().getJId();
        ICandidate iCandidate = iDataStore.getCandidateRepository().get(jCandidateId);
        if(iCandidate==null){
            throw new CandidateNotFoundException();
        }

        IReport iReportExist = iDataStore.getReportRepository().getByCandidate(jCandidateId);
        if(iReportExist !=null){
            CReport cReport = new CReport(iReportExist);
            cReport.setJStatus(ERowStatus.DELETED.getJValue());
            iDataStore.getReportRepository().update(iReportExist,cReport,false);
        }

        //Candidate
        CCandidate cCandidate =  new CCandidate(iCandidate);
        cCandidate.setJReportStatus(EReportStatus.NO_ACTION.getJValue());
        cCandidate.setJAdjudicationStatus(EAdjudicationStatus.NO_ACTION.getJValue());

        //pre-adverse-action

        List<CPreAdverseAction> cPreAdverseActions = new ArrayList<>();
        List<CPreAdverseCharge>  cPreAdverseCharges = new ArrayList<>();

        if(req.getMReport().getMPreAdverseActions()!=null && !req.getMReport().getMPreAdverseActions().isEmpty()){

            MPreAdverseAction mPreAdverseAction = req.getMReport().getMPreAdverseActions().get(0);

            if(mPreAdverseAction!=null){
                CPreAdverseAction cPreAdverseAction = new CPreAdverseAction();
                cPreAdverseAction.setDtAction(DateTimeUtils.getDtCurrentInUtc());
                cPreAdverseAction.setJDays(mPreAdverseAction.getJDays());
                cPreAdverseAction.setIPreAdverseCharges(new ArrayList<>());
                cPreAdverseActions.add(cPreAdverseAction);

                for(MPreAdverseCharge mPreAdverseCharge : mPreAdverseAction.getMPreAdverseCharges()){
                    ICharge iCharge = iDataStore.getChargeRepository().get(mPreAdverseCharge.getMCharge().getJId());

                    CPreAdverseCharge cPreAdverseCharge = new CPreAdverseCharge();
                    cPreAdverseCharge.setICharge(iCharge);
                    cPreAdverseCharges.add(cPreAdverseCharge);

                }
            }
        }

        IReport iReport = iDataStore.getReportRepository().create(cCandidate,cPreAdverseActions,cPreAdverseCharges);

        if(iReport!=null){
            resp.setApiResult(ApiResult.ok());
        }
        else {
            resp.setApiResult(ApiResult.failed(MessageConstants.REPORT_SAVE_FAILED));
        }

        return  resp;
    }

    @Override
    public ApiResponse addPreAdverseAction(AddReportReq req){
        ApiResponse resp = new ApiResponse();

        int jCandidateId = req.getMReport().getMCandidate().getJId();
        ICandidate iCandidate = iDataStore.getCandidateRepository().get(jCandidateId);
        if(iCandidate==null){
            throw new CandidateNotFoundException();
        }

        IReport iReport = iDataStore.getReportRepository().getByCandidate(jCandidateId);
        if(iReport == null){
            throw new ReportNotFoundException();
        }

        //Candidate
        CCandidate cCandidate = new CCandidate(iCandidate);
        cCandidate.setJAdjudicationStatus(EAdjudicationStatus.ADVERSE_ACTION.getJValue());

        //Report
        CReport cReport = new CReport(iReport);
        iReport = iDataStore.getReportRepository().update(iReport,cReport,true);

        //pre-adverse-action

        List<CPreAdverseAction> cPreAdverseActions = new ArrayList<>();
        List<CPreAdverseCharge>  cPreAdverseCharges = new ArrayList<>();

        if(req.getMReport().getMPreAdverseActions()!=null && !req.getMReport().getMPreAdverseActions().isEmpty()){

            MPreAdverseAction mPreAdverseAction = req.getMReport().getMPreAdverseActions().get(0);

            if(mPreAdverseAction!=null){
                CPreAdverseAction cPreAdverseAction = new CPreAdverseAction();
                cPreAdverseAction.setDtAction(DateTimeUtils.getDtCurrentInUtc());
                cPreAdverseAction.setJDays(mPreAdverseAction.getJDays());
                cPreAdverseAction.setIPreAdverseCharges(new ArrayList<>());
                cPreAdverseActions.add(cPreAdverseAction);

                for(MPreAdverseCharge mPreAdverseCharge : mPreAdverseAction.getMPreAdverseCharges()){
                    ICharge iCharge = iDataStore.getChargeRepository().get(mPreAdverseCharge.getMCharge().getJId());

                    CPreAdverseCharge cPreAdverseCharge = new CPreAdverseCharge();
                    cPreAdverseCharge.setICharge(iCharge);
                    cPreAdverseCharges.add(cPreAdverseCharge);

                }
            }
        }

        iReport = iDataStore.getReportRepository().addPreAdverseActions(iReport,cCandidate,cPreAdverseActions,cPreAdverseCharges);

        if(iReport!=null){
            resp.setApiResult(ApiResult.ok());
        }
        else {
            resp.setApiResult(ApiResult.failed(MessageConstants.REPORT_SAVE_FAILED));
        }

        return  resp;
    }

    @Override
    public ApiResponse engage(int jCandidateId){
        ApiResponse resp = new ApiResponse();

        ICandidate iCandidate = iDataStore.getCandidateRepository().get(jCandidateId);
        if(iCandidate==null){
            throw new CandidateNotFoundException();
        }

        IReport iReport = iDataStore.getReportRepository().getByCandidate(jCandidateId);
        if(iReport==null){
            throw new ReportNotFoundException();
        }

        CCandidate cCandidate = new CCandidate(iCandidate);
        cCandidate.setJAdjudicationStatus(EAdjudicationStatus.ENGAGE.getJValue());

        iCandidate = iDataStore.getCandidateRepository().update(iCandidate,cCandidate);

        if(iCandidate!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.REPORT_UPDATE_FAILED));
        }

        return  resp;
    }

    @Override
    public ApiResponse delete(int jId){
        ApiResponse resp = new ApiResponse();

        IReport iReport = iDataStore.getReportRepository().get(jId);
        if(iReport==null){
            throw new ReportNotFoundException();
        }

        CReport cReport = new CReport(iReport);
        cReport.setJStatus(ERowStatus.DELETED.getJValue());
        iReport = iDataStore.getReportRepository().update(iReport,cReport,false);

        if(iReport!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.REPORT_DELETE_FAILED));
        }

        return  resp;
    }
}
