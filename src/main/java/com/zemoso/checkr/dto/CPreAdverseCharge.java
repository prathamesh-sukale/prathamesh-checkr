package com.zemoso.checkr.dto;

import com.zemoso.checkr.db.contract.ICharge;
import com.zemoso.checkr.db.contract.IPreAdverseCharge;
import com.zemoso.checkr.model.MPreAdverseCharge;

public class CPreAdverseCharge extends CBase {

    private ICharge iCharge;
    private int jChargeStatus;

    public CPreAdverseCharge(){}

    public CPreAdverseCharge(IPreAdverseCharge iPreAdverseCharge){
        iCharge = iPreAdverseCharge.getICharge();
        jChargeStatus = iPreAdverseCharge.getJChargeStatus();

        setJId(iPreAdverseCharge.getJId());
        setDtCreate(iPreAdverseCharge.getDtCreate());
        setJStatus(iPreAdverseCharge.getJStatus());
    }

    public CPreAdverseCharge(MPreAdverseCharge mPreAdverseCharge){
        assignModel(mPreAdverseCharge);
    }

    public ICharge getICharge() {
        return iCharge;
    }

    public void setICharge(ICharge iCharge) {
        this.iCharge = iCharge;
    }

    public int getJChargeStatus() {
        return jChargeStatus;
    }

    public void setJChargeStatus(int jChargeStatus) {
        this.jChargeStatus = jChargeStatus;
    }

    public void assignModel(MPreAdverseCharge mPreAdverseCharge){
        if(mPreAdverseCharge!=null){
            jChargeStatus = mPreAdverseCharge.getJChargeStatus();
        }
    }

    public MPreAdverseCharge createModel(){
        MPreAdverseCharge mPreAdverseCharge =  new MPreAdverseCharge();

        mPreAdverseCharge.setMCharge(new CCharge(iCharge).createModel());
        mPreAdverseCharge.setJChargeStatus(jChargeStatus);

        mPreAdverseCharge.setJId(getJId());
        mPreAdverseCharge.setDtCreate(getDtCreate());
        mPreAdverseCharge.setJStatus(getJStatus());

        return mPreAdverseCharge;
    }
}
