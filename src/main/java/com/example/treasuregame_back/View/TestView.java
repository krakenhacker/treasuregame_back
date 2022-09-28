package com.example.treasuregame_back.View;


import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;
import javax.annotation.security.RolesAllowed;

@Route("test")
@RolesAllowed("ADMIN")
public class TestView extends VerticalLayout {

    public TestView(GameService service){
        Button NewGameButton = new Button("New Game");
        NewGameButton.addClickListener( e -> UI.getCurrent().navigate(NewGameView.class));

        var crud = new GridCrud<>(Game.class, service);
        crud.getGrid().setColumns("name","start","duration","x","y","w","z");
        crud.getCrudFormFactory().setVisibleProperties("name","start","duration","x","y","w","z");
        crud.setAddOperationVisible(false);
        crud.getCrudLayout().addToolbarComponent(NewGameButton);
        crud.setFindAllOperation(() -> service.findAll());
        add(
                 new H1("Admin Dashboard"),
                 crud
        );
    }
}
