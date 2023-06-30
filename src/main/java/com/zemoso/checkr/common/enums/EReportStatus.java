package com.zemoso.checkr.common.enums;

public enum EReportStatus {

    NO_ACTION(1),
    CLEAR(2),
    CONSIDER(3);

    private final int jValue;
    private String sText;

    private EReportStatus(int jValue){
        this.jValue =jValue;
    }

    public int getJValue(){
        return jValue;
    }

    public String getSText(){
        return sText;
    }
}
