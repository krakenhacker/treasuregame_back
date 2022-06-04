package com.example.treasuregame_back.View;

import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.RolesAllowed;

@Route("")
@RolesAllowed("ADMIN")
public class MainView extends VerticalLayout {

    public MainView(GameService service){
        var crud = new GridCrud<>(Game.class, service);
        crud.getGrid().setColumns("name", "timestart","timeend","x","y","w","z");
        crud.getCrudFormFactory().setVisibleProperties("name", "timestart","timestart","x","y","w","z");
       add(
                new H1("Admin Dashboard"),
                crud);
    }

}
