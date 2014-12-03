package models;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 25/11/14.
 */
public class LocacaoDatabase implements LocacaoDao{
//    private static ObjectContainer db;

    public LocacaoDatabase(){}

//    private void openDB(){
//        db = BaseDatabaseDB40.getInstance();
//    }

//    private void closeDB(){
//        BaseDatabaseDB40.closeDatabase();
//    }

    @Override
    public void insert(Locacao loc) {
        try{
            if(findByKey(loc.getKey()) != null) throw new RuntimeException("Locação já registrada!");
//            openDB();
            BaseDatabaseDB40.getInstance().store(loc);
            BaseDatabaseDB40.getInstance().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
    }

    @Override
    public ArrayList<Locacao> find(Locacao loc) {
        try{
            ArrayList<Locacao> result = new ArrayList<Locacao>();
//            openDB();
            ObjectSet<Object> query = BaseDatabaseDB40.getInstance().queryByExample(loc);
            while(query.hasNext()) result.add((Locacao)query.next());
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
        return null;
    }

    @Override
    public void delete(Locacao loc) {
        try{
//            openDB();
            ObjectSet<Object> query = BaseDatabaseDB40.getInstance().queryByExample(loc);
            while(query.hasNext()) BaseDatabaseDB40.getInstance().delete(query.next());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
    }

    @Override
    public void update(Locacao loc) {
        try{
            Locacao loc_int = new Locacao(null, null, null, null, null, loc.getKey());
            ObjectSet<Locacao> query = BaseDatabaseDB40.getInstance().queryByExample(loc_int);
            while(query.hasNext())
                BaseDatabaseDB40.getInstance().store(query.next().copy(loc));
            BaseDatabaseDB40.getInstance().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
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
