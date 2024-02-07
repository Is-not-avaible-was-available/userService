package Singleton.MultiThreadedEager;

public class DBConnection {

    private static final DBConnection dbConnection = new DBConnection();
    private DBConnection(){}

    public static DBConnection createInstance(){
        return dbConnection;
    }
}
