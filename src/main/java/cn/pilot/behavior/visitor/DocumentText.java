package cn.pilot.behavior.visitor;

import lombok.Getter;

@Getter
public abstract class DocumentText {
    private String text;

    public DocumentText(String text) {
        this.text = text;
    }

    public abstract void accept(Visitor visitor);
}