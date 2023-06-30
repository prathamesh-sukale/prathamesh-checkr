package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.contract.IUser;
import com.zemoso.checkr.db.domain.TUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OUser implements IUser {

    private TUser tbl;

    private OUser(TUser tbl){
        this.tbl = tbl;
    }

    public static OUser create(TUser tbl){
        return tbl==null ? null : new OUser(tbl);
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
    public String getSPassword() {
        return "***";
    }

    @Override
    public int getJRole() {
        return tbl.getJRole();
    }

    @Override
    public List<ICandidate> getICandidates() {
        return tbl.getTCandidates().stream().map(OCandidate::create).collect(Collectors.toList());
    }
}
