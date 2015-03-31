package cn.pilot.behavior.state;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Player player = new Player();

        // buffering state
        player.play();
        player.pause();

        // play state
        Thread.sleep(1500);
        player.play();
        player.pause();

        // pause state
        player.pause();
        player.play();
    }
}