package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.ICandidate;
import com.zemoso.checkr.db.contract.IUser;
import com.zemoso.checkr.db.domain.TUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OUser implements IUser {

    private TUser tUser;

    private OUser(TUser tbl){
        this.tUser = tbl;
    }

    public static OUser create(TUser tbl){
        return tbl==null ? null : new OUser(tbl);
    }

    @Override
    public int getJId() {
        return tUser.getJId();
    }

    @Override
    public LocalDateTime getDtCreate() {
        return tUser.getDtCreate();
    }

    @Override
    public int getJStatus() {
        return tUser.getJStatus();
    }

    @Override
    public String getSEmail() {
        return tUser.getSEmail();
    }

    @Override
    public String getSPassword() {
        return tUser.getSPassword();
    }

    @Override
    public int getJRole() {
        return tUser.getJRole();
    }

    @Override
    public List<ICandidate> getICandidates() {
        return tUser.getTCandidates().stream().map(OCandidate::create).collect(Collectors.toList());
    }
}
