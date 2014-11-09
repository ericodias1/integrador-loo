import models.Cliente;
import models.Media;
import models.Usuario;

import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Cliente c = new Cliente("Érico Dias", "029.562.450-11", "(42) 99862680", "4103261113", new Date(1993, 1, 3), 'M', "ericodias1@gmail.com", "Web developer");
        c.save()
        ArrayList<Cliente> clientes = Cliente.all();
        for(Cliente cliente : clientes){
            System.out.println(cliente.getCpf()+" - "+cliente.getNome()+" - "+cliente.getEmail());
        }
    }
}