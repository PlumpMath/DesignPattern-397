package cn.pilot.behavior.state;

public class PausedState extends PlayerState {
    public PausedState(StateContext stateContext) {
        super(stateContext);
    }

    @Override
    public void play() {
        System.out.println("Going to play the video");
        stateContext.setCurrentState(PlayingState.class.getName());
    }

    @Override
    public void pause() {
        System.out.println("Invalid: it has been paused");
    }
}