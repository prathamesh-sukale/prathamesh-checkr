package com.zemoso.checkr.db.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "report_court_search_mapper")
public class TReportCourtSearch extends TBase implements Serializable {

    private TCourtSearch tCourtSearch;
    private int jCourtSearchStatus;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "court_search_id")
    public TCourtSearch getTCourtSearch() {
        return tCourtSearch;
    }

    public void setTCourtSearch(TCourtSearch tCourtSearch) {
        this.tCourtSearch = tCourtSearch;
    }

    @Digits(integer =10,fraction = 0)
    @NotNull
    @Column(name = "court_search_status",nullable = false,precision = 10)
    public int getJCourtSearchStatus() {
        return jCourtSearchStatus;
    }

    public void setJCourtSearchStatus(int jCourtSearchStatus) {
        this.jCourtSearchStatus = jCourtSearchStatus;
    }
}
