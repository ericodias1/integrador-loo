package models;

import java.util.ArrayList;

/**
 * Created by andre on 16/11/14.
 */
public interface UsuarioDao {
    public void insert(Usuario c);
    public ArrayList<Usuario> find(Usuario c);
    public void delete(Usuario c);
    public void update(Usuario c);
    public ArrayList<Usuario> all();
    public boolean login(String login, String pass);
    public Usuario findByCpf(String cpf);
}
