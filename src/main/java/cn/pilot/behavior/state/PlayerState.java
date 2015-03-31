package cn.pilot.behavior.state;

public abstract class PlayerState implements Playable{
    protected StateContext stateContext;

    public PlayerState(StateContext stateContext) {
        this.stateContext = stateContext;
    }
}