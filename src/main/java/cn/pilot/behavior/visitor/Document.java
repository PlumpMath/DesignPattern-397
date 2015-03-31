package cn.pilot.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private List<DocumentText> texts = new ArrayList<>();

    {
        texts.add(new BoldText("bold text"));
        texts.add(new PlainText("plain text"));
        texts.add(new HyperlinkText("http://hyperlink.text"));
    }

    public void accept(Visitor visitor) {
        for (DocumentText text : texts) {
            text.accept(visitor);
        }
    }
}