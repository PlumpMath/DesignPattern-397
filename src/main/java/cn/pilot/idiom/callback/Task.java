package cn.pilot.idiom.callback;

public class Task {
    public void execute(Callback callback) {
        System.out.println("[Task] I am doing some stuffs");

        if (callback != null) {
            callback.call("CALLBACK");
        }
    }
}