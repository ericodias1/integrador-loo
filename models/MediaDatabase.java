package models;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erico on 18/11/14.
 */
public class MediaDatabase implements MediaDao{

//    private ObjectContainer db;

    public MediaDatabase(){}

//    private void openDB(){
//        db = BaseDatabaseDB40.getInstance();
//    }

//    private void closeDB(){
//        BaseDatabaseDB40.closeDatabase();
//    }

    @Override
    public void insert(Media m) {
        try{
//            openDB();
            BaseDatabaseDB40.getInstance().store(m);
            BaseDatabaseDB40.getInstance().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
    }

    @Override
    public ArrayList<Media> find(Media m) {
        try{
            ArrayList<Media> result = new ArrayList<Media>();
//            openDB();
            ObjectSet<Object> query = BaseDatabaseDB40.getInstance().queryByExample(m);
            while(query.hasNext()) result.add((Media)query.next());
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
        return null;
    }

    @Override
    public void delete(Media m) {
        try{
//            openDB();
            ObjectSet<Object> query = BaseDatabaseDB40.getInstance().queryByExample(m);
            while(query.hasNext()) BaseDatabaseDB40.getInstance().delete(query.next());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
    }

    @Override
    public void update(Media m) {
        try{
//            openDB();
            Media media = new Media(null, null, null, null, null, null, m.getId());
            ObjectSet<Media> query = BaseDatabaseDB40.getInstance().queryByExample(media);
            while(query.hasNext()) BaseDatabaseDB40.getInstance().store(query.next().copy(m));
            BaseDatabaseDB40.getInstance().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDatabaseDB40.closeDatabase();
        }
    }

    @Override
    public Media findById(int id) {
        List<Media> result = null;
        Media m = new Media(null, null, null, null, null, null, id);
        result = find(m);
        if(result.size() > 0) return result.get(0);
        return null;
    }

    @Override
    public ArrayList<Media> all() {
        return find(new Media(null, null, null, null, null, null, null));
    }
}
