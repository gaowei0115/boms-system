package com.mmc.boms.system.core.datasource;

import com.mmc.boms.system.core.utils.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @packageName：com.mmc.boms.system.core.datasource
 * @desrciption: 注册数据源
 *              实现ImportBeanDefinitionRegistrar接口，对DataSource数据源执行注册
 *
 * @author: gaowei
 * @date： 2019-01-02 10:23
 * @history: (version) author date desc
 */
@Slf4j
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar {


    /**
     * 默认数据源
     */
    private static final String DATASOURCE_DEFAULT = "datasource";

    /**
     * 指定默认数据源(springboot2.0默认数据源是hikari如何想使用其他数据源可以自己配置)
     */
    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";


    private static Properties DATASOURCE_LOAD;

    static {
        try {
            /**
             * 加载dataSouce配置文件
             */
            DATASOURCE_LOAD = PropertiesUtils.loadProperties("config-datasource.properties");
        } catch (Exception e) {
            log.error("loading datasource properties exception ", e);
        }
    }

    public DataSource initDefaultDataSource() {
        Map<DataSourceType, Object> map = new HashMap<>(16);
        map.put(DataSourceType.DRIVER, PropertiesUtils.getString(DATASOURCE_LOAD, "detault.datasource.driver"));
        map.put(DataSourceType.URL, PropertiesUtils.getString(DATASOURCE_LOAD, "detault.datasource.url"));
        map.put(DataSourceType.USERNAME, PropertiesUtils.getString(DATASOURCE_LOAD, "detault.datasource.username"));
        map.put(DataSourceType.PASSWORD, PropertiesUtils.getString(DATASOURCE_LOAD, "detault.datasource.password"));
        return buildDataSource(map);
    }

    /**
     * 生成DataSource对象
     * @param map
     * @return
     */
    public DataSource buildDataSource(Map<DataSourceType, Object> map) {
        /**
         * 数据源对象，如果为空，使用SpringBoot默认支持数据源
         */
        Object dsType = map.get(DataSourceType.TYPE);
        if (dsType == null) {
            dsType = DATASOURCE_TYPE_DEFAULT;
        }

        return null;
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

    }


    enum DataSourceType {
        DRIVER,
        URL,
        USERNAME,
        PASSWORD,
        TYPE;
    }

}
