package models;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erico on 09/11/14.
 */
public class ClienteDatabase implements ClienteDao{
//    private static ObjectContainer db;

    public ClienteDatabase(){}

//    private void openDB(){
//        try {
//            db = BaseDatabaseDB40.getInstance();
//        }catch (DatabaseFileLockedException e){
//            System.out.println("Database already opened.. skipping..");
//        }
//    }

//    private void closeDB(){
//        BaseDatabaseDB40.closeDatabase();
//    }

    public void insert(Cliente c){
        try{
            if(findByCpf(c.getCpf()) != null) throw new RuntimeException("Cliente já existe!");
//            openDB();
            BaseDatabaseDB40.getInstance().store(c);
            BaseDatabaseDB40.getInstance().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
    }

    public ArrayList<Cliente> find(Cliente c){
        try{
            ArrayList<Cliente> result = new ArrayList<Cliente>();
//            openDB();
            ObjectSet<Object> query = BaseDatabaseDB40.getInstance().queryByExample(c);
            while(query.hasNext()) result.add((Cliente)query.next());
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
        return null;
    }

    public void delete(Cliente c){
        try{
//            openDB();
            ObjectSet<Object> query = BaseDatabaseDB40.getInstance().queryByExample(c);
            while(query.hasNext()) BaseDatabaseDB40.getInstance().delete(query.next());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
    }

    public void update(Cliente c){
        try{
//            openDB();
            ObjectSet<Cliente> query = BaseDatabaseDB40.getInstance().queryByExample(new Cliente(null, c.getCpf(), null, null, null, null, null, null));
            while(query.hasNext()) BaseDatabaseDB40.getInstance().store(query.next().copy(c));
            BaseDatabaseDB40.getInstance().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
    }

    public Cliente findByCpf(String cpf){
        List<Cliente> result = null;
        result = find(new Cliente(null, cpf, null, null, null, null, null, null));
        if(result.size() > 0) return result.get(0);
        return null;
    }

    public ArrayList<Cliente> all(){
        return find(new Cliente(null, null, null, null, null, null, null, null));
    }
}
