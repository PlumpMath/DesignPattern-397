package cn.pilot.behavior.state;

public class PlayingState extends PlayState {
    public PlayingState(Player player) {
        super(player);
    }

    @Override
    public void play() {
        System.out.println("Invalid: It is playing");
    }

    @Override
    public void pause() {
        System.out.println("Going to pause the video");
        player.setCurrentState(PausedState.class.getName());
    }
}