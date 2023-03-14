package com.demo2.cloud.client.ironinvoke.intcept;

import com.demo2.cloud.commons.ServiceInstance;

import java.net.URI;
import java.util.Map;

public class Server implements ServiceInstance {
 
    private String serviceId;
    private String instanceId;
    private String host;
    private int port;
 
    public Server(String serviceId, String instanceId, String host, int port) {
        this.serviceId = serviceId;
        this.instanceId = instanceId;
        this.host = host;
        this.port = port;
    }
 
    public Server() {
    }
 
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
 
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
 
    public void setHost(String host) {
        this.host = host;
    }
 
    public void setPort(int port) {
        this.port = port;
    }
 
    @Override
    public String getInstanceId() {
        return null;
    }
 
    @Override
    public String getServiceId() {
        return null;
    }
 
    @Override
    public String getHost() {
        return host;
    }
 
    @Override
    public int getPort() {
        return port;
    }
 
    @Override
    public boolean isSecure() {
        return false;
    }
 
    @Override
    public URI getUri() {
        return null;
    }
 
    @Override
    public Map<String, String> getMetadata() {
        return null;
    }
 
    @Override
    public String getScheme() {
        return null;
    }
}