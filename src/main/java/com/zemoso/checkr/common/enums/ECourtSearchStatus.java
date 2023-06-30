package com.zemoso.checkr.common.enums;

public enum ECourtSearchStatus {

    CLEAR(1),
    CONSIDER(2);

    private final int jValue;
    private String sText;

    private ECourtSearchStatus(int jValue){
        this.jValue =jValue;
    }

    public int getJValue(){
        return jValue;
    }

    public String getSText(){
        return sText;
    }
}
