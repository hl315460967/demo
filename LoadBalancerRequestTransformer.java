package com.demo2.cloud.commons;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;

@Order(LoadBalancerRequestTransformer.DEFAULT_ORDER)
public interface LoadBalancerRequestTransformer {

	int DEFAULT_ORDER = 0;

	HttpRequest transformRequest(HttpRequest request, ServiceInstance instance);

}