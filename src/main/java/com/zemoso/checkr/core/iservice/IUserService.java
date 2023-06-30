package com.zemoso.checkr.core.iservice;

import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.request.AddOrEditUserReq;
import com.zemoso.checkr.model.response.GetAllUsersResp;

public interface IUserService {

    public GetAllUsersResp getAll();
    public GetAllUsersResp get(int jId);
    public ApiResponse add(AddOrEditUserReq req);
    public ApiResponse edit(int jId, AddOrEditUserReq req);
    public ApiResponse delete(int jId);
}
