package com.suman.linkedin.api_gateway.filters;

import com.suman.linkedin.api_gateway.security.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JWTService jwtService;

    public AuthenticationFilter(JWTService jwtService){
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info("Login request: {}", exchange.getRequest().getURI());
            final String tokenHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            // Check for space after Bearer to be safe
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("Authorization token header not found or invalid.");
                return exchange.getResponse().setComplete();
            }

            try {
                // Safer than .split()
                final String token = tokenHeader.substring(7);
                String userId = jwtService.getUserIdFromToken(token);

                // 1. Save the mutated exchange to a new variable
                ServerWebExchange mutatedExchange = exchange.mutate()
                        .request(r -> r.header("X-User-Id", userId))
                        .build();

                // 2. Pass the NEW mutated exchange down the chain
                return chain.filter(mutatedExchange);

            } catch (Exception e) {
                // Catch Exception instead of JwtException to prevent Netty socket crashes
                log.error("Authentication Exception: {}", e.getLocalizedMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        });
    }

    public static class Config {
        // Empty class is perfectly fine here
    }
}