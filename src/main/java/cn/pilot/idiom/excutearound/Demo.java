package cn.pilot.idiom.excutearound;

public class Demo {
    public static void main(String[] args) {
        new DatabaseOperation("jdbc://mockurl").doInOneTransaction(new SessionAction() {
            @Override
            public void execute(DatabaseOperation.Session session) {
                System.out.println("Run sql statement");
            }
        });
    }
}