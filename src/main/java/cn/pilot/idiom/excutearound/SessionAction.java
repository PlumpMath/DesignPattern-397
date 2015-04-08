package cn.pilot.idiom.excutearound;

public interface SessionAction {
    void execute(DatabaseOperation.Session session);
}