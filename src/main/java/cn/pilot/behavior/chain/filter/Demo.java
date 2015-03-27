package cn.pilot.behavior.chain.filter;

public class Demo {
    public static void main(String[] args) {
        // in reality, this will be configured in web.xml
        Filter[] filters = {new UserRoleFilter(), new LogFilter()};

        FilterChain filterChain = new FilterChain(filters);

        filterChain.doFilter();
    }
}