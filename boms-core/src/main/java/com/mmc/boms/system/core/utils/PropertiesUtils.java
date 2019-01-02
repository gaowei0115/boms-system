package com.mmc.boms.system.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @packageName：com.mmc.boms.system.core.utils
 * @desrciption: 属性配置文件加载工具类
 * @author: gaowei
 * @date： 2019-01-02 10:50
 * @history: (version) author date desc
 */
@Slf4j
public class PropertiesUtils {

    public static Properties loadProperties(String path) {
        Properties properties = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            properties = new Properties();
            properties.load(is);
        } catch (Exception e) {
            log.error(String.format("loading peroperties %s file exception cause: %s", path, e.getMessage()), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("release inputstream error", e);
                }
            }
        }
        return properties;
    }

    /**
     * 查询属性信息
     * @param properties
     * @param key
     * @return
     */
    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    /**
     * 查询属性信息
     * @param properties
     * @param key
     * @param default_value
     * @return
     */
    public static String getString(Properties properties, String key, String default_value) {
        String value = default_value;
        if (properties.contains(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }


    /**
     * 加载boolean类型属性值
     * @param properties
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties properties, String key) {
        return getBoolean(properties, key, false);
    }

    public static boolean getBoolean(Properties properties, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (properties.contains(key)) {
            value = CastUtils.castBoolean(properties.get(key));
        }
        return value;
    }

    /**
     * 获取int类型属性值
     * @param properties
     * @param key
     * @return
     */
    public static int getInt(Properties properties, String key) {
        return getInt(properties, key, 0);
    }

    public static int getInt(Properties properties, String key, int defaultValue) {
        int value = defaultValue;
        if (properties.contains(key)) {
            value = CastUtils.castInt(properties.get(key));
        }
        return value;
    }

}
