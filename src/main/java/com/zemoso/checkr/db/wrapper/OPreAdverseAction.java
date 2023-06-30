package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.IPreAdverseAction;
import com.zemoso.checkr.db.contract.IPreAdverseCharge;
import com.zemoso.checkr.db.domain.TPreAdverseAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OPreAdverseAction implements IPreAdverseAction {

    private TPreAdverseAction tPreAdverseAction;

    private OPreAdverseAction(TPreAdverseAction tbl){
        this.tPreAdverseAction = tbl;
    }

    public static OPreAdverseAction create(TPreAdverseAction tbl){
        return tbl==null ? null : new OPreAdverseAction(tbl);
    }

    @Override
    public int getJId() {
        return tPreAdverseAction.getJId();
    }

    @Override
    public LocalDateTime getDtCreate() {
        return tPreAdverseAction.getDtCreate();
    }

    @Override
    public int getJStatus() {
        return tPreAdverseAction.getJStatus();
    }


    @Override
    public LocalDateTime getDtAction() {
        return tPreAdverseAction.getDtAction();
    }

    @Override
    public int getJDays() {
        return tPreAdverseAction.getJDays();
    }

    @Override
    public List<IPreAdverseCharge> getIPreAdverseCharges() {
        return tPreAdverseAction.getTPreAdverseCharges().stream().map(OPreAdverseCharge::create).collect(Collectors.toList());
    }
}
