package com.zemoso.checkr.db.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "pre_adverse_charge")
public class TPreAdverseCharge extends TBase implements Serializable {

    private TCharge tCharge;
    private int jChargeStatus;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "charge_id")
    public TCharge getTCharge() {
        return tCharge;
    }

    public void setTCharge(TCharge tCharge) {
        this.tCharge = tCharge;
    }

    @Digits(integer =10,fraction = 0)
    @NotNull
    @Column(name = "charge_status",nullable = false,precision = 10)
    public int getJChargeStatus() {
        return jChargeStatus;
    }

    public void setJChargeStatus(int jChargeStatus) {
        this.jChargeStatus = jChargeStatus;
    }
}
