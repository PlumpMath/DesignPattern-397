package cn.pilot.structure.servicelocator;

public class Demo {
    public static void main(String[] args) {
        Service serviceA = ServiceLocator.getService("jndi/serviceA");
        System.out.printf("ServiceA address: %s\n", serviceA);

        Service serviceB = ServiceLocator.getService("jndi/serviceB");
        System.out.printf("ServiceB address: %s\n", serviceB);

        Service serviceAInCache = ServiceLocator.getService("jndi/serviceA");
        System.out.printf("ServiceAInCache address: %s\n", serviceA);
    }
}