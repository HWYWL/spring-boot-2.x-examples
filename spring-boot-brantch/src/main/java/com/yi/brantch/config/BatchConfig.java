package com.yi.brantch.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Batch配置
 * @author  YI
 * @date 2018-8-30 22:05:54
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig implements BatchConfigurer {

    @Override
    public JobRepository getJobRepository() throws Exception {
        return mapJobExplorerFactoryBean().getObject();
    }

    @Bean
    public MapJobRepositoryFactoryBean mapJobExplorerFactoryBean() {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
        try {
            factoryBean.setTransactionManager(getTransactionManager());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return factoryBean;
    }

    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
        return new ResourcelessTransactionManager();
    }

    @Override
    public JobLauncher getJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.afterPropertiesSet();

        return jobLauncher;
    }


    @Override
    public JobExplorer getJobExplorer() throws Exception {
        MapJobExplorerFactoryBean jobExplorerFactoryBean = new MapJobExplorerFactoryBean();
        jobExplorerFactoryBean.setRepositoryFactory(mapJobExplorerFactoryBean());

        return jobExplorerFactoryBean.getObject();
    }

    @Bean
    public JobRegistry jobRegistry() {
        return new MapJobRegistry();
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry());

        return jobRegistryBeanPostProcessor;
    }

}
