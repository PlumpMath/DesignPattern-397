package cn.pilot.behavior.visitor;

public class HyperlinkText extends DocumentText {
    public HyperlinkText(String text) {
        super(text);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}