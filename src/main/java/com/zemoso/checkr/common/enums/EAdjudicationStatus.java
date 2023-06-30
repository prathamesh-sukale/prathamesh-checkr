package com.zemoso.checkr.common.enums;

public enum EAdjudicationStatus {

    NO_ACTION(1),
    ADVERSE_ACTION(2),
    ENGAGE(3);

    private final int jValue;
    private String sText;

    private EAdjudicationStatus(int jValue){
        this.jValue =jValue;
    }

    public int getJValue(){
        return jValue;
    }

    public String getSText(){
        return sText;
    }
}
