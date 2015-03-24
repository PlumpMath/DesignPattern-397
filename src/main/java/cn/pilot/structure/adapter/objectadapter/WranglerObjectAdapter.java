package cn.pilot.structure.adapter.objectadapter;

import cn.pilot.structure.adapter.JeepAdapter;
import cn.pilot.structure.adapter.Wrangler;

public class WranglerObjectAdapter implements JeepAdapter {
    private Wrangler wrangler;

    public WranglerObjectAdapter() {
        this.wrangler = new Wrangler();
    }

    @Override
    public void dailyDrive() {
        wrangler.twoWheelDrive();
    }
}