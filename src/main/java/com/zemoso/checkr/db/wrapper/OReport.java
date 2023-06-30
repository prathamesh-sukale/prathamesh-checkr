package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.contract.IPreAdverseAction;
import com.zemoso.checkr.db.contract.IReport;
import com.zemoso.checkr.db.contract.IReportCourtSearch;
import com.zemoso.checkr.db.domain.TReport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OReport implements IReport {

    private TReport tbl;

    private OReport(TReport tbl){
        this.tbl = tbl;
    }

    public static OReport create(TReport tbl){
        return tbl==null ? null : new OReport(tbl);
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
    public ICandidate getICandidate() {
        return OCandidate.create(tbl.getTCandidate());
    }

    @Override
    public List<IPreAdverseAction> getIPreAdverseActions() {
        return tbl.getTPreAdverseActions().stream().map(OPreAdverseAction::create).collect(Collectors.toList());
    }

    @Override
    public List<IReportCourtSearch> getIReportCourtSearches() {
        return tbl.getTReportCourtSearches().stream().map(OReportCourtSearch::create).collect(Collectors.toList());
    }
}
