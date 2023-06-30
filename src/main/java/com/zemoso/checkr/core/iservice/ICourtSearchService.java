package com.zemoso.checkr.core.iservice;

import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddOrEditCourtSearchReq;
import com.zemoso.checkr.model.response.GetAllCourtSearchesResp;

public interface ICourtSearchService {

    public GetAllCourtSearchesResp getAll();
    public GetAllCourtSearchesResp get(int jId);
    public ApiResponse add(AddOrEditCourtSearchReq req);
    public ApiResponse edit(int jId, AddOrEditCourtSearchReq req);
    public ApiResponse delete(int jId);
}
