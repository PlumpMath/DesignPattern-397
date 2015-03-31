package cn.pilot.behavior.state;

public class PlayingState extends PlayerState {
    public PlayingState(StateContext stateContext) {
        super(stateContext);
    }

    @Override
    public void play() {
        System.out.println("Invalid: It is playing");
    }

    @Override
    public void pause() {
        System.out.println("Going to pause the video");
        stateContext.setCurrentState(PausedState.class.getName());
    }
}