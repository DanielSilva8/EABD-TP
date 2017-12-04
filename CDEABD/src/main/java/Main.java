import db.DB;

/**
 * Created by danys on 03-Dec-17.
 */
public class Main {

    public static void main(String [] args) {
        DB db = new DB();

        System.out.println(db.getUsers());
        System.out.println(db.getDatafiles());
        System.out.println(db.getTablespaces());
        System.out.println(db.getStats());
        System.out.println(db.getSessions());
        db.disconnectDB();
    }

}
