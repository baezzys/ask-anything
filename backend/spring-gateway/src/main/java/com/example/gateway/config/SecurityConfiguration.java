package com.example.gateway.config;

import java.beans.IntrospectionException;
import java.util.*;

import com.example.gateway.dto.GoogleIntrospectionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    Log log = LogFactory.getLog(SecurityConfiguration.class);
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
                        )
                );

        return http.build();
    }

    private class GoogleIntrospector implements ReactiveOpaqueTokenIntrospector {

        private final String googleTokenInfoEndpoint = "https://oauth2.googleapis.com/tokeninfo";

        public Mono<OAuth2AuthenticatedPrincipal> introspect(String access_token) {
            return WebClient.create(googleTokenInfoEndpoint)
                    .get()
                    .uri(uriBuilder -> uriBuilder.queryParam("id_token", access_token).build())
                    .retrieve()
                    .onStatus(
                            status -> status.equals(HttpStatus.BAD_REQUEST),
                            clientResponse -> Mono.error(new IntrospectionException("Invalid token")))
                    .bodyToMono(GoogleIntrospectionResponse.class)
                    .flatMap(
                            googleIntrospectionResponse -> {
                                Map<String, Object> attributes = getAttributes(googleIntrospectionResponse);

                                List<GrantedAuthority> authorities =
                                        parseScopeAuthorities(((GoogleIntrospectionResponse)googleIntrospectionResponse).getScope(), attributes);
                                authorities.add(new OAuth2UserAuthority("ROLE_USER", attributes));

                                return Mono.just(
                                        new OAuth2IntrospectionAuthenticatedPrincipal(attributes, authorities));
                            });
        }

        private Map<String, Object> getAttributes(
                GoogleIntrospectionResponse googleIntrospectionResponse) {
            return Map.of(
                    "aud", googleIntrospectionResponse.getAud(),
                    "sub", googleIntrospectionResponse.getSub(),
                    "email", googleIntrospectionResponse.getEmail(),
                    "email_verified", googleIntrospectionResponse.getEmail_verified(),
                    "name", googleIntrospectionResponse.getName());
        }

        private List<GrantedAuthority> parseScopeAuthorities(
                String oauth2Scopes, Map<String, Object> attributes) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            if (oauth2Scopes == null) {
                return authorities;
            }
            String[] scopes = oauth2Scopes.split(" ");

            for (int i = 0; i < scopes.length; i++) {
                authorities.add(new OAuth2UserAuthority("SCOPE_" + scopes[i], attributes));
            }

            return authorities;
        }
    }
}