package controllers;

import views.MediaSaveDialog;
import views.MediaWindow;

import javax.swing.*;

/**
 * Created by andre on 19/11/14.
 */
public class MediaController {

    public void listDialog(){
        new MediaWindow(this);
    }

    public void createDialog(JFrame frame){
        new MediaSaveDialog(frame, this, null);
    }
}
