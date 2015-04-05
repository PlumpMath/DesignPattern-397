package cn.pilot.structure.servicelocator;

public class InitialContext {
    public Object lookup(String jndiName) {
        // simple dummy implementation
        switch (jndiName) {
            case "jndi/serviceA":
                return new DummyService("serviceA");
            case "jndi/serviceB":
                return new DummyService("serviceB");
            default:
                return null;
        }
    }
}