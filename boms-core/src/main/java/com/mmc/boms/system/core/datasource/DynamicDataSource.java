package com.mmc.boms.system.core.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @packageName：com.mmc.boms.system.core.datasource
 * @desrciption: 动态数据源
 *                  继承AbstractRoutingDataSource（数据源路由api） 实现determineCurrentLookupKey获取当前操作数据源key方法
 * @author: gaowei
 * @date： 2019-01-02 10:02
 * @history: (version) author date desc
 */
public class DynamicDataSource extends AbstractRoutingDataSource{


    /**
     * 返回当前操作数据源ID
     * @return
     */
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceId();
    }


    /**
     * 数据源控制类
     */
    @Slf4j
    static class DynamicDataSourceContextHolder {

        /**
         * 保存当前线程拥有的Datasource Id
         */
        private static final ThreadLocal<String> DATASOURCE_ID = new ThreadLocal<>();

        /**
         * 数据源ID集合
         */
        private static List<String> dataSourceIds = new ArrayList<>();

        /**
         * 设置数据源ID
         * @param datasourceId
         */
        public static void setDatasourceId(String datasourceId) {
            DATASOURCE_ID.set(datasourceId);
        }

        /**
         * 取出数据源ID
         * @return
         */
        public static String getDataSourceId() {
            return DATASOURCE_ID.get();
        }


        /**
         * 清除数据源
         */
        public static void removeDataSourceId() {
            DATASOURCE_ID.remove();
        }

        /**
         * 增加数据源ID
         * @param id
         */
        public static void addDataSourceIds(String id) {
            dataSourceIds.add(id);
        }

        /**
         * 验证数据源是否存在
         * @param id
         * @return
         */
        public static boolean isContainsDataSource(String id) {
            return dataSourceIds.contains(id);
        }
    }
}
