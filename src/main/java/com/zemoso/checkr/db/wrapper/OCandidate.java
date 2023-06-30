package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.domain.TCandidate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OCandidate implements ICandidate {

    private TCandidate tbl;

    private OCandidate(TCandidate tbl){
        this.tbl = tbl;
    }

    public static OCandidate create(TCandidate tbl){
        return tbl==null ? null : new OCandidate(tbl);
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
    public String getSEmail() {
        return tbl.getSEmail();
    }

    @Override
    public String getSFirstName() {
        return tbl.getSFirstName();
    }

    @Override
    public String getSLastName() {
        return tbl.getSLastName();
    }

    @Override
    public LocalDate getDtDob() {
        return tbl.getDtDob();
    }

    @Override
    public String getSLocation() {
        return tbl.getSLocation();
    }

    @Override
    public int getJReportStatus() {
        return tbl.getJReportStatus();
    }

    @Override
    public int getJAdjudicationStatus() {
        return tbl.getJAdjudicationStatus();
    }
}
