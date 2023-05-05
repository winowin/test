package com.rookie.utils;

public class StringUtils {
    public static boolean isEmpty(String str){
        if(str==null)
            return true;
        if("".equals(str))
            return true;
        return false;
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
