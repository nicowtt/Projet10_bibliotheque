package com.eLibraryBatch.batchMailproxies.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public BasicAuthRequestInterceptor mBasicAuthentificationInterceptor() {
        return new BasicAuthRequestInterceptor("utilisateur", "mdp");
    }
}
