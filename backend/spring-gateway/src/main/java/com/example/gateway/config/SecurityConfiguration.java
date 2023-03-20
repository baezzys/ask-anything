package com.example.gateway.config;

import com.example.gateway.oauth.GoogleIntrospector;
import com.example.gateway.oauth.KakaoIntrospector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http) {
        http
                .cors()
                .and()
                .csrf()
                .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .opaqueToken(opaqueToken -> opaqueToken
                                .introspector(new GoogleIntrospector())
                                .introspector(new KakaoIntrospector())
                        )
                );

        return http.build();
    }
}
