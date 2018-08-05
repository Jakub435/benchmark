/*package com.benchmark.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static java.util.Collections.singletonMap;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgreEntityManagerFactory",
        transactionManagerRef = "postgreTransactionManager",
        basePackages = "com.benchmark.PostgreSQL.repo"
)
@EnableTransactionManagement
@ComponentScan(basePackages = "com.benchmark.PostgreSQL")
public class PostgresqlConfig {

    @Bean(name = "postgreEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgreEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                             final @Qualifier("postgresql-db") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.benchmark.PostgreSQL.domain")
                .persistenceUnit("postgreBD")
                .properties(singletonMap("hibernate.hbm2ddl.auto", "update"))
                .build();
    }

    @Bean(name = "postgreTransactionManager")
    public PlatformTransactionManager postgreTransactionManager(@Qualifier("postgreEntityManagerFactory")
                                                                       EntityManagerFactory postgreEntityManagerFactory) {
        return new JpaTransactionManager(postgreEntityManagerFactory);
    }
}
*/