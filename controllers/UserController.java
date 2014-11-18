package controllers;

import views.LoginWindow;

/**
 * Created by andre on 16/11/14.
 */
public class UserController {
    public UserController(){
        new LoginWindow(this);
    }
}
