package com.zemoso.checkr.common.enums;

public enum EChargeStatus {

    ACTIVE(1),
    INACTIVE(2);

    private final int jValue;
    private String sText;

    private EChargeStatus(int jValue){
        this.jValue =jValue;
    }

    public int getJValue(){
        return jValue;
    }

    public String getSText(){
        return sText;
    }
}
