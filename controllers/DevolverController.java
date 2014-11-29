package controllers;

import models.Locacao;
import views.DevolverWindow;

import java.util.ArrayList;

/**
 * Created by andre on 27/11/14.
 */
public class DevolverController {

    public ArrayList<Locacao> getActiveLocation() {
        ArrayList<Locacao> locs = new ArrayList<Locacao>();
        for(Locacao loc : Locacao.all()){
            locs.add(loc);
        }
        return locs;
    }

    public void ListDialog() {
        new DevolverWindow(this);
    }

    public void preparToDevolution(String id) {
        System.out.println(id);
    }
}
