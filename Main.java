import controllers.HomeController;
import controllers.LocarController;
import controllers.LoginController;
import controllers.MediaController;

import controllers.*;
import models.Locacao;
import models.Media;
import models.Usuario;
import views.DevolverSaveDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {



//        Locacao l =new Locacao("a",new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3})),12.2d, false, false, "Loc1");
//
//        l.save();
//
//        for(Locacao loc : Locacao.all()) {
//            System.out.println(loc);
//        }
//
//        System.out.println("==================");
//
//        Locacao.findByKey("Loc1").delete();
//
//        for(Locacao loc : Locacao.all())
//            System.out.println(loc);

//        Usuario u = new Usuario(null, "027562450-11", null, null, null, null, null, null, "erico", "123");
//        u.save();
        new LoginController();
    }
}
