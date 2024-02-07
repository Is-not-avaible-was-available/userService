package Singleton.SingleThreaded;

public class DBConnection {

    public static DBConnection dbConnection;

    private DBConnection(){}

    public static DBConnection createInstance(){
        if(dbConnection==null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
}
