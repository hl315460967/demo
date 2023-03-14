package com.demo2.cloud.commons;

import java.io.IOException;
import java.net.URI;

public interface LoadBalancerClient extends ServiceInstanceChooser {

	/**
	 * 使用来自 LoadBalancer 的 ServiceInstance 为指定的请求执行请求服务。
	 * @param serviceId 查找 LoadBalancer 的服务 ID。
	 * @param request 允许实现执行前后动作，例如递增指标。
	 * @param <T> 响应类型
	 * @throws IOException 以防出现 IO 问题。
	 * @return LoadBalancerRequest 回调的结果服务实例。
	 */
	<T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException;

	/**
	 * 使用来自 LoadBalancer 的 ServiceInstance 为指定的请求执行请求服务。
	 * @param serviceId 查找 LoadBalancer 的服务 ID。
	 * @param serviceInstance 要执行请求的服务。
	 * @param request 允许实现执行前后动作，例如递增指标。
	 * @param <T> 响应类型
	 * @throws IOException 以防出现 IO 问题。
	 * @return LoadBalancerRequest 回调的结果服务实例。
	 */
	<T> T execute(String serviceId, ServiceInstance serviceInstance,
			LoadBalancerRequest<T> request) throws IOException;

	/**
	 * 使用真实主机和端口创建适当的 URI 供系统使用。
	 * 一些系统使用具有逻辑服务名称的 URI 作为主机，例如
	 * http://myservice/path/to/service. 这会将服务名称替换为host:port
	 * @param instance 服务实例重构URI
	 * @param original 以主机作为逻辑服务名称的 URI。
	 * @return 一个重构的 URI。
	 */
	URI reconstructURI(ServiceInstance instance, URI original);

}
