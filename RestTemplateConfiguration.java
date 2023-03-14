package com.demo2.cloud.client.ironinvoke.intcept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    @IronLoadBalanced
    public RestTemplate ironInvokerRestTemplate() {
        return new RestTemplate();
    }

}
