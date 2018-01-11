package neo4j.to2.testdb;

import neo4j.to2.engine.PersistenceContext;
import neo4j.to2.DBTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceContext.class);
        DBTest dbTest = ctx.getBean(DBTest.class);
        dbTest.generateDataAndTest();
    }
}
