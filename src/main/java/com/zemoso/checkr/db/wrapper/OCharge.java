package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICharge;
import com.zemoso.checkr.db.domain.TCharge;

import java.time.LocalDateTime;

public class OCharge implements ICharge {

    private TCharge tbl;

    private OCharge(TCharge tbl){
        this.tbl = tbl;
    }

    public static OCharge create(TCharge tbl){
        return tbl==null ? null : new OCharge(tbl);
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
    public String getSName() {
        return tbl.getSName();
    }
}
