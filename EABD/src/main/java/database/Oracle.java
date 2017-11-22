package database;

import utils.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Created by danys on 10-Nov-17.
 */
public class Oracle {

    Connection conn = null;
    Statement stmt = null;
    OracleProperties prop = new OracleProperties();

    public boolean connectDB(){

        String url = "jdbc:oracle:thin:@" + prop.getDefaultDatabaseAddress() + ":" + prop.getDefaultDatabasePort() + ":" + prop.getDefaultSid();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, prop.getDefaultUser(), prop.getDefaultPassword());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void getUsers(){

    }

    public Connection getConn() {
        return conn;
    }

    private ResultSet getFromDB(String properties, String tableName){

        String query = "SELECT " + properties + " FROM " + tableName;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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

    public void createSchema(){
        Schema.create(conn);
    }

    public void dropSchema(){
        Schema.drop(conn);
    }
}
