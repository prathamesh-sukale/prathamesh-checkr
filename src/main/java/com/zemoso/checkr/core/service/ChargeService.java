package com.zemoso.checkr.core.service;

import com.zemoso.checkr.common.enums.ERowStatus;
import com.zemoso.checkr.common.message.MessageConstants;
import com.zemoso.checkr.core.iservice.IChargeService;
import com.zemoso.checkr.db.contract.ICharge;
import com.zemoso.checkr.dto.CCharge;
import com.zemoso.checkr.exception.ChargeNotFoundException;
import com.zemoso.checkr.model.MCharge;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import com.zemoso.checkr.model.request.AddOrEditChargeReq;
import com.zemoso.checkr.model.response.GetAllChargesResp;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargeService extends ApplicationService implements IChargeService {
    protected ChargeService() {
        super(ChargeService.class);
    }

    @Override
    public GetAllChargesResp getAll(){
        GetAllChargesResp resp = new GetAllChargesResp();
        List<MCharge> mCharges = iDataStore.getChargeRepository().getAll().stream().map(e-> new CCharge(e).createModel()).collect(Collectors.toList());
        resp.setMCharges(mCharges);

        resp.setApiResult(ApiResult.ok());
        return  resp;
    }

    @Override
    public GetAllChargesResp get(int jId){
        GetAllChargesResp resp = new GetAllChargesResp();

        ICharge iCharge = iDataStore.getChargeRepository().get(jId);
        if(iCharge==null){
            throw  new ChargeNotFoundException();
        }
        List<MCharge> mCharges = Arrays.asList(new CCharge(iCharge).createModel());
        resp.setMCharges(mCharges);

        resp.setApiResult(ApiResult.ok());

        return  resp;
    }

    @Override
    public ApiResponse add(AddOrEditChargeReq req){
        ApiResponse resp = new ApiResponse();
        CCharge cCharge = new CCharge(req.getMCharge());
        ICharge iCharge = iDataStore.getChargeRepository().create(cCharge);
        if(iCharge!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.CHARGE_SAVE_FAILED));
        }
        return  resp;
    }

    @Override
    public ApiResponse edit(int jId, AddOrEditChargeReq req){
        ApiResponse resp = new ApiResponse();

        ICharge iCharge = iDataStore.getChargeRepository().get(jId);
        if(iCharge==null){
            throw new ChargeNotFoundException();
        }

        CCharge cCharge = new CCharge(iCharge);
        cCharge.setSName(req.getMCharge().getSName());

        iCharge = iDataStore.getChargeRepository().update(iCharge,cCharge);

        if(iCharge!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.CHARGE_UPDATE_FAILED));
        }

        return  resp;
    }

    @Override
    public ApiResponse delete(int jId){
        ApiResponse resp = new ApiResponse();

        ICharge iCharge = iDataStore.getChargeRepository().get(jId);
        if(iCharge==null){
            throw new ChargeNotFoundException();
        }

        CCharge cCharge = new CCharge(iCharge);
        cCharge.setJStatus(ERowStatus.DELETED.getJValue());

        iCharge = iDataStore.getChargeRepository().update(iCharge,cCharge);

        if(iCharge!=null){
            resp.setApiResult(ApiResult.ok());
        }else{
            resp.setApiResult(ApiResult.failed(MessageConstants.CHARGE_DELETE_FAILED));
        }

        return  resp;
    }
}
