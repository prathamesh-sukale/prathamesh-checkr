package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICourtSearch;
import com.zemoso.checkr.db.domain.TCourtSearch;

import java.time.LocalDateTime;

public class OCourtSearch implements ICourtSearch {

    private TCourtSearch tCourtSearch;

    private OCourtSearch(TCourtSearch tbl){
        this.tCourtSearch = tbl;
    }

    public static OCourtSearch create(TCourtSearch tbl){
        return tbl==null ? null : new OCourtSearch(tbl);
    }

    @Override
    public int getJId() {
        return tCourtSearch.getJId();
    }

    @Override
    public LocalDateTime getDtCreate() {
        return tCourtSearch.getDtCreate();
    }

    @Override
    public int getJStatus() {
        return tCourtSearch.getJStatus();
    }

    @Override
    public String getSName() {
        return tCourtSearch.getSName();
    }
}
