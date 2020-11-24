package com.yi.hbase.service.impl;

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import com.yi.hbase.model.User;
import com.yi.hbase.service.HBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.yi.hbase.config.HBaseConfig.initHbase;

/**
 * HBase逻辑业务
 *
 * @author huangwenyi
 */
@Slf4j
@Service
public class HBaseServiceImpl implements HBaseService {
    private static Admin admin;

    /**
     * 创建表
     *
     * @param tableNmae 表名
     * @param cols      列名
     * @throws IOException
     */
    @Override
    public void createTable(String tableNmae, String[] cols) throws IOException {
        TableName tableName = TableName.valueOf(tableNmae);
        admin = initHbase().getAdmin();
        if (admin.tableExists(tableName)) {
            log.info("表已存在！");
        } else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for (String col : cols) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
        }
    }

    /**
     * 插入数据
     *
     * @param tableName 表名
     * @param user      数据
     * @throws IOException
     */
    @Override
    public void insertData(String tableName, User user) throws IOException {
        TableName tablename = TableName.valueOf(tableName);
        Put put = new Put(("user-" + user.getId()).getBytes());
        //参数：1.列族名  2.列名  3.值
        put.addColumn("information".getBytes(), "username".getBytes(), user.getUsername().getBytes());
        put.addColumn("information".getBytes(), "age".getBytes(), user.getAge().getBytes());
        put.addColumn("information".getBytes(), "gender".getBytes(), user.getGender().getBytes());
        put.addColumn("contact".getBytes(), "phone".getBytes(), user.getPhone().getBytes());
        put.addColumn("contact".getBytes(), "email".getBytes(), user.getEmail().getBytes());
        Table table = initHbase().getTable(tablename);
        table.put(put);
    }

    /**
     * 获取原始数据
     *
     * @param tableName 表名
     * @return
     */
    @Override
    public ResultScanner getNoDealData(String tableName) {
        try {
            Table table = initHbase().getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            return table.getScanner(scan);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据rowKey进行查询
     *
     * @param tableName 表名
     * @param rowKey    行key
     * @return
     * @throws IOException
     */
    @Override
    public User getDataByRowKey(String tableName, String rowKey) throws IOException {

        Table table = initHbase().getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        User user = new User();
        user.setId(rowKey);
        //先判断是否有此条数据
        if (!get.isCheckExistenceOnly()) {
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                if (colName.equals("username")) {
                    user.setUsername(value);
                }
                if (colName.equals("age")) {
                    user.setAge(value);
                }
                if (colName.equals("gender")) {
                    user.setGender(value);
                }
                if (colName.equals("phone")) {
                    user.setPhone(value);
                }
                if (colName.equals("email")) {
                    user.setEmail(value);
                }
            }
        }

        return user;
    }

    /**
     * 查询指定单cell内容
     *
     * @param tableName 表名
     * @param rowKey    行key
     * @param family    所在的列族
     * @param col       指定需要查询的字段
     * @return
     */
    @Override
    public String getCellData(String tableName, String rowKey, String family, String col) {
        String result = null;
        try {
            Table table = initHbase().getTable(TableName.valueOf(tableName));

            Get get = new Get(rowKey.getBytes());
            if (!get.isCheckExistenceOnly()) {
                get.addColumn(Bytes.toBytes(family), Bytes.toBytes(col));
                Result res = table.get(get);
                byte[] resByte = res.getValue(Bytes.toBytes(family), Bytes.toBytes(col));
                result = Bytes.toString(resByte);
            } else {
                result = "查询结果不存在";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 查询指定表名中所有的数据
     *
     * @param tableName 表名
     * @return
     */
    @Override
    public List<User> getAllData(String tableName) throws IOException {

        Table table;
        List<User> list = new ArrayList<>();

        table = initHbase().getTable(TableName.valueOf(tableName));
        ResultScanner results = table.getScanner(new Scan());
        User user;
        for (Result result : results) {
            log.info("用户名:" + new String(result.getRow()));
            user = new User();
            for (Cell cell : result.rawCells()) {
                String row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                user.setId(row);
                if (colName.equals("username")) {
                    user.setUsername(value);
                }
                if (colName.equals("age")) {
                    user.setAge(value);
                }
                if (colName.equals("gender")) {
                    user.setGender(value);
                }
                if (colName.equals("phone")) {
                    user.setPhone(value);
                }
                if (colName.equals("email")) {
                    user.setEmail(value);
                }
            }
            list.add(user);
        }

        return list;
    }

    /**
     * 删除指定cell数据
     *
     * @param tableName 表名
     * @param rowKey    行key
     * @throws IOException
     */
    @Override
    public void deleteByRowKey(String tableName, String rowKey) throws IOException {

        Table table = initHbase().getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除指定列
        table.delete(delete);
    }

    /**
     * 删除表
     *
     * @param tableName 表名
     */
    @Override
    public void deleteTable(String tableName) {
        try {
            TableName tablename = TableName.valueOf(tableName);
            admin = initHbase().getAdmin();
            admin.disableTable(tablename);
            admin.deleteTable(tablename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
