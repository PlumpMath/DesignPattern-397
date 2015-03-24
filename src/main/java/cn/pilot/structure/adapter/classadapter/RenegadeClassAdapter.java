package cn.pilot.structure.adapter.classadapter;

import cn.pilot.structure.adapter.JeepAdapter;
import cn.pilot.structure.adapter.Renegade;

public class RenegadeClassAdapter extends Renegade implements JeepAdapter {
    @Override
    public void dailyDrive() {
        super.drive();
    }
}