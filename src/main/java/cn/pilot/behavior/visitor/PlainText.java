package cn.pilot.behavior.visitor;

public class PlainText extends DocumentText {
    public PlainText(String text) {
        super(text);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}