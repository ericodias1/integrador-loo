package models;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erico on 09/11/14.
 */
public class ClienteDatabase implements ClienteDao{
    private static ObjectContainer db;

    public ClienteDatabase(){}

    private void openDB(){
        db = Db4oEmbedded.openFile("database.db");
    }

    private void closeDB(){
        db.close();
    }

    public void insert(Cliente c){
        try{
            if(findByCpf(c.getCpf()) != null) throw new RuntimeException("Cliente já existe!");
            openDB();
            db.store(c);
            db.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    public ArrayList<Cliente> find(Cliente c){
        try{
            ArrayList<Cliente> result = new ArrayList<Cliente>();
            openDB();
            ObjectSet<Object> query = db.queryByExample(c);
            while(query.hasNext()) result.add((Cliente)query.next());
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
        return null;
    }

    public void delete(Cliente c){
        try{
            openDB();
            ObjectSet<Object> query = db.queryByExample(c);
            while(query.hasNext()) db.delete(query.next());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    public void update(Cliente c){
        try{
            openDB();
            ObjectSet<Cliente> query = db.queryByExample(new Cliente(null, c.getCpf(), null, null, null, null, null, null));
            while(query.hasNext()) db.store(query.next().copy(c));
            db.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
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
