package db;
import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danys on 03-Dec-17.
 */
public class DB {
    private static Connection conn = null;
    public DB(){
        System.out.println("DB Connection Status: " + (connectDB() ? "CONNECTED" : "ERROR"));
    }
    private boolean connectDB(){

        try {
            System.out.println("Connecting to DB");
            System.out.println("HOST: " + OracleProperties.getDefaultDatabaseAddress());
            System.out.println("PORT: " + OracleProperties.getDefaultDatabasePort());
            System.out.println("SID: " + OracleProperties.getDefaultSid());
            System.out.println("USER: " + OracleProperties.getDefaultUser());
            conn = OracleProperties.createConnection();
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't connect. Check your credentials");
        } catch (SQLException e) {
            System.out.println("Couldn't connect. Check your credentials");
        }
        return false;
    }
    public String getTablespaces(){return getFromDB(" * ", "TABLESPACES_VIEW");}
    public String getDatafiles(){return getFromDB(" * ", "DATAFILES_VIEW");}
    public String getStats(){return getFromDB(" * ", "STATS_VIEW");}
    public String getSessions(){return getFromDB(" * ", "SESSIONS_VIEW");}
    public String getUsers(){return getFromDB(" * ", "USERS_VIEW");}
    public String getMemory(){return getFromDB(" * ", "MEMORY_VIEW");}
    private String resultSetToJson(ResultSet rs){
        List<String> list = new ArrayList<String>();
        try {
            HashMap<String,String> map = new HashMap<String, String>();
            int cc = rs.getMetaData().getColumnCount();
            String[] headers = new String[cc];
            for (int i = 1; i <= cc; ++i) {
                headers[i - 1] = rs.getMetaData().getColumnName(i);
            }
            int z =0;
            while (rs.next()) {
                for (int i = 1; i <= cc; ++i) {
                    map.put(headers[i-1],rs.getString(i));
                }
                list.add(new Gson().toJson(map));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toString();
    }
    private String getFromDB(String properties, String tableName){
        String query = "SELECT " + properties + " FROM " + tableName;
        Statement stmt;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String json = resultSetToJson(rs);
            stmt.close();
            return json;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean disconnectDB(){
        try{
            conn.close();
            return true;
        }catch (Exception e){
            System.out.println("Error disconnecting");
            return false;
        }
    }
}
