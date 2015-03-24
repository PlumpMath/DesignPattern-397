package cn.pilot.structure.adapter.objectadapter;

import cn.pilot.structure.adapter.JeepAdapter;

/**
 * Adapter can fix the issue that we don't want to make any changes to current class
 *
 * Wrangler and Renegade have different methods but we want to an adapter to call its own method
 */
public class Demo {
    public static void main(String[] args) {
        JeepAdapter wrangler = new WranglerObjectAdapter();
        wrangler.dailyDrive();

        JeepAdapter renegade = new RenegadeObjectAdapter();
        renegade.dailyDrive();
    }
}