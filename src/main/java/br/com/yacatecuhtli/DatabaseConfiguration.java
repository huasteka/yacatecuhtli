package br.com.yacatecuhtli;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(DatabaseConfiguration.JPA_REPOSITORY_PACKAGE)
@EnableTransactionManagement
public class DatabaseConfiguration {

    static final String JPA_REPOSITORY_PACKAGE = "br.com.yacatecuhtli.domain";

}
