package cn.pilot.behavior.state;

public class BufferingState extends PlayState {

    public BufferingState(final Player player) {
        super(player);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Start to buffer the video");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                player.setCurrentState(PlayingState.class.getName());
            }
        }).start();
    }

    @Override
    public void play() {
        System.out.println("Video is buffering now. Please wait");
    }

    @Override
    public void pause() {
        System.out.println("Video is buffering now. Please wait");
    }
}