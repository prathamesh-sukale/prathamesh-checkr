package com.zemoso.checkr.db.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "report")
public class TReport extends TBase implements Serializable {

    private TCandidate tCandidate;
    private List<TPreAdverseAction> tPreAdverseActions = new ArrayList<>();

    private List<TReportCourtSearch> tReportCourtSearches = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    public TCandidate getTCandidate() {
        return tCandidate;
    }

    public void setTCandidate(TCandidate tCandidate) {
        this.tCandidate = tCandidate;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id",referencedColumnName = "id")
    public List<TPreAdverseAction> getTPreAdverseActions() {
        return tPreAdverseActions;
    }

    public void setTPreAdverseActions(List<TPreAdverseAction> tPreAdverseActions) {
        this.tPreAdverseActions = tPreAdverseActions;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id",referencedColumnName = "id")
    public List<TReportCourtSearch> getTReportCourtSearches() {
        return tReportCourtSearches;
    }

    public void setTReportCourtSearches(List<TReportCourtSearch> tReportCourtSearches) {
        this.tReportCourtSearches = tReportCourtSearches;
    }
}
