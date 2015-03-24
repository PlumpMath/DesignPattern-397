package cn.pilot.structure.adapter.classadapter;

import cn.pilot.structure.adapter.JeepAdapter;

public class Demo {
    public static void main(String[] args) {
        JeepAdapter wrangler = new WranglerClassAdapter();
        wrangler.dailyDrive();

        JeepAdapter renegade = new RenegadeClassAdapter();
        renegade.dailyDrive();
    }
}