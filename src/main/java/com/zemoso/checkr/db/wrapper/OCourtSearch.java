package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICourtSearch;
import com.zemoso.checkr.db.domain.TCourtSearch;

import java.time.LocalDateTime;

public class OCourtSearch implements ICourtSearch {

    private TCourtSearch tbl;

    private OCourtSearch(TCourtSearch tbl){
        this.tbl = tbl;
    }

    public static OCourtSearch create(TCourtSearch tbl){
        return tbl==null ? null : new OCourtSearch(tbl);
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
