package cn.pilot.behavior.state;

public class PausedState extends PlayState {
    public PausedState(Player player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("Going to play the video");
        player.setCurrentState(PlayingState.class.getName());
    }

    @Override
    public void pause() {
        System.out.println("Invalid: it has been paused");
    }
}