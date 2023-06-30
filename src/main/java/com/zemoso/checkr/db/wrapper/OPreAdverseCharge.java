package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICharge;
import com.zemoso.checkr.db.contract.IPreAdverseCharge;
import com.zemoso.checkr.db.domain.TPreAdverseCharge;

import java.time.LocalDateTime;

public class OPreAdverseCharge implements IPreAdverseCharge {

    private TPreAdverseCharge tPreAdverseCharge;

    private OPreAdverseCharge(TPreAdverseCharge tbl){
        this.tPreAdverseCharge = tbl;
    }

    public static OPreAdverseCharge create(TPreAdverseCharge tbl){
        return tbl==null ? null : new OPreAdverseCharge(tbl);
    }

    @Override
    public int getJId() {
        return tPreAdverseCharge.getJId();
    }

    @Override
    public LocalDateTime getDtCreate() {
        return tPreAdverseCharge.getDtCreate();
    }

    @Override
    public int getJStatus() {
        return tPreAdverseCharge.getJStatus();
    }


    @Override
    public ICharge getICharge() {
        return  OCharge.create(tPreAdverseCharge.getTCharge());
    }

    @Override
    public int getJChargeStatus() {
        return tPreAdverseCharge.getJChargeStatus();
    }
}
