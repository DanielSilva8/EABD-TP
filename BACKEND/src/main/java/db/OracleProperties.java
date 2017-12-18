package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by danys on 10-Nov-17.
 */
public class OracleProperties {

    private static final String DEFAULT_DATABASE_ADDRESS =  System.getProperty("HOST") == null ? "127.0.0.1" : System.getProperty("HOST");

    private static final int DEFAULT_DATABASE_PORT = System.getProperty("PORT") == null ? 1521 : Integer.parseInt(System.getProperty("PORT")) ;
    private static final String DEFAULT_SID = System.getProperty("SID") == null ? "UPGR" : System.getProperty("SID") ;
    private static final String DEFAULT_USER = System.getProperty("USER") == null ? "tp" : System.getProperty("USER");
    private static final String DEFAULT_PASSWORD = System.getProperty("PASS") == null ? "oracle" : System.getProperty("PASS") ;

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@" + DEFAULT_DATABASE_ADDRESS + ":" + DEFAULT_DATABASE_PORT + ":" + DEFAULT_SID, DEFAULT_USER, DEFAULT_PASSWORD);
        return conn;
    }

    public static String getDefaultDatabaseAddress() {
        return DEFAULT_DATABASE_ADDRESS;
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
}
