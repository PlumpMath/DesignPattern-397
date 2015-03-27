package cn.pilot.behavior.chain.filter;

public class FilterChain {
    private Filter[] filters;
    private int index = -1;

    public FilterChain(Filter[] filters) {
        this.filters = filters;
    }

    public void doFilter() {
        if (index < -1 || index >= filters.length) {
            return;
        }

        final int length = filters.length;
        while (++index < length) {
            filters[index].doFilter(this);
        }
    }
}