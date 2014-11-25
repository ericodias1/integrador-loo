package controllers;

import models.Cliente;
import models.Media;
import views.HomeWindow;
import views.LocarSaveDialog;
import views.LocarWindow;

import javax.swing.*;
import java.util.ArrayList;

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
        ArrayList<Media> m = new ArrayList<Media>();
        for(Integer code : ids){
            m.add(Media.findById(code));
        }
        this.saveDialog(null, m,c);
    }
}
