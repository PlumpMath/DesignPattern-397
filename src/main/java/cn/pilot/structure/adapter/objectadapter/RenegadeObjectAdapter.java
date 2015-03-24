package cn.pilot.structure.adapter.objectadapter;

import cn.pilot.structure.adapter.JeepAdapter;
import cn.pilot.structure.adapter.Renegade;

public class RenegadeObjectAdapter implements JeepAdapter {
    private Renegade renegade;

    public RenegadeObjectAdapter() {
        this.renegade = new Renegade();
    }

    @Override
    public void dailyDrive() {
        renegade.drive();
    }
}