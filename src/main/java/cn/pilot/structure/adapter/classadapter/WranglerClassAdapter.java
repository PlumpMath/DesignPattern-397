package cn.pilot.structure.adapter.classadapter;

import cn.pilot.structure.adapter.JeepAdapter;
import cn.pilot.structure.adapter.Wrangler;

public class WranglerClassAdapter extends Wrangler implements JeepAdapter{
    @Override
    public void dailyDrive() {
        super.twoWheelDrive();
    }
}