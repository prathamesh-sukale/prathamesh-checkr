package com.zemoso.checkr.core.service;

import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.message.MessageConstants;
import com.zemoso.checkr.core.iservice.ICourtSearchService;
import com.zemoso.checkr.db.contract.ICourtSearch;
import com.zemoso.checkr.dto.CCourtSearch;
import com.zemoso.checkr.exception.CourtSearchNotFoundException;
import com.zemoso.checkr.model.MCourtSearch;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddOrEditCourtSearchReq;
import com.zemoso.checkr.model.response.GetAllCourtSearchesResp;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtSearchService extends ApplicationService implements ICourtSearchService {
    protected CourtSearchService() {
        super(CourtSearchService.class);
    }

    @Override
    public GetAllCourtSearchesResp getAll(){
        GetAllCourtSearchesResp resp = new GetAllCourtSearchesResp();
        List<MCourtSearch> mCourtSearches = iDataStore.getCourtSearchRepository().getAll().stream().map(e-> new CCourtSearch(e).createModel()).collect(Collectors.toList());
        resp.setMCourtSearches(mCourtSearches);
        resp.setApiResult(ApiResult.ok());
        return  resp;
    }

    @Override
    public GetAllCourtSearchesResp get(int jId){
        GetAllCourtSearchesResp resp = new GetAllCourtSearchesResp();

        ICourtSearch iCourtSearch = iDataStore.getCourtSearchRepository().get(jId);
        if(iCourtSearch==null){
            throw new CourtSearchNotFoundException();
        }

        List<MCourtSearch> mCourtSearches = Arrays.asList(new CCourtSearch(iCourtSearch).createModel());
        resp.setMCourtSearches(mCourtSearches);

        resp.setApiResult(ApiResult.ok());

        return  resp;
    }

    @Override
    public ApiResponse add(AddOrEditCourtSearchReq req){
        ApiResponse resp = new ApiResponse();
        CCourtSearch cCourtSearch = new CCourtSearch(req.getMCourtSearch());
        ICourtSearch iCourtSearch = iDataStore.getCourtSearchRepository().create(cCourtSearch);
        if(iCourtSearch!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.COURT_SEARCH_SAVE_FAILED));
        }
        return  resp;
    }

    @Override
    public ApiResponse edit(int jId, AddOrEditCourtSearchReq req){
        ApiResponse resp = new ApiResponse();

        ICourtSearch iCourtSearch = iDataStore.getCourtSearchRepository().get(jId);
        if(iCourtSearch==null){
            throw new CourtSearchNotFoundException();
        }

        CCourtSearch cCourtSearch = new CCourtSearch(iCourtSearch);
        cCourtSearch.setSName(req.getMCourtSearch().getSName());

        iCourtSearch = iDataStore.getCourtSearchRepository().update(iCourtSearch,cCourtSearch);

        if(iCourtSearch!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.COURT_SEARCH_UPDATE_FAILED));
        }

        return  resp;
    }

    @Override
    public ApiResponse delete(int jId){
        ApiResponse resp = new ApiResponse();

        ICourtSearch iCourtSearch = iDataStore.getCourtSearchRepository().get(jId);
        if(iCourtSearch==null){
            throw new CourtSearchNotFoundException();
        }

        CCourtSearch cCourtSearch = new CCourtSearch(iCourtSearch);
        cCourtSearch.setJStatus(ERowStatus.DELETED.getJValue());

        iCourtSearch = iDataStore.getCourtSearchRepository().update(iCourtSearch,cCourtSearch);

        if(iCourtSearch!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.COURT_SEARCH_DELETE_FAILED));
        }

        return  resp;
    }
}
