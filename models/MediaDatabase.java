package models;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erico on 18/11/14.
 */
public class MediaDatabase implements MediaDao{

    private ObjectContainer db;

    public MediaDatabase(){}

    private void openDB(){
        db = Db4oEmbedded.openFile("database.db");
    }

    private void closeDB(){
        db.close();
    }

    @Override
    public void insert(Media m) {
        try{
            m.setId(m.hashCode());
            openDB();
            db.store(m);
            db.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    @Override
    public ArrayList<Media> find(Media m) {
        try{
            ArrayList<Media> result = new ArrayList<Media>();
            openDB();
            ObjectSet<Object> query = db.queryByExample(m);
            while(query.hasNext()) result.add((Media)query.next());
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
        return null;
    }

    @Override
    public void delete(Media m) {
        try{
            openDB();
            ObjectSet<Object> query = db.queryByExample(m);
            while(query.hasNext()) db.delete(query.next());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    @Override
    public void update(Media m) {
        try{
            openDB();
            Media media = new Media(null, null, null, null, null, null, m.getId());
            ObjectSet<Media> query = db.queryByExample(media);
            while(query.hasNext()) db.store(query.next().copy(m));
            db.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
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
