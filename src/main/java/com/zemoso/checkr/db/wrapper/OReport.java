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

    private TReport tReport;

    private OReport(TReport tbl){
        this.tReport = tbl;
    }

    public static OReport create(TReport tbl){
        return tbl==null ? null : new OReport(tbl);
    }

    @Override
    public int getJId() {
        return tReport.getJId();
    }

    @Override
    public LocalDateTime getDtCreate() {
        return tReport.getDtCreate();
    }

    @Override
    public int getJStatus() {
        return tReport.getJStatus();
    }

    @Override
    public ICandidate getICandidate() {
        return OCandidate.create(tReport.getTCandidate());
    }

    @Override
    public List<IPreAdverseAction> getIPreAdverseActions() {
        return tReport.getTPreAdverseActions().stream().map(OPreAdverseAction::create).collect(Collectors.toList());
    }

    @Override
    public List<IReportCourtSearch> getIReportCourtSearches() {
        return tReport.getTReportCourtSearches().stream().map(OReportCourtSearch::create).collect(Collectors.toList());
    }
}
