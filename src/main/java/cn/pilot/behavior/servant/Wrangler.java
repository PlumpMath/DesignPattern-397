package cn.pilot.behavior.servant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Wrangler implements Paintable {
    private Color color;

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}