package com.zemoso.checkr.db.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class TBase implements Serializable{

    private int jId;
    private LocalDateTime dtCreate;
    private int jStatus;

    @Digits(integer =10,fraction = 0)
    @NotNull
    @Column(name = "id",nullable = false,precision = 10)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getJId() {
        return jId;
    }

    public void setJId(int jId) {
        this.jId = jId;
    }

    @NotNull
    @Column(name = "create_datetime", nullable = false,length = 29)
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Digits(integer =10,fraction = 0)
    @NotNull
    @Column(name = "status",nullable = false,precision = 10)
    public int getJStatus() {
        return jStatus;
    }

    public void setJStatus(int jStatus) {
        this.jStatus = jStatus;
    }
}
