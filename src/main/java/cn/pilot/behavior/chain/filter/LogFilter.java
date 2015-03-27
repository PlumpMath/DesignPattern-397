package cn.pilot.behavior.chain.filter;

public class LogFilter implements Filter {
    @Override
    public void doFilter(FilterChain filterChain) {
        System.out.println("LogFilter: logging ip address");
        filterChain.doFilter();
    }
}