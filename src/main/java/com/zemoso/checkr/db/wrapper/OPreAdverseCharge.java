package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICharge;
import com.zemoso.checkr.db.contract.IPreAdverseCharge;
import com.zemoso.checkr.db.domain.TPreAdverseCharge;

import java.time.LocalDateTime;

public class OPreAdverseCharge implements IPreAdverseCharge {

    private TPreAdverseCharge tbl;

    private OPreAdverseCharge(TPreAdverseCharge tbl){
        this.tbl = tbl;
    }

    public static OPreAdverseCharge create(TPreAdverseCharge tbl){
        return tbl==null ? null : new OPreAdverseCharge(tbl);
    }

    @Override
    public int getJId() {
        return tbl.getJId();
    }

    @Override
    public LocalDateTime getDtCreate() {
        return tbl.getDtCreate();
    }

    @Override
    public int getJStatus() {
        return tbl.getJStatus();
    }


    @Override
    public ICharge getICharge() {
        return  OCharge.create(tbl.getTCharge());
    }

    @Override
    public int getJChargeStatus() {
        return tbl.getJChargeStatus();
    }
}
