package com.demo2.cloud.commons;

import java.net.URI;
import java.util.Map;

public interface ServiceInstance {

	default String getInstanceId() {
		return null;
	}

	String getServiceId();

	String getHost();

	int getPort();

	boolean isSecure();

	URI getUri();

	Map<String, String> getMetadata();

	default String getScheme() {
		return null;
	}

}
