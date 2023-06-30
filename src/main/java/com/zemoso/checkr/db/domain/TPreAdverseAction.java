package com.zemoso.checkr.db.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pre_adverse_action")
public class TPreAdverseAction extends TBase implements Serializable {

    private LocalDateTime dtAction;
    private int jDays;
    private List<TPreAdverseCharge> tPreAdverseCharges =new ArrayList<>();

    @NotNull
    @Column(name = "action_datetime", nullable = false,length = 29)
    public LocalDateTime getDtAction() {
        return dtAction;
    }

    public void setDtAction(LocalDateTime dtAction) {
        this.dtAction = dtAction;
    }

    @Digits(integer =10,fraction = 0)
    @NotNull
    @Column(name = "days",nullable = false,precision = 10)
    public int getJDays() {
        return jDays;
    }

    public void setJDays(int jDays) {
        this.jDays = jDays;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_adverse_action_id",referencedColumnName = "id")
    public List<TPreAdverseCharge> getTPreAdverseCharges() {
        return tPreAdverseCharges;
    }

    public void setTPreAdverseCharges(List<TPreAdverseCharge> tPreAdverseCharges) {
        this.tPreAdverseCharges = tPreAdverseCharges;
    }
}
