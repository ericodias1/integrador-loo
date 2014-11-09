package models;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Created by erico on 09/11/14.
 */
public class ClienteDB{
    private static ObjectContainer db;

    public ClienteDB(){}

    private void openDB(){
        db = Db4oEmbedded.openFile("database.db");
    }

    private void closeDB(){
        db.close();
    }

    private void save(){

    }
}
