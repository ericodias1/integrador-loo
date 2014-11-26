package models;

import java.util.ArrayList;

/**
 * Created by andre on 25/11/14.
 */
public interface LocacaoDao {
    public void insert(Locacao loc);
    public ArrayList<Locacao> find(Locacao loc);
    public void delete(Locacao loc);
    public void update(Locacao loc);
    public Locacao findByKey(String key);
    public ArrayList<Locacao> all();
}
