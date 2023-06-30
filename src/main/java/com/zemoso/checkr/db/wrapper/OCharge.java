package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICharge;
import com.zemoso.checkr.db.domain.TCharge;

import java.time.LocalDateTime;

public class OCharge implements ICharge {

    private TCharge tCharge;

    private OCharge(TCharge tbl){
        this.tCharge = tbl;
    }

    public static OCharge create(TCharge tbl){
        return tbl==null ? null : new OCharge(tbl);
    }

    @Override
    public int getJId() {
        return tCharge.getJId();
    }

    @Override
    public LocalDateTime getDtCreate() {
        return tCharge.getDtCreate();
    }

    @Override
    public int getJStatus() {
        return tCharge.getJStatus();
    }

    @Override
    public String getSName() {
        return tCharge.getSName();
    }
}
