package com.example.gateway.config;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class SecurityConfiguration {

    Log log = LogFactory.getLog(SecurityConfiguration.class);

    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/actuator/**")
                .access(
                        (authenticationMono, context) ->
                                Mono.just(new AuthorizationDecision(true))
                                        .doOnNext((authn) -> this.log.info("hello")))
                .anyExchange()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .opaqueToken()
                .introspector(new GoogleIntrospector())
                .and()
                .and()
                .httpBasic()
                .disable()
                .formLogin()
                .disable()
                .csrf()
                .disable();
        return http.build();
    }

    private class GoogleIntrospector implements ReactiveOpaqueTokenIntrospector {

        private final String googleTokenInfoEndpoint = "https://oauth2.googleapis.com/tokeninfo";

        private record GoogleIntrospectionResponse(
                String azp,
                String aud,
                String sub,
                String scope,
                String exp,
                String expires_in,
                String email,
                String email_verified,
                String access_type) {}

        private record GoogleIntrospectionError(String error, String error_description) {}

        public Mono<OAuth2AuthenticatedPrincipal> introspect(String access_token) {
            return WebClient.create(googleTokenInfoEndpoint)
                    .get()
                    .uri(uriBuilder -> uriBuilder.queryParam("access_token", access_token).build())
                    .retrieve()
                    .onStatus(
                            status -> status.equals(HttpStatus.BAD_REQUEST),
                            clientResponse -> Mono.error(new IntrospectionException("Invalid token")))
                    .bodyToMono(GoogleIntrospectionResponse.class)
                    .doOnNext(
                            googleIntrospectionResponse -> {
                                log.info(googleIntrospectionResponse);
                            })
                    .flatMap(
                            googleIntrospectionResponse -> {
                                Map<String, Object> attributes = getAttributes(googleIntrospectionResponse);

                                List<GrantedAuthority> authorities =
                                        parseScopeAuthorities(googleIntrospectionResponse.scope, attributes);
                                authorities.add(new OAuth2UserAuthority("ROLE_USER", attributes));

                                return Mono.just(
                                        new OAuth2IntrospectionAuthenticatedPrincipal(attributes, authorities));
                            });
        }

        private Map<String, Object> getAttributes(
                GoogleIntrospectionResponse googleIntrospectionResponse) {
            return Map.of(
                    "aud", googleIntrospectionResponse.aud,
                    "sub", googleIntrospectionResponse.sub,
                    "email", googleIntrospectionResponse.email,
                    "email_verified", googleIntrospectionResponse.email_verified);
        }

        private List<GrantedAuthority> parseScopeAuthorities(
                String oauth2Scopes, Map<String, Object> attributes) {
            String[] scopes = oauth2Scopes.split(" ");
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            for (int i = 0; i < scopes.length; i++) {
                authorities.add(new OAuth2UserAuthority("SCOPE_" + scopes[i], attributes));
            }

            return authorities;
        }
    }
}