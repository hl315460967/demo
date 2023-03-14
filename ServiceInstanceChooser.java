package com.demo2.cloud.commons;

public interface ServiceInstanceChooser {

	/**
	 * 从 LoadBalancer 中为指定服务选择一个 ServiceInstance。
	 * @param serviceId 查找 LoadBalancer 的服务 ID。
	 * @return 与 serviceId 匹配的 ServiceInstance。
	 */
	ServiceInstance choose(String serviceId);

}