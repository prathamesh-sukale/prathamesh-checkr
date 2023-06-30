package com.zemoso.checkr.core.service;

import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.message.MessageConstants;
import com.zemoso.checkr.core.iservice.IUserService;
import com.zemoso.checkr.db.contract.IUser;
import com.zemoso.checkr.dto.CUser;
import com.zemoso.checkr.exception.UserNotFoundException;
import com.zemoso.checkr.model.MUser;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddOrEditUserReq;
import com.zemoso.checkr.model.response.GetAllUsersResp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends ApplicationService implements IUserService {
    protected UserService() {
        super(UserService.class);
    }

    @Override
    @Transactional
    public GetAllUsersResp getAll(){
        GetAllUsersResp resp = new GetAllUsersResp();
        List<MUser> mUsers = iDataStore.getUserRepository().getAll().stream().map(e-> new CUser(e).createModel()).collect(Collectors.toList());
        resp.setMUsers(mUsers);
        resp.setApiResult(ApiResult.ok());
        return  resp;
    }

    @Override
    @Transactional
    public GetAllUsersResp get(int jId){
        GetAllUsersResp resp = new GetAllUsersResp();

        IUser iUser = iDataStore.getUserRepository().get(jId);
        if(iUser==null){
            throw new UserNotFoundException();
        }

        List<MUser> mUsers = Arrays.asList(new CUser(iUser).createModel());
        resp.setMUsers(mUsers);

        resp.setApiResult(ApiResult.ok());

        return  resp;
    }

    @Override
    @Transactional
    public ApiResponse add(AddOrEditUserReq req){
        ApiResponse resp = new ApiResponse();
        CUser cUser = new CUser(req.getMUser());
        IUser iUser = iDataStore.getUserRepository().create(cUser);
        if(iUser!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.USER_SAVE_FAILED));
        }
        return  resp;
    }

    @Override
    @Transactional
    public ApiResponse edit(int jId, AddOrEditUserReq req){
        ApiResponse resp = new ApiResponse();

        IUser iUser = iDataStore.getUserRepository().get(jId);
        if(iUser==null){
            throw new UserNotFoundException();
        }

        CUser cUser = new CUser(iUser);
        cUser.setSPassword(req.getMUser().getSPassword());

        iUser = iDataStore.getUserRepository().update(iUser,cUser,null);

        if(iUser!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.USER_UPDATE_FAILED));
        }

        return  resp;
    }

    @Override
    @Transactional
    public ApiResponse delete(int jId){
        ApiResponse resp = new ApiResponse();

        IUser iUser = iDataStore.getUserRepository().get(jId);
        if(iUser==null){
            throw new UserNotFoundException();
        }

        CUser cUser = new CUser(iUser);
        cUser.setJStatus(ERowStatus.DELETED.getJValue());
        iUser = iDataStore.getUserRepository().update(iUser,cUser,null);

        if(iUser!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.USER_DELETE_FAILED));
        }

        return  resp;
    }
}
