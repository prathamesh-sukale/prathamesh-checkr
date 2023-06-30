package com.zemoso.checkr.dto;

import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.contract.IPreAdverseAction;
import com.zemoso.checkr.db.contract.IReport;
import com.zemoso.checkr.db.contract.IReportCourtSearch;
import com.zemoso.checkr.model.MReport;

import java.util.List;
import java.util.stream.Collectors;

public class CReport extends CBase {

    private ICandidate iCandidate;
    private List<IPreAdverseAction> iPreAdverseActions;
    private List<IReportCourtSearch> iReportCourtSearches;


    public CReport(){}

    public CReport(IReport iReport){
        iCandidate = iReport.getICandidate();
        iPreAdverseActions = iReport.getIPreAdverseActions();
        iReportCourtSearches = iReport.getIReportCourtSearches();

        setJId(iReport.getJId());
        setDtCreate(iReport.getDtCreate());
        setJStatus(iReport.getJStatus());
    }

    public CReport(MReport mReport){
        assignModel(mReport);
    }

    public ICandidate getICandidate() {
        return iCandidate;
    }

    public void setICandidate(ICandidate iCandidate) {
        this.iCandidate = iCandidate;
    }

    public List<IPreAdverseAction> getIPreAdverseActions() {
        return iPreAdverseActions;
    }

    public void setIPreAdverseActions(List<IPreAdverseAction> iPreAdverseActions) {
        this.iPreAdverseActions = iPreAdverseActions;
    }

    public List<IReportCourtSearch> getIReportCourtSearches() {
        return iReportCourtSearches;
    }

    public void setIReportCourtSearches(List<IReportCourtSearch> iReportCourtSearches) {
        this.iReportCourtSearches = iReportCourtSearches;
    }



    public void assignModel(MReport mReport){
        //Add any new property which can be assigned to model
    }

    public MReport createModel(){
        MReport mReport =  new MReport();

        mReport.setMCandidate(iCandidate==null ? null : new CCandidate(iCandidate).createModel());
        mReport.setMPreAdverseActions(iPreAdverseActions==null ? null : iPreAdverseActions.stream().map(e->new CPreAdverseAction(e).createModel()).collect(Collectors.toList()));
        mReport.setMReportCourtSearches(iReportCourtSearches==null ? null : iReportCourtSearches.stream().map(e->new CReportCourtSearch(e).createModel()).collect(Collectors.toList()));

        mReport.setJId(getJId());
        mReport.setDtCreate(getDtCreate());
        mReport.setJStatus(getJStatus());

        return mReport;
    }
}
