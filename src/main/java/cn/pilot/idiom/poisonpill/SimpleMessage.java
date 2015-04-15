package cn.pilot.idiom.poisonpill;

import java.util.HashMap;
import java.util.Map;

public class SimpleMessage implements Message {
    private Map<Header, String> headers = new HashMap<>();
    private String body;

    @Override
    public void addHeader(Header header, String value) {
        headers.put(header, value);
    }

    @Override
    public String getHeader(Header header) {
        return headers.get(header);
    }

    @Override
    public Map<Header, String> getHeaders() {
        return this.headers;
    }

    @Override
    public String getBody() {
        return this.body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }
}