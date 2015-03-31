package cn.pilot.behavior.state;

public class BufferingState extends PlayerState {

    public BufferingState(final StateContext stateContext) {
        super(stateContext);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Start to buffer the video");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stateContext.setCurrentState(PlayingState.class.getName());
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