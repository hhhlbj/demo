package com.hhhlbjshop.backend.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by songyouming on 2017/7/26.
 */
public class BaseUtil {


    public static boolean Base_HasValue(Object pValue) {
        Boolean HasValue = true;
        if (pValue == (null)) {
            HasValue = false;
        }
        return HasValue;
    }

    public static boolean Base_HasValue(String pValue) {
        Boolean HasValue = true;
        if (pValue == (null)) {
            HasValue = false;
        } else if (pValue.equals("")) {
            HasValue = false;
        } else if (pValue.trim().equals("")) {
            HasValue = false;
        } else if (pValue.trim().equals("null")) {

        }
        return HasValue;
    }

    public static boolean Base_HasValue(Integer pValue) {
        Boolean HasValue = true;
        if (pValue == (null)) {
            HasValue = false;
        }
        return HasValue;
    }

    public static boolean Base_HasValue(Map pValue) {
        Boolean HasValue = true;
        if (pValue == (null) || pValue.size() < 1) {
            HasValue = false;
        }
        return HasValue;
    }

    public static boolean Base_HasValue(List pValue) {
        Boolean HasValue = true;
        if (pValue == (null) || pValue.size() < 1) {
            HasValue = false;
        }
        return HasValue;
    }

    public static boolean Base_HasValue(Double pValue) {
        Boolean HasValue = true;
        if (pValue == (null)) {
            HasValue = false;
        }
        return HasValue;
    }


    public static void Base_CheckHasValue(String Msg, Object pValue) {
        if (!Base_HasValue(pValue)) {
            throw new Error(Msg + " : 是空值");
        }
    }

    public static void Base_CheckHasValue(String Msg, String pValue) {
        if (!Base_HasValue(pValue)) {
            throw new Error(Msg + " : 是空值");
        }
    }

    public static void Base_CheckHasValue(String Msg, Integer pValue) {
        if (!Base_HasValue(pValue)) {
            throw new Error(Msg + " : 是空值");
        }
    }

    public static void Base_CheckHasValue(String Msg, Map pValue) {
        if (!Base_HasValue(pValue)) {
            throw new Error(Msg + " : 是空值");
        }
    }

    public static void Base_CheckHasValue(String Msg, List pValue) {
        if (!Base_HasValue(pValue)) {
            throw new Error(Msg + " : 是空值");
        }
    }

    public static void Base_CheckHasValue(String Msg, Double pValue) {
        if (!Base_HasValue(pValue)) {
            throw new Error(Msg + " : 是空值");
        }
    }

    public static Object Base_DefaultValue(Object oldValue, Object defValue) {
        if (Base_HasValue(oldValue)) {
            if (oldValue instanceof Integer) {
                return ((Integer) oldValue).intValue();
            } else if (oldValue instanceof Long) {
                return ((Long) oldValue).longValue();
            } else if (oldValue instanceof Double) {
                return ((Double) oldValue).doubleValue();
            } else if (oldValue instanceof Float) {
                return ((Float) oldValue).floatValue();
            } else if (oldValue instanceof Boolean) {
                return ((Boolean) oldValue).booleanValue();
            } else if (oldValue instanceof String) {
                return (String) oldValue;
            } else if (oldValue instanceof Date) {
                return (Date) oldValue;
            }
        }
        return defValue;
    }


    public static String[] Base_Split(String string, String divisionChar) {
        int i = 0;
        StringTokenizer tokenizer = new StringTokenizer(string, divisionChar);

        String[] str = new String[tokenizer.countTokens()];

        while (tokenizer.hasMoreTokens()) {
            str[i] = new String();
            str[i] = tokenizer.nextToken();
            i++;
        }

        return str;
    }
}