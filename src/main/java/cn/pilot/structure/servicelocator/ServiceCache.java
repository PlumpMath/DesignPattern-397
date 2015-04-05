package cn.pilot.structure.servicelocator;

import java.util.HashMap;
import java.util.Map;

public class ServiceCache {
    private Map<String, Service> cache = new HashMap<>();

    public Service getService(String jndiName) {
        return cache.get(jndiName);
    }

    public void addService(String jndiName, Service service) {
        cache.put(jndiName, service);
    }
}