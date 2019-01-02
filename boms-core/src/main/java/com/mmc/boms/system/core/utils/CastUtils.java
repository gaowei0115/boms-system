package com.mmc.boms.system.core.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @packageName：com.mmc.boms.system.core.utils
 * @desrciption: 类型转换
 * @author: gaowei
 * @date： 2019-01-02 12:26
 * @history: (version) author date desc
 */
public class CastUtils {


    /**
     * boolean类型转换
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj) {
        return castBoolean(obj, false);
    }

    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean value = defaultValue;
        if (obj != null) {
            String temp = castString(obj);
            if (StringUtils.isNotEmpty(temp)) {
                value = Boolean.parseBoolean(temp);
            }
        }
        return value;
    }

    /**
     * 转换成long类型
     * @param obj
     * @return
     */
    public static long castLong(Object obj) {
        return castLong(obj, 0L);
    }

    public static long castLong(Object obj, long defaultValue) {
        long value = defaultValue;
        if (obj != null) {
            String temp = castString(obj);
            if (StringUtils.isNotEmpty(temp)) {
                value = Long.parseLong(temp);
            }
        }
        return value;
    }

    /**
     * 转换成double类型
     * @param obj
     * @return
     */
    public static double castDouble(Object obj) {
        return castDouble(obj, 0.0D);
    }

    public static double castDouble(Object obj, double defaultValue) {
        double value = defaultValue;
        if (obj != null) {
            String temp = castString(obj);
            if (StringUtils.isNotEmpty(temp)) {
                value = Double.parseDouble(temp);
            }
        }
        return value;
    }

    /**
     * 转换成int类型
     * @param obj
     * @return
     */
    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }

    /**
     * 转换成int类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj, int defaultValue) {
        int value = defaultValue;
        if (obj != null) {
            String temp = castString(obj);
            if (StringUtils.isNotEmpty(temp)) {
                value = Integer.parseInt(temp);
            }
        }
        return value;
    }

    /**
     * 转换成String类型
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return castString(obj, "");
    }

    /**
     * 转换成String类型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        String value = defaultValue;
        if (obj != null) {
            if (obj instanceof String) {
               value = String.valueOf(obj);
            }
        }
        return value;
    }
}
