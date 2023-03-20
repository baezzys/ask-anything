package com.example.gateway.oauth;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KakaoIntrospector implements ReactiveOpaqueTokenIntrospector {

    private final String kakaoTokenInfoEndpoint = "https://kapi.kakao.com/v1/user/access_token_info";
    private final String kakaoClientId = "YOUR_KAKAO_CLIENT_ID";

    public Mono<OAuth2AuthenticatedPrincipal> introspect(String access_token) {
        return WebClient.create(kakaoTokenInfoEndpoint)
                .get()
                .headers(headers -> headers.setBearerAuth(access_token))
                .retrieve()
                .onStatus(status -> status.equals(HttpStatus.UNAUTHORIZED),
                        clientResponse -> Mono.error(new IntrospectionException("Invalid token")))
                .bodyToMono(Map.class)
                .flatMap(
                        kakaoIntrospectionResponse -> {
                            Map<String, Object> attributes = getAttributes(kakaoIntrospectionResponse);

                            List<GrantedAuthority> authorities =
                                    parseScopeAuthorities(attributes.get("scope").toString(), attributes);
                            authorities.add(new OAuth2UserAuthority("ROLE_USER", attributes));

                            return Mono.just(
                                    new OAuth2IntrospectionAuthenticatedPrincipal(attributes, authorities));
                        });
    }

    private Map<String, Object> getAttributes(Map<String, Object> kakaoIntrospectionResponse) {
        return Map.of(
                "aud", kakaoClientId,
                "sub", kakaoIntrospectionResponse.get("id"),
                "email", kakaoIntrospectionResponse.get("email"),
                "name", kakaoIntrospectionResponse.get("properties.name"));
    }

    private List<GrantedAuthority> parseScopeAuthorities(
            String oauth2Scopes, Map<String, Object> attributes) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (oauth2Scopes == null) {
            return authorities;
        }
        String[] scopes = oauth2Scopes.split(",");

        for (int i = 0; i < scopes.length; i++) {
            authorities.add(new OAuth2UserAuthority("SCOPE_" + scopes[i], attributes));
        }

        return authorities;
    }
}
