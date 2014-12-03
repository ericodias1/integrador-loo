package models;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseClosedException;

/**
 * Created by erico on 02/12/14.
 */
public class BaseDatabaseDB40 {
    private ObjectContainer database = null;
    private static BaseDatabaseDB40 instance = null;


    private BaseDatabaseDB40(){
        database = Db4oEmbedded.openFile("database.db");
    }

    public static ObjectContainer getInstance(){

        if(instance == null)
            instance = new BaseDatabaseDB40();

        return instance.database;
    }

    public static void closeDatabase(){
//        if(database != null){
//            database.close();
//            database = null;
//        }
    }
}
