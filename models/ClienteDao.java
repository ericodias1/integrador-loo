package models;

import java.util.ArrayList;

/**
 * Created by erico on 11/11/14.
 */
public interface ClienteDao {
    public void insert(Cliente c);
    public ArrayList<Cliente> find(Cliente c);
    public void delete(Cliente c);
    public void update(Cliente c);
    public Cliente findByCpf(String cpf);
    public ArrayList<Cliente> all();
}
