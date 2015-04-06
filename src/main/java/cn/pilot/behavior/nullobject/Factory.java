package cn.pilot.behavior.nullobject;

public class Factory {
    public static Service getService(String name) {
        NullService defaultService = new NullService();
        switch (name) {
            case "api":
                return new API();
            default:
                return defaultService;
        }
    }
}