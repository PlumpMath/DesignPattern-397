package cn.pilot.behavior.state;

import java.util.HashMap;
import java.util.Map;

public class Player implements Playable {
    private PlayState current;
    private Map<String, PlayState> states;

    public Player() {
        states = new HashMap<>();
        states.put(BufferingState.class.getName(), new BufferingState(this));
        states.put(PlayingState.class.getName(), new PlayingState(this));
        states.put(PausedState.class.getName(), new PausedState(this));

        this.current = states.get(BufferingState.class.getName());
    }

    public void setCurrentState(String state) {
        this.current = states.get(state);
    }

    @Override
    public void play() {
        current.play();
    }

    @Override
    public void pause() {
        current.pause();
    }
}