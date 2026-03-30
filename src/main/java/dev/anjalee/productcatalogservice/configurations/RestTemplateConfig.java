package dev.anjalee.productcatalogservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    /*
    In configuration, we tell spring to create some library object and keep it
   /manage its lifecycle
     */

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
