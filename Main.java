import controllers.LoginController;
import models.Media;
import models.Usuario;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Media m1 = new Media("A volta dos que não foram", new Date(2000, 1, 13), "Comedia", 18, 3.00, true);
        Media m2 = new Media("O pente e o careca", new Date(2000, 1, 13), "Terror", 14, 2.00, false);
        Media m3 = new Media("A bala que dobrou a esquina", new Date(2000, 1, 13), "Ação", 12, 5.00, true);
        m1.delete();
        m2.delete();
        m3.delete();

        for(Media m : Media.all()){
            System.out.println(m.toString());
        }

//        new LoginController();
    }
}
