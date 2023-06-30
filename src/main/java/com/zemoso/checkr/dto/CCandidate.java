package com.zemoso.checkr.dto;

import com.zemoso.checkr.common.util.StringHelper;
import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.model.MCandidate;

import java.time.LocalDate;

public class CCandidate extends CBase {

    private String sEmail;
    private String sFirstName;
    private String sLastName;
    private LocalDate dtDob;
    private String sLocation;
    private int jReportStatus;
    private int jAdjudicationStatus;

    public CCandidate(){}

    public CCandidate(ICandidate iCandidate){
        sEmail = iCandidate.getSEmail();
        sFirstName = iCandidate.getSFirstName();
        sLastName = iCandidate.getSLastName();
        sLocation = iCandidate.getSLocation();
        dtDob = iCandidate.getDtDob();
        jReportStatus = iCandidate.getJReportStatus();
        jAdjudicationStatus = iCandidate.getJAdjudicationStatus();

        setJId(iCandidate.getJId());
        setDtCreate(iCandidate.getDtCreate());
        setJStatus(iCandidate.getJStatus());
    }

    public CCandidate(MCandidate mCandidate){
        assignModel(mCandidate);
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getSFirstName() {
        return sFirstName;
    }

    public void setSFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    public String getSLastName() {
        return sLastName;
    }

    public void setSLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public LocalDate getDtDob() {
        return dtDob;
    }

    public void setDtDob(LocalDate dtDob) {
        this.dtDob = dtDob;
    }

    public String getSLocation() {
        return sLocation;
    }

    public void setSLocation(String sLocation) {
        this.sLocation = sLocation;
    }

    public int getJReportStatus() {
        return jReportStatus;
    }

    public void setJReportStatus(int jReportStatus) {
        this.jReportStatus = jReportStatus;
    }

    public int getJAdjudicationStatus() {
        return jAdjudicationStatus;
    }

    public void setJAdjudicationStatus(int jAdjudicationStatus) {
        this.jAdjudicationStatus = jAdjudicationStatus;
    }

    public void assignModel(MCandidate mCandidate){
        if(mCandidate!=null){
            if(!StringHelper.isNullOrEmpty(mCandidate.getSEmail())){
                sEmail = mCandidate.getSEmail();
            }
            if(!StringHelper.isNullOrEmpty(mCandidate.getSFirstName())){
                sFirstName = mCandidate.getSFirstName();
            }
            if(!StringHelper.isNullOrEmpty(mCandidate.getSLastName())){
                sLastName = mCandidate.getSLastName();
            }
            if(!StringHelper.isNullOrEmpty(mCandidate.getSLocation())){
                sLocation = mCandidate.getSLocation();
            }
            if(mCandidate.getDtDob()!=null){
                dtDob = mCandidate.getDtDob();
            }

            if(mCandidate.getJReportStatus()!=0){
                jReportStatus = mCandidate.getJReportStatus();
            }

            if(mCandidate.getJAdjudicationStatus()!=0){
                jAdjudicationStatus = mCandidate.getJAdjudicationStatus();
            }

        }
    }

    public MCandidate createModel(){
        MCandidate mCandidate =  new MCandidate();

        mCandidate.setSEmail(sEmail);
        mCandidate.setSFirstName(sFirstName);
        mCandidate.setSLastName(sLastName);
        mCandidate.setSLocation(sLocation);
        mCandidate.setDtDob(dtDob);
        mCandidate.setJReportStatus(jReportStatus);
        mCandidate.setJAdjudicationStatus(jAdjudicationStatus);

        mCandidate.setJId(getJId());
        mCandidate.setDtCreate(getDtCreate());
        mCandidate.setJStatus(getJStatus());

        return mCandidate;
    }
}
