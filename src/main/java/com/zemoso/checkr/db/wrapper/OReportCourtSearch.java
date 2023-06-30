package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICourtSearch;
import com.zemoso.checkr.db.contract.IReportCourtSearch;
import com.zemoso.checkr.db.domain.TReportCourtSearch;

import java.time.LocalDateTime;

public class OReportCourtSearch implements IReportCourtSearch {

    private TReportCourtSearch tbl;

    private OReportCourtSearch(TReportCourtSearch tbl){
        this.tbl = tbl;
    }

    public static OReportCourtSearch create(TReportCourtSearch tbl){
        return tbl==null ? null : new OReportCourtSearch(tbl);
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
    public ICourtSearch getICourtSearch() {
        return  OCourtSearch.create(tbl.getTCourtSearch());
    }

    @Override
    public int getJCourtSearchStatus() {
        return tbl.getJCourtSearchStatus();
    }
}
