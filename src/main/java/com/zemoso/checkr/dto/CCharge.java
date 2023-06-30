package com.zemoso.checkr.dto;

import com.zemoso.checkr.db.contract.ICharge;
import com.zemoso.checkr.model.MCharge;

public class CCharge extends CBase {

    private String sName;

    public CCharge(){}

    public CCharge(ICharge iCharge){
        sName = iCharge.getSName();

        setJId(iCharge.getJId());
        setDtCreate(iCharge.getDtCreate());
        setJStatus(iCharge.getJStatus());
    }

    public CCharge(MCharge mCharge){
        assignModel(mCharge);
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public void assignModel(MCharge mCharge){
        if(mCharge!=null){
            sName = mCharge.getSName();
        }
    }

    public MCharge createModel(){
        MCharge mCharge =  new MCharge();

        mCharge.setSName(sName);

        mCharge.setJId(getJId());
        mCharge.setDtCreate(getDtCreate());
        mCharge.setJStatus(getJStatus());

        return mCharge;
    }
}
