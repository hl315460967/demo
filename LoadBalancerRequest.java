package com.demo2.cloud.commons;

public interface LoadBalancerRequest<T> {

	T apply(ServiceInstance instance) throws Exception;

}