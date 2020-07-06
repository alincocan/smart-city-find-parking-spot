package com.smartcity.parking.security;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String userId;
        try {
            userId = TokenProvider.getUserIdFromToken(authToken);
        } catch (Exception e) {
            log.error("AuthenticationManager.authenticate {} ", e.getMessage(), e);
            userId = null;
        }
        if (userId != null && TokenProvider.validateToken(authToken)) {
            Claims claims = TokenProvider.getAllClaimsFromToken(authToken);
            List<GrantedAuthority> authorities = Collections.emptyList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userId,
                userId,
                authorities
            );
            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}
