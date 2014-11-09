import models.Cliente;
import models.Media;
import models.Usuario;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Cliente c = new Cliente("Érico", "027.562.450-11", "(42) 99862680", "4103261113", new Date(1993, 1, 3), 'M', "ericodias1@gmail.com", "Web developer");
        Usuario u = new Usuario("Érico", "027.562.450-11", "(42) 99862680", "4103261113", new Date(1993, 1, 3), 'M', "ericodias1@gmail.com", "123123123", "ericodias1", "Teste");
        Media m = new Media("A volta dos que não foram", new Date(2014, 11, 3), "Açao", 18, 3.00, true);
    }
}