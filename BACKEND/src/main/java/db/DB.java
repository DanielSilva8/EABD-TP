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
            conn = OracleProperties.createConnection();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getTablespaces(){return resultSetToJson(getFromDB(" * ", "TABLESPACES"));}
    public String getDatafiles(){return resultSetToJson(getFromDB(" * ", "DATAFILES"));}
    public String getStats(){return resultSetToJson(getFromDB(" * ", "STATS"));}
    public String getSessions(){return resultSetToJson(getFromDB(" * ", "SESSIONS"));}
    public String getUsers(){return resultSetToJson(getFromDB(" * ", "USERS"));}
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
    private static ResultSet getFromDB(String properties, String tableName){
        String query = "SELECT " + properties + " FROM " + tableName;
        try {
            Statement stmt;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            stmt.close();
            return rs;
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
