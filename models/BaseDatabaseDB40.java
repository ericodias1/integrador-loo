package models;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Created by erico on 02/12/14.
 */
public class BaseDatabaseDB40 {
    private static ObjectContainer database = null;
    private BaseDatabaseDB40(){}

    public static ObjectContainer getInstance(){
        if(database == null){
            database = Db4oEmbedded.openFile("database.db");
        }
        return database;
    }

    public static void closeDatabase(){
        if(database != null){
            database.close();
            database = null;
        }
    }
}
