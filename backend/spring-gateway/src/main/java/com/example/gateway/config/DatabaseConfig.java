package com.example.gateway.config;

import com.github.jasync.r2dbc.mysql.MysqlConnectionFactoryProvider;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions factoryOptions = ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.HOST, "ask-anything-db")
                .option(ConnectionFactoryOptions.SSL, Boolean.FALSE)
                .option(ConnectionFactoryOptions.PASSWORD, "root")
                .option(ConnectionFactoryOptions.DATABASE, "ask_anything")
                .option(ConnectionFactoryOptions.PORT, 3306)
                .option(ConnectionFactoryOptions.USER, "root")
                .build();
        return new MysqlConnectionFactoryProvider().create(factoryOptions);
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }
}
