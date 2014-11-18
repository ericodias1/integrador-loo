package models;

import java.util.ArrayList;

/**
 * Created by erico on 18/11/14.
 */
public interface MediaDao {
    public void insert(Media c);
    public ArrayList<Media> find(Media c);
    public void delete(Media c);
    public void update(Media c);
    public Media findById(String id);
    public ArrayList<Media> all();
}
