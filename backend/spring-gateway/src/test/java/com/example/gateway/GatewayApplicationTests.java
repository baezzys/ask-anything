package com.example.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootTest
@EnableR2dbcRepositories
@EnableAutoConfiguration
class GatewayApplicationTests {

	@Test
	void contextLoads() {
	}

}
