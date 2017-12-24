package database;

import utils.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by danys on 10-Nov-17.
 */
public class Schema {

    public static void create(Connection conn){
        ScriptRunner runner = new ScriptRunner(conn, false, true);
        try {
            runner.runScript(new BufferedReader(new FileReader(OracleProperties.getDefaultSchemaPath())));
            System.out.println("Schema created");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error creating Schema");
    }

    public static void drop(Connection conn){
        ScriptRunner runner = new ScriptRunner(conn, false, true);
        try {
            runner.runScript(new BufferedReader(new FileReader(OracleProperties.getDefaultDropSchemaPath())));
            System.out.println("Schema droped");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error droping Schema");
    }

    public static void seed(Connection conn){
        ScriptRunner runner = new ScriptRunner(conn, false, true);
        try {
            runner.runScript(new BufferedReader(new FileReader(OracleProperties.getDefaultSeedSchemaPath())));
            System.out.println("Schema seeded");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Error seeding Schema");
    }
}
