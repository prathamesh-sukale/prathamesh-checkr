package com.zemoso.checkr.core.iservice;

import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddReportReq;
import com.zemoso.checkr.model.response.GetReportResp;

public interface IReportService {

    public GetReportResp get(int jId);
    public ApiResponse add(AddReportReq req);
    public ApiResponse delete(int jId);
    public ApiResponse engage(int jCandidateId);
    public ApiResponse addPreAdverseAction(AddReportReq req);
}
