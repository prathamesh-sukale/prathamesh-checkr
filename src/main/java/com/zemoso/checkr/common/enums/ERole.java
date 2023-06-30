package com.zemoso.checkr.common.enums;

public enum ERole {

    ADMIN(1),
    CANDIDATE(2);

    private final int jValue;
    private String sText;

    private ERole(int jValue){
        this.jValue =jValue;
    }

    public int getJValue(){
        return jValue;
    }

    public String getSText(){
        return sText;
    }
}
