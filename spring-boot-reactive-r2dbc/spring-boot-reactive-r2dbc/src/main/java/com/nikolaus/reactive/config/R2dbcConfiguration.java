package com.nikolaus.reactive.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

@Configuration
public class R2dbcConfiguration {

    private final ConnectionFactory connectionFactory;

    public R2dbcConfiguration(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate() {
        return new R2dbcEntityTemplate(connectionFactory);
    }
}