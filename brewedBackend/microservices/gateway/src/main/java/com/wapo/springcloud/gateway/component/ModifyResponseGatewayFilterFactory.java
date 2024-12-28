package com.wapo.springcloud.gateway.component;

import com.wapo.springcloud.gateway.service.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class ModifyResponseGatewayFilterFactory extends AbstractGatewayFilterFactory<ModifyResponseGatewayFilterFactory.Config> {
    @Autowired
    private SecurityContextService securityContextService;

    public ModifyResponseGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        String username = securityContextService.getUsername();
        return (exchange, chain) -> {
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(r -> r.header("Auth-User", username))
                    .build();
            return chain.filter(modifiedExchange);
        };
    }

    public static class Config {
    }
}
