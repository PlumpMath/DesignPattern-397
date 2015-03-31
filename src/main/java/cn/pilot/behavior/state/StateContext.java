package cn.pilot.behavior.state;

import java.util.HashMap;
import java.util.Map;

public class StateContext {
    private Map<String, PlayerState> states;
    private PlayerState current; // in reality, this should be synchronized

    public StateContext() {
        states = new HashMap<>();
        states.put(BufferingState.class.getName(), new BufferingState(this));
        states.put(PlayingState.class.getName(), new PlayingState(this));
        states.put(PausedState.class.getName(), new PausedState(this));

        this.current = states.get(BufferingState.class.getName());
    }

    public PlayerState getCurrentState() {
        return this.current;
    }

    public void setCurrentState(String state) {
        this.current = states.get(state);
    }
}