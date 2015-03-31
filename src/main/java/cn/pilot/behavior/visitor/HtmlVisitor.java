package cn.pilot.behavior.visitor;

public class HtmlVisitor implements Visitor {
    private StringBuilder sb = new StringBuilder();

    public String getAllHtmlText() {
        return sb.toString();
    }

    @Override
    public void visit(PlainText plainText) {
        sb.append(String.format("%s\n", plainText.getText()));
    }

    @Override
    public void visit(BoldText boldText) {
        sb.append(String.format("<b>%s</b>\n", boldText.getText()));
    }

    @Override
    public void visit(HyperlinkText hyperlinkText) {
        sb.append(String.format("<a>%s</a>\n", hyperlinkText.getText()));
    }
}