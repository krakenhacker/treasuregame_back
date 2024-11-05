package com.example.treasuregame_back.View;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends Composite<LoginOverlay> {

    public LoginView(){
        getContent().setOpened(true);
        getContent().setTitle("Treasure Game Back Office");
        getContent().setDescription("Admin Platform Treasure Game");
        getContent().setAction("login");
    }
}
