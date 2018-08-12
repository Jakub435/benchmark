package com.benchmark.configuration;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.benchmark.Neo4j")
@EnableNeo4jRepositories("com.benchmark.Neo4j.repo")
public class Neo4jConfig {
/*
    @Value(value = "${neo4j.username}")
    private String username;

    @Value(value = "${neo4j.password}")
    private String pass;
*/
    @Value(value = "${neo4j.uri}")
    private String uri;

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "com.benchmark.Neo4j.domain");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() throws Exception {
        return new Neo4jTransactionManager(sessionFactory());
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        return new org.neo4j.ogm.config.Configuration.Builder()
                .uri(uri)
                //.credentials(username, pass)
                .build();
    }
}
