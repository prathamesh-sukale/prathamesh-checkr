package com.zemoso.checkr.core.iservice;

import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddOrEditChargeReq;
import com.zemoso.checkr.model.response.GetAllChargesResp;

public interface IChargeService {

    public GetAllChargesResp getAll();
    public GetAllChargesResp get(int jId);
    public ApiResponse add(AddOrEditChargeReq req);
    public ApiResponse edit(int jId, AddOrEditChargeReq req);
    public ApiResponse delete(int jId);
}
