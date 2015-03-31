package cn.pilot.behavior.visitor;

public class Demo {
    public static void main(String[] args) {
        Document document = new Document();

        Visitor htmlVisitor = new HtmlVisitor();
        document.accept(htmlVisitor);
        String allHtmlText = ((HtmlVisitor) htmlVisitor).getAllHtmlText();

        System.out.println(allHtmlText);

        // new requirement is coming; markdown text is needed;
        // simple: create a new visitor without changing any Objects
        Visitor markdownVisitor = new MarkdownVisitor();
        document.accept(markdownVisitor);
        String allMarkdownText = ((MarkdownVisitor) markdownVisitor).getAllMarkdownText();

        System.out.println(allMarkdownText);
    }
}