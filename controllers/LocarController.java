package controllers;

import models.Cliente;
import models.Locacao;
import models.Media;
import views.HomeWindow;
import views.LocarSaveDialog;
import views.LocarWindow;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by andre on 18/11/14.
 */
public class LocarController {
    public void ListDialog(){
        new LocarWindow(this);
    }

    public void saveDialog(JFrame parent, ArrayList<Media> medias, Cliente c){
        new LocarSaveDialog(parent,medias, c, this);
    }

    public ArrayList<Media> getMedias() {
        ArrayList<Media> medias = Media.all();
        if(medias.size() > 0) return medias;
        return new ArrayList<Media>();
    }

    public void preparSave(String user, ArrayList<Integer> ids) {
        Cliente c = Cliente.findByCpf(user);
        ArrayList<Media> medias = new ArrayList<Media>();
        for(Integer id : ids ){
            medias.add(Media.findById(id));
        }

        this.saveDialog(null, medias,c);
    }

    public void createLocation(Cliente c, ArrayList<Integer> medias, boolean pagamento) {
        boolean locado = true;
        float total = 0;

        for(Integer id : medias){
            Media m = Media.findById(id);
            total = (float) (total+m.getPreco());
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        Locacao loc = new Locacao(c,medias,(double)total,pagamento, true, timeStamp );
        loc.save();

    }
}
