package models;

import java.util.ArrayList;

/**
 * Created by erico on 18/11/14.
 */
public interface MediaDao {
    public void insert(Media m);
    public ArrayList<Media> find(Media m);
    public void delete(Media m);
    public void update(Media m);
    public Media findById(int id);
    public ArrayList<Media> all();
}
