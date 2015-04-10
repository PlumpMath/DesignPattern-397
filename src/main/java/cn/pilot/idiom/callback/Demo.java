package cn.pilot.idiom.callback;

public class Demo {
    public static void main(String[] args) {
        new Task().execute(new Callback() {
            @Override
            public void call(String event) {
                System.out.printf("[Callback] receives event from [Task]%s\n", event);
            }
        });
    }
}