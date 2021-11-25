package com.teylas.roommanager;

final public class Utils {
    public static String formatDecimal(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
}
