package cn.pilot.behavior.servant;

public class Demo {
    public static void main(String[] args) {
        Wrangler whiteWrangler = new Wrangler(Color.WHITE);
        Renegade whiteRenegade = new Renegade(Color.WHITE);

        Servant servant = new Servant();

        servant.repaint(whiteWrangler, Color.RED);
        servant.repaint(whiteRenegade, Color.BLACK);
    }
}