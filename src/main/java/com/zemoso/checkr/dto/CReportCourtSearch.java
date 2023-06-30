package com.zemoso.checkr.dto;

import com.zemoso.checkr.db.contract.ICourtSearch;
import com.zemoso.checkr.db.contract.IReportCourtSearch;
import com.zemoso.checkr.model.MReportCourtSearch;

public class CReportCourtSearch extends CBase {

    private ICourtSearch iCourtSearch;
    private int jCourtSearchStatus;


    public CReportCourtSearch(){}

    public CReportCourtSearch(IReportCourtSearch iReportCourtSearch){
        iCourtSearch = iReportCourtSearch.getICourtSearch();
        jCourtSearchStatus = iReportCourtSearch.getJCourtSearchStatus();

        setJId(iReportCourtSearch.getJId());
        setDtCreate(iReportCourtSearch.getDtCreate());
        setJStatus(iReportCourtSearch.getJStatus());
    }

    public CReportCourtSearch(MReportCourtSearch mReportCourtSearch){
        assignModel(mReportCourtSearch);
    }

    public ICourtSearch getICourtSearch() {
        return iCourtSearch;
    }

    public void setICourtSearch(ICourtSearch iCourtSearch) {
        this.iCourtSearch = iCourtSearch;
    }

    public int getJCourtSearchStatus() {
        return jCourtSearchStatus;
    }

    public void setJCourtSearchStatus(int jCourtSearchStatus) {
        this.jCourtSearchStatus = jCourtSearchStatus;
    }

    public void assignModel(MReportCourtSearch mReportCourtSearch){
        if(mReportCourtSearch!=null){
            jCourtSearchStatus = mReportCourtSearch.getJCourtSearchStatus();
        }
    }

    public MReportCourtSearch createModel(){
        MReportCourtSearch mReportCourtSearch =  new MReportCourtSearch();

        mReportCourtSearch.setMCourtSearch(new CCourtSearch(iCourtSearch).createModel());
        mReportCourtSearch.setJCourtSearchStatus(jCourtSearchStatus);

        mReportCourtSearch.setJId(getJId());
        mReportCourtSearch.setDtCreate(getDtCreate());
        mReportCourtSearch.setJStatus(getJStatus());

        return mReportCourtSearch;
    }
}
