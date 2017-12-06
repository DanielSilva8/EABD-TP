import database.Oracle;

/**
 * Created by danys on 10-Nov-17.
 */
public class Main {

    public static void main(String[] args){

        Oracle db = new Oracle();

        if(db.connectDB()){

            System.out.println("Connected");
            db.dropSchema();
            db.createSchema();
            db.disconnectDB();

            System.out.println("Disconnected");
        }else{
            System.out.println("Couldn't connect");
        }
    }

}
