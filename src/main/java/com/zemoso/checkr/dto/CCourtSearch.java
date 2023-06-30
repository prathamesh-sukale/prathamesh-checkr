package com.zemoso.checkr.dto;

import com.zemoso.checkr.db.contract.ICourtSearch;
import com.zemoso.checkr.model.MCourtSearch;

public class CCourtSearch extends CBase {

    private String sName;

    public CCourtSearch(){}

    public CCourtSearch(ICourtSearch iCourtSearch){
        sName = iCourtSearch.getSName();

        setJId(iCourtSearch.getJId());
        setDtCreate(iCourtSearch.getDtCreate());
        setJStatus(iCourtSearch.getJStatus());
    }

    public CCourtSearch(MCourtSearch mCourtSearch){
        assignModel(mCourtSearch);
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public void assignModel(MCourtSearch mCourtSearch){
        if(mCourtSearch!=null){
            sName = mCourtSearch.getSName();
        }
    }

    public MCourtSearch createModel(){
        MCourtSearch mCourtSearch =  new MCourtSearch();

        mCourtSearch.setSName(sName);

        mCourtSearch.setJId(getJId());
        mCourtSearch.setDtCreate(getDtCreate());
        mCourtSearch.setJStatus(getJStatus());

        return mCourtSearch;
    }
}
