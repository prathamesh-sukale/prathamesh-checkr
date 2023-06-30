package com.zemoso.checkr.core.iservice;

import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddOrEditCandidateReq;
import com.zemoso.checkr.model.request.GetAllCandidatesReq;
import com.zemoso.checkr.model.response.GetAllCandidatesResp;

public interface ICandidateService {

    public GetAllCandidatesResp getAll(GetAllCandidatesReq req);
    public GetAllCandidatesResp get(int jId);
    public ApiResponse add(AddOrEditCandidateReq req);
    public ApiResponse edit(int jId, AddOrEditCandidateReq req);
    public ApiResponse delete(int jId);
}
