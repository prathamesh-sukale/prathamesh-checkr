package com.zemoso.checkr.dto;

import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.contract.IUser;
import com.zemoso.checkr.model.MUser;

import java.util.ArrayList;
import java.util.List;

public class CUser extends CBase {

    private String sEmail;
    private String sPassword;
    private int jRole;

    private List<ICandidate> iCandidates = new ArrayList<>();


    public CUser(){}

    public CUser(IUser iUser){
        sEmail = iUser.getSEmail();
        sPassword = iUser.getSPassword();
        jRole = iUser.getJRole();
        iCandidates = iUser.getICandidates();

        setJId(iUser.getJId());
        setDtCreate(iUser.getDtCreate());
        setJStatus(iUser.getJStatus());
    }

    public  CUser(MUser mUser){
        assignModel(mUser);
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public int getJRole() {
        return jRole;
    }

    public void setJRole(int jRole) {
        this.jRole = jRole;
    }

    public List<ICandidate> getICandidates() {
        return iCandidates;
    }

    public void setICandidates(List<ICandidate> iCandidates) {
        this.iCandidates = iCandidates;
    }

    public void assignModel(MUser mUser){
        if(mUser!=null){
            sEmail = mUser.getSEmail();
            sPassword = mUser.getSPassword();
            jRole = mUser.getJRole();
        }
    }

    public MUser createModel(){
        MUser mUser =  new MUser();

        mUser.setSEmail(sEmail);
        mUser.setSPassword(sPassword);
        mUser.setJRole(jRole);

        mUser.setJId(getJId());
        mUser.setDtCreate(getDtCreate());
        mUser.setJStatus(getJStatus());

        return mUser;
    }
}
