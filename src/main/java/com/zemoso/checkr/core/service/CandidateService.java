package com.zemoso.checkr.core.service;

import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.core.iservice.ICandidateService;
import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.contract.IUser;
import com.zemoso.checkr.dto.CCandidate;
import com.zemoso.checkr.dto.CUser;
import com.zemoso.checkr.exception.CandidateNotFoundException;
import com.zemoso.checkr.model.MCandidate;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddOrEditCandidateReq;
import com.zemoso.checkr.model.request.GetAllCandidatesReq;
import com.zemoso.checkr.model.response.GetAllCandidatesResp;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService extends ApplicationService implements ICandidateService {

    protected CandidateService() {
        super(CandidateService.class);
    }

    @Override
    public GetAllCandidatesResp getAll(GetAllCandidatesReq req){
        GetAllCandidatesResp resp = new GetAllCandidatesResp();

        int jAuthUserId =req.getMUser().getJId();
        IUser iUser = iDataStore.getUserRepository().get(jAuthUserId);
        if(iUser!=null){
            List<ICandidate> iCandidates = iDataStore.getUserRepository().get(jAuthUserId).getICandidates();
            String sSearch = req.getSSearch();
            if(sSearch!=null && !sSearch.isBlank()){
                iCandidates = iCandidates.stream()
                        .filter(o-> o.getSFirstName().concat(" ").concat(o.getSLastName()).contains(sSearch))
                        .collect(Collectors.toList());
            }

            if(req.getJStatuses()!=null && !req.getJStatuses().isEmpty()){
                iCandidates = iCandidates.stream().filter(e-> req.getJStatuses().stream().anyMatch(a->a==e.getJReportStatus())).collect(Collectors.toList());

            }

            if(req.getJAdjudications()!=null && !req.getJAdjudications().isEmpty()){
                iCandidates = iCandidates.stream().filter(e-> req.getJAdjudications().stream().anyMatch(a->a==e.getJAdjudicationStatus())).collect(Collectors.toList());
            }

            resp.setMCandidates(iCandidates.stream().map(e-> new CCandidate(e).createModel()).collect(Collectors.toList()));
            resp.setApiResult(ApiResult.ok());
        }

        return  resp;
    }

    @Override
    public GetAllCandidatesResp get(int jId){
        GetAllCandidatesResp resp = new GetAllCandidatesResp();

        ICandidate iCandidate = iDataStore.getCandidateRepository().get(jId);
        if(iCandidate==null){
            throw  new CandidateNotFoundException();
        }

        List<MCandidate> mCandidates = Arrays.asList(new CCandidate(iCandidate).createModel());
        resp.setMCandidates(mCandidates);

        resp.setApiResult(ApiResult.ok());

        return  resp;
    }

    @Override
    public ApiResponse add(AddOrEditCandidateReq req){
        ApiResponse resp = new ApiResponse();
        IUser iUser = iDataStore.getUserRepository().get(req.getMUser().getJId());
        if(iUser==null){
            resp.setApiResult(ApiResult.failed("Unable to find User"));
        }
        CCandidate cCandidate = new CCandidate(req.getMCandidate());
        iUser = iDataStore.getUserRepository().update(iUser,new CUser(iUser),cCandidate);
        if(iUser!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed("Unable to add Candidate"));
        }

        return  resp;
    }

    @Override
    public ApiResponse edit(int jId, AddOrEditCandidateReq req){
        ApiResponse resp = new ApiResponse();

        ICandidate iCandidate = iDataStore.getCandidateRepository().get(jId);

        if(iCandidate==null){
            throw  new CandidateNotFoundException();
        }

        CCandidate cCandidate = new CCandidate(iCandidate);
        cCandidate.assignModel(req.getMCandidate());

        iCandidate = iDataStore.getCandidateRepository().update(iCandidate,cCandidate);

        if(iCandidate!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed("Unable to update Candidate"));
        }

        return  resp;
    }

    @Override
    public ApiResponse delete(int jId){
        ApiResponse resp = new ApiResponse();

        ICandidate iCandidate = iDataStore.getCandidateRepository().get(jId);
        if(iCandidate==null){
            throw  new CandidateNotFoundException();
        }

        CCandidate cCandidate = new CCandidate(iCandidate);
        cCandidate.setJStatus(ERowStatus.DELETED.getJValue());

        iCandidate = iDataStore.getCandidateRepository().update(iCandidate,cCandidate);

        if(iCandidate!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed("Unable to delete Candidate"));
        }

        return  resp;
    }
}
