package com.demo2.cloud.client.ironinvoke.intcept;

import com.demo2.cloud.commons.DiscoveryClient;
import com.demo2.cloud.client.ironinvoke.LoadBalance;
import com.demo2.cloud.commons.LoadBalancerClient;
import com.demo2.cloud.commons.LoadBalancerRequest;
import com.demo2.cloud.commons.ServiceInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class IronLoadBalancerClient implements LoadBalancerClient {

    @Autowired
    AbstractEnvironment environment;
    @Autowired
    DiscoveryClient discoveryClient;
 
    @Override
    public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
        ServiceInstance server = this.choose(serviceId);
        return execute(serviceId, server, request);
    }
	
    @Override
    public <T> T execute(String serviceId, ServiceInstance serviceInstance, LoadBalancerRequest<T> request) throws IOException {
        T returnVal = null;
        try {
            returnVal = request.apply(serviceInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVal;
    }
    
    @Override
    public URI reconstructURI(ServiceInstance instance, URI original) {
        String host = instance.getHost();
        int port = instance.getPort();
        if (host.equals(original.getHost())
                && port == original.getPort()
                ) {
            return original;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("http").append("://");
            if (!StringUtils.isNotBlank(original.getRawUserInfo())) {
                sb.append(original.getRawUserInfo()).append("@");
            }
            sb.append(host);
            if (port >= 0) {
                sb.append(":").append(port);
            }
            sb.append(original.getRawPath());
            if (!StringUtils.isNotBlank(original.getRawQuery())) {
                sb.append("?").append(original.getRawQuery());
            }
            if (!StringUtils.isNotBlank(original.getRawFragment())) {
                sb.append("#").append(original.getRawFragment());
            }
            URI newURI = new URI(sb.toString());
            return newURI;
        }catch (URISyntaxException e){
            throw new RuntimeException(e);
        }
    }
 
    @Override
    public ServiceInstance choose(String serviceId) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
        int modulo = LoadBalance.incrementAndGetModulo(serviceInstances.size());
        return serviceInstances.get(modulo);
    }
}