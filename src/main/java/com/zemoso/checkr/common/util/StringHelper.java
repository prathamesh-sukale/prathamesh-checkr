package com.zemoso.checkr.common.util;

public class StringHelper {

    private StringHelper(){}

    public  static boolean isNullOrEmpty(String str){
        return str==null || str.trim().isEmpty();
    }
}
