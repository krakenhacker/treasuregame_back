package com.example.treasuregame_back.View;


import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.crudui.crud.impl.GridCrud;
import javax.annotation.security.RolesAllowed;

@Route("")
@RolesAllowed("ADMIN")
public class MainView extends VerticalLayout {

    public MainView(GameService service){
        var crud = new GridCrud<>(Game.class, service);
        crud.getGrid().setColumns("name", "startdate","starttime","duration","x","y","w","z");
        crud.getCrudFormFactory().setVisibleProperties("name", "startdate","starttime","duration","x","y","w","z");
        crud.setAddOperationVisible(false);
        crud.getCrudLayout().addToolbarComponent(new Button(new RouterLink("New Game", NewGameView.class)));
        crud.setFindAllOperation(() -> service.findAll());
        add(
                 new H1("Admin Dashboard"),
                 crud
        );
    }
}
