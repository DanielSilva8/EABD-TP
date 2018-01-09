package database;

import utils.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Timer;

/**
 * Created by danys on 10-Nov-17.
 */
public class Oracle {
    Connection conn = null;
    public boolean connectDB(){
        String url = "jdbc:oracle:thin:@" + OracleProperties.getDefaultDatabaseAddress() + ":" + OracleProperties.getDefaultDatabasePort() + ":" + OracleProperties.getDefaultSid();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, OracleProperties.getDefaultUser(), OracleProperties.getDefaultPassword());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Connection getConn() {
        return conn;
    }
    public boolean disconnectDB(){
        try{
            conn.close();
            return true;
        }catch (Exception e){
            System.out.println("Error disconnecting");
            return false;
        }
    }

    public void createSchema(){Schema.create(conn);}
    public void dropSchema(){Schema.drop(conn);}
    public void seedSchema() { Schema.seed(conn);}
    public void updateSchema() {
        Timer t = new Timer();
        UpdateRecords task = new UpdateRecords(conn);
        t.scheduleAtFixedRate(task, 5000, 5000);
    }
}
