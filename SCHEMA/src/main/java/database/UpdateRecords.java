package database;

import java.sql.Connection;
import java.util.TimerTask;

/**
 * Created by danys on 09-Jan-18.
 */
public class UpdateRecords extends TimerTask {
    private Connection conn;
    public UpdateRecords(Connection _conn) {
        this.conn = _conn;
    }
    @Override
    public void run() {
        if (conn != null) Schema.seed(conn);
    }
}
