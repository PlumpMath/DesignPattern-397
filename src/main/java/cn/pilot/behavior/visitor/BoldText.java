package cn.pilot.behavior.visitor;

public class BoldText extends DocumentText {
    public BoldText(String text) {
        super(text);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}