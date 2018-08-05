package com.benchmark.configuration;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.benchmark")
public class CloudConfig extends AbstractCloudConfig {

    @Primary
    @Bean(name = "mysql-db")
    public DataSource mysqlDataSource(){
        return connectionFactory().dataSource("mysql");
    }

    @Bean(name = "postgresql-db")
    public DataSource secondDataSource() {
        return connectionFactory().dataSource("postgresql");
    }
}
