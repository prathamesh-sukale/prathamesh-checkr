package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.domain.TCandidate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OCandidate implements ICandidate {

    private TCandidate tCandidate;

    private OCandidate(TCandidate tbl){
        this.tCandidate = tbl;
    }

    public static OCandidate create(TCandidate tbl){
        return tbl==null ? null : new OCandidate(tbl);
    }

    @Override
    public int getJId() {
        return tCandidate.getJId();
    }

    @Override
    public LocalDateTime getDtCreate() {
        return tCandidate.getDtCreate();
    }

    @Override
    public int getJStatus() {
        return tCandidate.getJStatus();
    }

    @Override
    public String getSEmail() {
        return tCandidate.getSEmail();
    }

    @Override
    public String getSFirstName() {
        return tCandidate.getSFirstName();
    }

    @Override
    public String getSLastName() {
        return tCandidate.getSLastName();
    }

    @Override
    public LocalDate getDtDob() {
        return tCandidate.getDtDob();
    }

    @Override
    public String getSLocation() {
        return tCandidate.getSLocation();
    }

    @Override
    public int getJReportStatus() {
        return tCandidate.getJReportStatus();
    }

    @Override
    public int getJAdjudicationStatus() {
        return tCandidate.getJAdjudicationStatus();
    }
}
