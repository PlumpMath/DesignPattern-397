package cn.pilot.behavior.state;

public abstract class PlayState implements Playable{
    protected Player player;

    public PlayState(Player player) {
        this.player = player;
    }
}