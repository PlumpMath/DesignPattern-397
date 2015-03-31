package cn.pilot.behavior.visitor;

public class MarkdownVisitor implements Visitor {
    private StringBuilder sb = new StringBuilder();

    public String getAllMarkdownText() {
        return sb.toString();
    }

    @Override
    public void visit(PlainText plainText) {
        sb.append(String.format("%s\n", plainText.getText()));
    }

    @Override
    public void visit(BoldText boldText) {
        sb.append(String.format("**%s**\n", boldText.getText()));
    }

    @Override
    public void visit(HyperlinkText hyperlinkText) {
        sb.append(String.format("[this is a link](%s)\n", hyperlinkText.getText()));
    }
}