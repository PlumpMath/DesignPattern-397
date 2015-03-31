package cn.pilot.behavior.state;

import java.util.HashMap;
import java.util.Map;

public class Player implements Playable {
    private StateContext stateContext;

    public Player() {
         stateContext = new StateContext();
    }

    @Override
    public void play() {
        stateContext.getCurrentState().play();
    }

    @Override
    public void pause() {
        stateContext.getCurrentState().pause();
    }
}