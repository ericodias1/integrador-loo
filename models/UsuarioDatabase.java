package models;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 16/11/14.
 */
public class UsuarioDatabase implements UsuarioDao{

    private static ObjectContainer db;

    public UsuarioDatabase(){

    }

    private void openDB(){
        db = Db4oEmbedded.openFile("database.db");
    }

    private void closeDB(){
        db.close();
    }

    @Override
    public void insert(Usuario u) {
        try{
            if(findByCpf(u.getCpf()) != null) throw new RuntimeException("Usuário já Cadastrado!");
            openDB();
            db.store(u);
            db.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    @Override
    public ArrayList<Usuario> find(Usuario u) {
        try{
            ArrayList<Usuario> result = new ArrayList<Usuario>();
            openDB();
            ObjectSet<Object> query = db.queryByExample(u);
            while(query.hasNext()) result.add((Usuario)query.next());
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
        return null;
    }

    @Override
    public void delete(Usuario u) {
        try{
            openDB();
            ObjectSet<Object> query = db.queryByExample(u);
            while(query.hasNext()) db.delete(query.next());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    @Override
    public void update(Usuario u) {
        try{
            openDB();
            ObjectSet<Usuario> query = db.queryByExample(new Usuario(null, u.getCpf(), null, null, null, null, null, null, null, null));
            while(query.hasNext()) db.store(query.next().copy(u));
            db.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeDB();
        }
    }

    @Override
    public ArrayList<Usuario> all() {
        return find(new Usuario(null, null, null, null, null, null, null, null, null, null));
    }

    @Override
    public boolean login(String login, String pass) {
       List<Usuario> result = null;
        result = find(new Usuario(null, null, null, null, null, null, null, null, login, pass));
        if(result.size() > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Usuario findByCpf(String cpf) {
        List<Usuario> result = null;
        result = find(new Usuario(null, cpf, null, null, null, null, null, null, null, null));
        if(result.size() > 0) return result.get(0);
        return null;
    }
}
