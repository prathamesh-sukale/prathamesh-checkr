package com.zemoso.checkr.db.wrapper;

import com.zemoso.checkr.db.contract.IPreAdverseAction;
import com.zemoso.checkr.db.contract.IPreAdverseCharge;
import com.zemoso.checkr.db.domain.TPreAdverseAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OPreAdverseAction implements IPreAdverseAction {

    private TPreAdverseAction tbl;

    private OPreAdverseAction(TPreAdverseAction tbl){
        this.tbl = tbl;
    }

    public static OPreAdverseAction create(TPreAdverseAction tbl){
        return tbl==null ? null : new OPreAdverseAction(tbl);
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
    public LocalDateTime getDtAction() {
        return tbl.getDtAction();
    }

    @Override
    public int getJDays() {
        return tbl.getJDays();
    }

    @Override
    public List<IPreAdverseCharge> getIPreAdverseCharges() {
        return tbl.getTPreAdverseCharges().stream().map(OPreAdverseCharge::create).collect(Collectors.toList());
    }
}
