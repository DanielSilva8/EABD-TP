package database;

/**
 * Created by danys on 10-Nov-17.
 */
public class OracleProperties {

    private static final String DEFAULT_DATABASE_ADDRESS = "127.0.0.1";
    private static final String DEFAULT_CONNECTION_NAME = "SYS.ORCL12";
    private static final int DEFAULT_DATABASE_PORT = 1521;
    private static final String DEFAULT_SID = "UPGR";
    private static final String DEFAULT_USER = "sys as sysdba";
    private static final String DEFAULT_PASSWORD = "oracle";
    private static final String DEFAULT_SCHEMA_PATH = System.getProperty("user.dir") + "/src/main/java/database/schema.sql";
    private static final String DEFAULT_DROP_SCHEMA_PATH = System.getProperty("user.dir") + "/src/main/java/database/drop.sql";
    private static final String DEFAULT_SEED_SCHEMA_PATH = "C:/Sites/EABD/src/main/java/database/seed.sql";

    public static String getDefaultSeedSchemaPath() {
        return DEFAULT_SEED_SCHEMA_PATH;
    }

    public static String getDefaultSchemaPath() {
        return DEFAULT_SCHEMA_PATH;
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

    public static String getDefaultDropSchemaPath() {
        return DEFAULT_DROP_SCHEMA_PATH;
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
