package com.zemoso.checkr.dto;

import java.time.LocalDateTime;

public class CBase {

    private int jId;
    private LocalDateTime dtCreate;
    private int jStatus;

    public int getJId() {
        return jId;
    }

    public void setJId(int jId) {
        this.jId = jId;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public int getJStatus() {
        return jStatus;
    }

    public void setJStatus(int jStatus) {
        this.jStatus = jStatus;
    }

}
