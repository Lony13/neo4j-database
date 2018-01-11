package neo4j.to2.testdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection{
    public static void main(String[] args){
        String user = "neo4j";
        String pass = "neo4j";

        String jdbcUrl = "jdbc:neo4j:bolt://localhost:7687/";

        try{
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("SUCCESS !");

            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}