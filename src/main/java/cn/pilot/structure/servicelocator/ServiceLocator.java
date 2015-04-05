package cn.pilot.structure.servicelocator;

public class ServiceLocator {
    private static ServiceCache serviceCache = new ServiceCache();

    public static Service getService(String jndiName) {
        Service service = serviceCache.getService(jndiName);

        if (service == null) {
            InitialContext ctx = new InitialContext();
            service = (Service) ctx.lookup(jndiName);

            serviceCache.addService(jndiName, service);
        }

        return service;
    }
}