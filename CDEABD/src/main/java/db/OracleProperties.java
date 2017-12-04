package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by danys on 10-Nov-17.
 */
public class OracleProperties {

    private static final String DEFAULT_DATABASE_ADDRESS = "127.0.0.1";
    private static final String DEFAULT_CONNECTION_NAME = "TP.ORCL12";
    private static final int DEFAULT_DATABASE_PORT = 1521;
    private static final String DEFAULT_SID = "UPGR";
    private static final String DEFAULT_USER = "tp";
    private static final String DEFAULT_PASSWORD = "oracle";


    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@" + DEFAULT_DATABASE_ADDRESS + ":" + DEFAULT_DATABASE_PORT + ":" + DEFAULT_SID, DEFAULT_USER, DEFAULT_PASSWORD);
        return conn;
    }

    public static String getDefaultDatabaseAddress() {
        return DEFAULT_DATABASE_ADDRESS;
    }

    public static String getDefaultConnectionName() {
        return DEFAULT_CONNECTION_NAME;
    }

    public static int getDefaultDatabasePort() {
        return DEFAULT_DATABASE_PORT;
    }

    public static String getDefaultSid() {
        return DEFAULT_SID;
    }

    public static String getDefaultUser() {
        return DEFAULT_USER;
    }

    public static String getDefaultPassword() {
        return DEFAULT_PASSWORD;
    }
}
