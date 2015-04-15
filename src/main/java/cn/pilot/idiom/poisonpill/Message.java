package cn.pilot.idiom.poisonpill;

import java.util.Map;

public interface Message {
    public static final Message POISON_PILL = new Message() {
        @Override
        public void addHeader(Header header, String value) {
            throw new RuntimeException("not supposed to be called");
        }

        @Override
        public String getHeader(Header header) {
            throw new RuntimeException("not supposed to be called");
        }

        @Override
        public Map<Header, String> getHeaders() {
            throw new RuntimeException("not supposed to be called");
        }

        @Override
        public String getBody() {
            throw new RuntimeException("not supposed to be called");
        }

        @Override
        public void setBody(String body) {
            throw new RuntimeException("not supposed to be called");
        }
    };

    void addHeader(Header header, String value);

    String getHeader(Header header);

    Map<Header, String> getHeaders();

    String getBody();

    void setBody(String body);

    public enum Header {
        DATE
    }
}