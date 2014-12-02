package models;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 25/11/14.
 */
public class LocacaoDatabase implements LocacaoDao{
    private static ObjectContainer db;

    public LocacaoDatabase(){}

    private void openDB(){
        db = Db4oEmbedded.openFile("database.db");
    }

    private void closeDB(){
        db.close();
    }

    @Override
    public void insert(Locacao loc) {
        try{
            if(findByKey(loc.getKey()) != null) throw new RuntimeException("Locação já registrada!");
            openDB();
            db.store(loc);
            db.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    @Override
    public ArrayList<Locacao> find(Locacao loc) {
        try{
            ArrayList<Locacao> result = new ArrayList<Locacao>();
            openDB();
            ObjectSet<Object> query = db.queryByExample(loc);
            while(query.hasNext()) result.add((Locacao)query.next());
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
        return null;
    }

    @Override
    public void delete(Locacao loc) {
        try{
            openDB();
            ObjectSet<Object> query = db.queryByExample(loc);
            while(query.hasNext()) db.delete(query.next());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    @Override
    public void update(Locacao loc) {
        try{
            openDB();
            Locacao loc_int = new Locacao(null, null, null, null, null, loc.getKey());
            ObjectSet<Locacao> query = db.queryByExample(loc_int);
            if(query.hasNext()) db.store(query.next().copy(loc));
            db.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    @Override
    public Locacao findByKey(String key) {
        List<Locacao> result = null;
        result = find(new Locacao(null, null, null, null, null, key));
        if(result.size() > 0) return result.get(0);
        return null;
    }

    @Override
    public ArrayList<Locacao> all() {
        return find(new Locacao(null, null, null, null, null, null));
    }
}
