package com.demo2.cloud.commons;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.util.List;

public class LoadBalancerRequestFactory {

	private LoadBalancerClient loadBalancer;

	private List<LoadBalancerRequestTransformer> transformers;

	public LoadBalancerRequestFactory(LoadBalancerClient loadBalancer,
			List<LoadBalancerRequestTransformer> transformers) {
		this.loadBalancer = loadBalancer;
		this.transformers = transformers;
	}

	public LoadBalancerRequestFactory(LoadBalancerClient loadBalancer) {
		this.loadBalancer = loadBalancer;
	}

	public LoadBalancerRequest<ClientHttpResponse> createRequest(
			final HttpRequest request, final byte[] body,
			final ClientHttpRequestExecution execution) {
		return instance -> {
			HttpRequest serviceRequest = new ServiceRequestWrapper(request, instance,
					this.loadBalancer);
			if (this.transformers != null) {
				for (LoadBalancerRequestTransformer transformer : this.transformers) {
					serviceRequest = transformer.transformRequest(serviceRequest,
							instance);
				}
			}
			return execution.execute(serviceRequest, body);
		};
	}

}
