package cn.pilot.behavior.chain.filter;

public class UserRoleFilter implements Filter {
    @Override
    public void doFilter(FilterChain filterChain) {
        System.out.println("UserRoleFilter: Added user info to session before calling next chain");

        // if user is not authorized, return;
        filterChain.doFilter();

        System.out.println("UserRoleFilter: Other stuffs could be also done after calling next chain");
    }
}