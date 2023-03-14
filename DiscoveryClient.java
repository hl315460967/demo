package com.demo2.cloud.commons;

import com.demo2.cloud.commons.ServiceInstance;
import org.springframework.core.Ordered;

import java.util.List;

public interface DiscoveryClient extends Ordered {

    int DEFAULT_ORDER = 0;

    List<ServiceInstance> getInstances(String serviceName);

    @Override
    default int getOrder() {
        return DEFAULT_ORDER;
    }
}
