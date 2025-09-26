package com.playground.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
// @EnableBatchProcessing
// Removing @EnableBatchProcessing should make Spring Boot take control on how to configure Spring Batch,
// including the Batch schema creation in the autoconfigured data source.
public class BatchInfrastructureConfig {

    /**
     * The datasource that both the JobRepository *and* your Steps will use.
     * Spring Boot creates this bean automatically from application.yml,
     * so you only need it injected.
     */
    private final DataSource dataSource;

    public BatchInfrastructureConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Spring Batch 5 no longer creates a transaction manager for you.
     * Expose one explicitly so that both the infrastructure
     * and any Step definitions can reuse it.
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
