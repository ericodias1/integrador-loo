package controllers;

import views.ClienteSaveDialog;
import views.ClienteWindow;
import views.HomeWindow;
import views.LoginWindow;

import javax.swing.*;

/**
 * Created by erico on 11/11/14.
 */
public class ClienteController {

    public void listCliente(){
        new ClienteWindow(this);
    }

    public void createDialog(JFrame frame){
        new ClienteSaveDialog(frame,this, null);
    }
}
