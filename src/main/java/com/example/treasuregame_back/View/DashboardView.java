package com.example.treasuregame_back.View;
import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.security.RolesAllowed;
import java.util.Collection;


@Route("")
@RolesAllowed("ADMIN")
public class DashboardView extends Div {
    @Autowired
    private GameService gameService;

    public DashboardView(GameService service) {
        Button NewGameButton = new Button("New Game");
        NewGameButton.addClickListener( e -> UI.getCurrent().navigate(NewGameView.class));

        add(new H1("Dashboard"),NewGameButton);

        Grid<Game> grid = new Grid<>(Game.class, false);
        setupGrid(grid,service);
    }

    public void setupGrid(Grid<Game> grid,GameService service){
        grid.addColumn(Game::getName).setHeader("Name");
        grid.addColumn(new LocalDateTimeRenderer<>(Game::getStart,"dd-MM-yyyy HH:mm")).setHeader("Start").setSortable(true);
        grid.addColumn(Game::getDuration).setHeader("Duration").setSortable(true);
        grid.addColumn(Game::getX).setHeader("X");
        grid.addColumn(Game::getY).setHeader("Y");
        grid.addColumn(Game::getW).setHeader("W");
        grid.addColumn(Game::getZ).setHeader("Z");
        grid.addColumn(new NativeButtonRenderer<Game>(item -> "Duplicate",
                clickedItem -> {
                    clickedItem.setName(clickedItem.getName()+" Duplicate");
                    Long newgameid = clickedItem.getId()+service.getNextVal();
                    clickedItem.setId(newgameid);
                    service.add(clickedItem);
                })
        );
        Collection<Game> games = service.findAll();
        grid.setItems(games);
        add(grid);
    }

}
