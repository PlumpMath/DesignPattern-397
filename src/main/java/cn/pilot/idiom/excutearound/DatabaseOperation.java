package cn.pilot.idiom.excutearound;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DatabaseOperation {
    private String dbUrl;

    public void doInOneTransaction(SessionAction sessionAction) {
        open();
        Session session = new Session();
        sessionAction.execute(session);
        close();
    }

    private void close() {
        System.out.printf("Commit transaction\n");
        System.out.printf("Close session for %s\n", dbUrl);
    }

    private void open() {
        System.out.printf("Open session for %s\n", dbUrl);
        System.out.printf("Open Transaction\n");
    }

    public class Session {
    }
}