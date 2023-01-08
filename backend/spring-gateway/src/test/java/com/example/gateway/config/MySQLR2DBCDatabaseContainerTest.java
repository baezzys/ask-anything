package com.example.gateway.config;

import io.r2dbc.spi.ConnectionFactoryOptions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.MySQLR2DBCDatabaseContainer;


public class MySQLR2DBCDatabaseContainerTest extends AbstractR2DBCDatabaseContainerTest<MySQLContainer<?>> {

    @Override
    protected ConnectionFactoryOptions getOptions(MySQLContainer<?> container) {
        return MySQLR2DBCDatabaseContainer.getOptions(container);
    }

    @Override
    protected String createR2DBCUrl() {
        return "r2dbc:tc:mysql:///ask_anything?TC_IMAGE_TAG=5.7.22";
    }

    @Override
    protected MySQLContainer<?> createContainer() {
        return new MySQLContainer<>();
    }

}