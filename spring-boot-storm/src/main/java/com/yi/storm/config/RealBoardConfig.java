package com.yi.storm.config;

import com.yi.storm.bolt.CountMoneyBolt;
import lombok.Data;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Storm 配置
 *
 * @author huangwenyi
 * @date 2019-9-3 10:18:20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "custom.storm.kafka")
public class RealBoardConfig {

    /**
     * Topology 名称
     */
    String name;
    /**
     * kafka集群
     */
    String bootstrapServers;
    /**
     * topics 名称
     */
    String[] topics;

    @Bean
    public void realBoard() throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        KafkaSpoutConfig.Builder<String, String> kafkaSpoutConfigBuilder = KafkaSpoutConfig.builder(bootstrapServers, topics);

        kafkaSpoutConfigBuilder.setOffsetCommitPeriodMs(1000);
        kafkaSpoutConfigBuilder.setGroupId("order_group");
        kafkaSpoutConfigBuilder.setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.UNCOMMITTED_LATEST);

        KafkaSpoutConfig<String, String> kafakSpoutConfig = kafkaSpoutConfigBuilder.build();


        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<>(kafakSpoutConfig);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafkaSpout", kafkaSpout);
        builder.setBolt("countMoneyBolt", new CountMoneyBolt()).localOrShuffleGrouping("kafkaSpout");
        Config config = new Config();

        if (!StringUtils.isEmpty(name)) {
            // 集群模式
            StormSubmitter.submitTopology(name, config, builder.createTopology());
        } else {
            // 本地运行模式
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("countMoney", config, builder.createTopology());
        }
    }
}
