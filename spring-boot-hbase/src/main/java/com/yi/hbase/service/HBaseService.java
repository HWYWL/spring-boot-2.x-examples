package com.yi.hbase.service;

import com.yi.hbase.model.User;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.io.IOException;
import java.util.List;

/**
 * HBase CRUD 操作接口
 * @author huangwenyi
 */
public interface HBaseService {
    /**
     * 创建表
     *
     * @param tableNmae 表名
     * @param cols      列名
     * @throws IOException
     */
    void createTable(String tableNmae, String[] cols) throws IOException;

    /**
     * 插入数据
     *
     * @param tableName 表名
     * @param user      数据
     * @throws IOException
     */
    void insertData(String tableName, User user) throws IOException;

    /**
     * 获取原始数据
     *
     * @param tableName 表名
     * @return
     */
    ResultScanner getNoDealData(String tableName);

    /**
     * 根据rowKey进行查询
     *
     * @param tableName 表名
     * @param rowKey    行key
     * @return
     * @throws IOException
     */
    User getDataByRowKey(String tableName, String rowKey) throws IOException;

    /**
     * 查询指定单cell内容
     *
     * @param tableName 表名
     * @param rowKey    行key
     * @param family    所在的列族
     * @param col       指定需要查询的字段
     * @return
     */
    String getCellData(String tableName, String rowKey, String family, String col);

    /**
     * 查询指定表名中所有的数据
     *
     * @param tableName 表名
     * @return
     */
    List<User> getAllData(String tableName) throws IOException;

    /**
     * 删除指定cell数据
     *
     * @param tableName 表名
     * @param rowKey    行key
     * @throws IOException
     */
    void deleteByRowKey(String tableName, String rowKey) throws IOException;

    /**
     * 删除表
     *
     * @param tableName 表名
     */
    void deleteTable(String tableName);
}
