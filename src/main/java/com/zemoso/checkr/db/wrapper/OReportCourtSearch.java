package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICourtSearch;
import com.zemoso.checkr.db.contract.IReportCourtSearch;
import com.zemoso.checkr.db.domain.TReportCourtSearch;

import java.time.LocalDateTime;

public class OReportCourtSearch implements IReportCourtSearch {

    private TReportCourtSearch tReportCourtSearch;

    private OReportCourtSearch(TReportCourtSearch tbl){
        this.tReportCourtSearch = tbl;
    }

    public static OReportCourtSearch create(TReportCourtSearch tbl){
        return tbl==null ? null : new OReportCourtSearch(tbl);
    }

    @Override
    public int getJId() {
        return tReportCourtSearch.getJId();
    }

    @Override
    public LocalDateTime getDtCreate() {
        return tReportCourtSearch.getDtCreate();
    }

    @Override
    public int getJStatus() {
        return tReportCourtSearch.getJStatus();
    }


    @Override
    public ICourtSearch getICourtSearch() {
        return  OCourtSearch.create(tReportCourtSearch.getTCourtSearch());
    }

    @Override
    public int getJCourtSearchStatus() {
        return tReportCourtSearch.getJCourtSearchStatus();
    }
}
