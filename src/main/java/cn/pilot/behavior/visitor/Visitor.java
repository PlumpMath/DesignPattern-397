package cn.pilot.behavior.visitor;

public interface Visitor {
    void visit(PlainText plainText);

    void visit(BoldText boldText);

    void visit(HyperlinkText hyperlinkText);
}