package com.microservices.paymentModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean("accountRest")
    public RestTemplate registryAccountRestTemplate(){
        return new RestTemplate();
    }
}
