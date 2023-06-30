package com.zemoso.checkr.db.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "charge")
public class TCharge extends TBase implements Serializable {

    private String sName;

    @NotEmpty
    @Size(max = 512)
    @Column(name = "name", nullable = false, length = 512)
    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

}
