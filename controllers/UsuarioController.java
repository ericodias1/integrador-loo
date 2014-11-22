package controllers;

import views.ClienteWindow;
import views.UsuarioSaveDialog;
import views.UsuarioWindow;

import javax.swing.*;

/**
 * Created by erico on 20/11/14.
 */
public class UsuarioController {
    public void ListController(){
        new UsuarioWindow(this);
    }
    public void createDialog(JFrame frame){
        new UsuarioSaveDialog(frame, this, null);
    }
}
