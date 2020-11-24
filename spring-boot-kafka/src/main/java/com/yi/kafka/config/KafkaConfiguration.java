package com.yi.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;


/**
 * Kafka 初始化
 * @author YI
 * @data 2018-8-11 11:19:57
 */
@Slf4j
@Configuration
@EnableKafka
@ComponentScan(value = {"com.yi.kafka"})
public class KafkaConfiguration {

}
