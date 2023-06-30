package com.zemoso.checkr.common.enums;

public enum ERowStatus {

    ACTIVE(1),
    DELETED(2);

    private final int jValue;
    private String sText;

    private ERowStatus(int jValue){
        this.jValue =jValue;
    }

    public int getJValue(){
        return jValue;
    }

    public String getSText(){
        return sText;
    }
}
