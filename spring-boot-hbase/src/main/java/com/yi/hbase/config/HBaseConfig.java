package com.yi.hbase.config;

import lombok.Data;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * HBase配置
 *
 * @author huangwenyi
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.hbase")
public class HBaseConfig {

    /**
     * zk集群地址
     */
    private static String quorum;
    /**
     * hbase根目录
     */
    private static String rootDir;
    /**
     * zk节点目录
     */
    private static String nodeParent;

    /**
     * 连接集群
     *
     * @return
     * @throws IOException
     */
    public static Connection initHbase() throws IOException {

        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        // 单机配置
//        configuration.set("hbase.zookeeper.property.clientPort", "2181");
//        configuration.set("hbase.zookeeper.quorum", "127.0.0.1");
        //集群配置↓
        configuration.set("hbase.zookeeper.quorum", quorum);
        Connection connection = ConnectionFactory.createConnection(configuration);
        return connection;
    }

    public void setQuorum(String quorum) {
        HBaseConfig.quorum = quorum;
    }

    public void setRootDir(String rootDir) {
        HBaseConfig.rootDir = rootDir;
    }

    public void setNodeParent(String nodeParent) {
        HBaseConfig.nodeParent = nodeParent;
    }
}
