package com.zemoso.checkr.dto;

import com.zemoso.checkr.db.contract.IPreAdverseAction;
import com.zemoso.checkr.db.contract.IPreAdverseCharge;
import com.zemoso.checkr.model.MPreAdverseAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CPreAdverseAction extends CBase {

    private LocalDateTime dtAction;
    private int jDays;
    private List<IPreAdverseCharge> iPreAdverseCharges;

    public CPreAdverseAction(){}

    public CPreAdverseAction(IPreAdverseAction iPreAdverseAction){
        dtAction = iPreAdverseAction.getDtAction();
        jDays = iPreAdverseAction.getJDays();
        iPreAdverseCharges = iPreAdverseAction.getIPreAdverseCharges();

        setJId(iPreAdverseAction.getJId());
        setDtCreate(iPreAdverseAction.getDtCreate());
        setJStatus(iPreAdverseAction.getJStatus());
    }

    public CPreAdverseAction(MPreAdverseAction mPreAdverseAction){
        assignModel(mPreAdverseAction);
    }

    public LocalDateTime getDtAction() {
        return dtAction;
    }

    public void setDtAction(LocalDateTime dtAction) {
        this.dtAction = dtAction;
    }

    public int getJDays() {
        return jDays;
    }

    public void setJDays(int jDays) {
        this.jDays = jDays;
    }

    public List<IPreAdverseCharge> getIPreAdverseCharges() {
        return iPreAdverseCharges;
    }

    public void setIPreAdverseCharges(List<IPreAdverseCharge> iPreAdverseCharges) {
        this.iPreAdverseCharges = iPreAdverseCharges;
    }

    public void assignModel(MPreAdverseAction mPreAdverseAction){
        if(mPreAdverseAction!=null){
            dtAction = mPreAdverseAction.getDtAction();
            jDays = mPreAdverseAction.getJDays();
        }
    }

    public MPreAdverseAction createModel(){
        MPreAdverseAction mPreAdverseAction =  new MPreAdverseAction();

        mPreAdverseAction.setDtAction(dtAction);
        mPreAdverseAction.setJDays(jDays);
        mPreAdverseAction.setMPreAdverseCharges(iPreAdverseCharges.stream().map(e-> new CPreAdverseCharge(e).createModel()).collect(Collectors.toList()));

        mPreAdverseAction.setJId(getJId());
        mPreAdverseAction.setDtCreate(getDtCreate());
        mPreAdverseAction.setJStatus(getJStatus());

        return mPreAdverseAction;
    }
}
