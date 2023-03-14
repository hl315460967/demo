package com.demo2.cloud.client.ironinvoke.intcept;

import com.demo2.cloud.commons.LoadBalancerClient;
import com.demo2.cloud.commons.LoadBalancerRequestFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class IronInvokeIntceptConfiguration {

    @Bean
    public LoadBalancerClient loadBalancerClient(){
        return new IronLoadBalancerClient();
    }

    @IronLoadBalanced
    @Autowired(required = false)
    private List<RestTemplate> restTemplates = Collections.emptyList();

    @Bean
    public LoadBalancerRequestFactory loadBalancerRequestFactory(
            LoadBalancerClient loadBalancerClient) {
        return new LoadBalancerRequestFactory(loadBalancerClient);
    }

    @Bean
    public IronLoadBalancerInterceptor ironLoadBalancerInterceptor(
            LoadBalancerClient loadBalancerClient,
            LoadBalancerRequestFactory requestFactory){
        return new IronLoadBalancerInterceptor(loadBalancerClient,requestFactory);
    }

    @Bean
    public SmartInitializingSingleton smartInitializingSingleton(
            final IronLoadBalancerInterceptor ironLoadBalancerInterceptor){
        return ()->{
            for (RestTemplate restTemplate : IronInvokeIntceptConfiguration.this.restTemplates) {
                List<ClientHttpRequestInterceptor> list = new ArrayList<>(
                        restTemplate.getInterceptors());
                list.add(ironLoadBalancerInterceptor);
                restTemplate.setInterceptors(list);
            }
        };
    }

}