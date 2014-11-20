package controllers;

import models.Media;
import views.LocarWindow;

import java.util.ArrayList;

/**
 * Created by andre on 18/11/14.
 */
public class LocarController {
    public LocarController(){
        new LocarWindow(this);
    }

    public ArrayList<Media> getMedias() {
        ArrayList<Media> medias = Media.all();
        if(medias.size() > 0) return medias;
        return new ArrayList<Media>();
    }
}
