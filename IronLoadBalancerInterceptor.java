package com.demo2.cloud.client.ironinvoke.intcept;

import com.demo2.cloud.commons.LoadBalancerClient;
import com.demo2.cloud.commons.LoadBalancerRequestFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.URI;

public class IronLoadBalancerInterceptor implements ClientHttpRequestInterceptor {
    private LoadBalancerClient loadBalancerClient;
 
    private LoadBalancerRequestFactory requestFactory;
 
    public IronLoadBalancerInterceptor(LoadBalancerClient loadBalancerClient,
                                       LoadBalancerRequestFactory requestFactory) {
        this.loadBalancerClient = loadBalancerClient;
        this.requestFactory = requestFactory;
    }
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        final URI originalUri = request.getURI();
        String serviceName = originalUri.getHost();
        return this.loadBalancerClient.execute(serviceName,
                this.requestFactory.createRequest(request, body, execution));
    }
}