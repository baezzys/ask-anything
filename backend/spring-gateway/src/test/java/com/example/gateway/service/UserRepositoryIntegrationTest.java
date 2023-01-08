package com.example.gateway.service;

import com.example.gateway.domain.User;
import com.example.gateway.repository.UserRepository;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.MySQLR2DBCDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataR2dbcTest
@Testcontainers
@ActiveProfiles("test")
class UserRepositoryIntegrationTest {


    @TestConfiguration
    static class TestConfig {
        @Bean
        public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

            ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
            initializer.setConnectionFactory(connectionFactory);

            CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
            populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
            initializer.setDatabasePopulator(populator);

            return initializer;
        }
    }

    @Autowired
    DatabaseClient client;

    @Autowired
    UserRepository userRepository;

    @Test
    void T() {


        User user = new User("jinwoo", "test");
        userRepository.save(user);

    }
}