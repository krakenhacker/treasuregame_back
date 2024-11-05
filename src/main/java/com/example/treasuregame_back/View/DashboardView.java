package com.example.treasuregame_back.View;
import com.example.treasuregame_back.Services.GameUsersService;
import com.example.treasuregame_back.Models.Game;
import com.example.treasuregame_back.Services.GameService;
import com.example.treasuregame_back.Models.User;
import com.example.treasuregame_back.Services.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.Optional;


@Route(value = "" ,layout = MainLayoutView.class)
@RolesAllowed("ADMIN")
public class DashboardView extends Div {
    @Autowired
    private GameService gameService;
    @Autowired
    private GameUsersService gameUsersService;
    @Autowired
    private UserService userService;

    public DashboardView(GameService service) {
        Button NewGameButton = new Button("New Game");
        NewGameButton.addClickListener( e -> UI.getCurrent().navigate(NewGameView.class));
        Button CopyMembersButton = new Button("Copy Members");
        CopyMembersButton.addClickListener( e -> UI.getCurrent().navigate(CopyMembersView.class));
        add(new H1("Dashboard"),NewGameButton,CopyMembersButton);

        Grid<Game> grid = new Grid<>(Game.class, false);
        Grid<User> membergrid = new Grid<>(User.class, false);
        setupGrid(grid,service);
        setupMemberView(grid,membergrid);
    }

    private void setupMemberView(Grid<Game> grid,Grid<User> membergrid) {
        add(new H1("Invited Members"));
        membergrid.addColumn(User::getNickname).setHeader("Nickname");
        membergrid.addColumn(User::getEmail).setHeader("Email");
        membergrid.addColumn(
                new ComponentRenderer<>(Button::new, (button, user) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                        Game game = grid.getSelectedItems().stream().toList().get(0);
                        gameUsersService.delete(gameUsersService.searchGameUser(game,user));
                        membergrid.setItems(gameUsersService.findInvitedUsersFromGame(game));
                        membergrid.getDataProvider().refreshAll();
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Manage").setTextAlign(ColumnTextAlign.END).setWidth("100px");
        grid.addSelectionListener(selection -> {
            Optional<Game> game = selection.getFirstSelectedItem();
            if (game.isPresent()) {
                membergrid.setItems(gameUsersService.findInvitedUsersFromGame(game.get()));
                add(membergrid);
            }
        });
    }

    public void setupGrid(Grid<Game> grid,GameService service){

        Binder<Game> binder = new Binder<>(Game.class);
        Editor<Game> editor = grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        grid.addColumn(Game::getName).setHeader("Name").setKey("Name");
        grid.addColumn(new LocalDateTimeRenderer<>(Game::getStart,"dd-MM-yyyy HH:mm")).setHeader("Start").setSortable(true).setKey("Start");;
        grid.addColumn(Game::getDuration).setHeader("Duration(hours)").setSortable(true).setKey("Duration");
        grid.addColumn(Game::getX).setHeader("X").setKey("X").setWidth("90px").setFlexGrow(0);
        grid.addColumn(Game::getY).setHeader("Y").setKey("Y").setWidth("90px").setFlexGrow(0);
        grid.addColumn(Game::getW).setHeader("W").setKey("W").setWidth("90px").setFlexGrow(0);
        grid.addColumn(Game::getZ).setHeader("Z").setKey("Z").setWidth("90px").setFlexGrow(0);
        grid.addComponentColumn(game -> {
            Button duplicateButton = new Button("Duplicate");
            duplicateButton.addClickListener(e ->{
                game.setName(game.getName()+" Duplicate");
                Long newgameid = service.getNextVal();
                game.setId(newgameid);
                service.add(game);
                Collection<Game> games = service.findAll();
                grid.setItems(games);
            });
            return duplicateButton;
        }).setWidth("150px").setFlexGrow(0);;

        Grid.Column<Game> editColumn = grid.addComponentColumn(game -> {
            gameService.update(game);
            Button editButton = new Button("Edit");
            editButton.addClickListener(e -> {
                if (editor.isOpen()) {
                    editor.cancel();
                }
                grid.getEditor().editItem(game);
            });
            return editButton;
        }).setWidth("150px").setFlexGrow(0);



        TextField NameField = new TextField();
        NameField.setWidthFull();
        addCloseHandler(NameField, editor);
        binder.forField(NameField)
                .asRequired("First name must not be empty")
                .bind(Game::getName, Game::setName);
        grid.getColumnByKey("Name").setEditorComponent(NameField);

        DateTimePicker StartField = new DateTimePicker();
        StartField.setWidthFull();
        addCloseHandler(StartField, editor);
        binder.forField(StartField).asRequired("Date must not be empty")
                .bind(Game::getStart, Game::setStart);
        grid.getColumnByKey("Start").setEditorComponent(StartField);

        NumberField DurationField = new NumberField();
        DurationField.setWidthFull();
        addCloseHandler(DurationField, editor);
        binder.forField(DurationField).asRequired("Duration must not be empty")
                .bind(Game::getDuration, Game::setDuration);
        grid.getColumnByKey("Duration").setEditorComponent(DurationField);

        NumberField XField = new NumberField();
        DurationField.setWidthFull();
        addCloseHandler(XField, editor);
        binder.forField(XField).asRequired("X must not be empty")
                .bind(Game::getX, Game::setX);
        grid.getColumnByKey("X").setEditorComponent(XField);

        NumberField YField = new NumberField();
        DurationField.setWidthFull();
        addCloseHandler(YField, editor);
        binder.forField(YField).asRequired("Y must not be empty")
                .bind(Game::getY, Game::setY);
        grid.getColumnByKey("Y").setEditorComponent(YField);

        NumberField WField = new NumberField();
        DurationField.setWidthFull();
        addCloseHandler(WField, editor);
        binder.forField(WField).asRequired("W must not be empty")
                .bind(Game::getW, Game::setW);
        grid.getColumnByKey("W").setEditorComponent(WField);

        NumberField ZField = new NumberField();
        DurationField.setWidthFull();
        addCloseHandler(ZField, editor);
        binder.forField(ZField).asRequired("Z must not be empty")
                .bind(Game::getZ, Game::setZ);
        grid.getColumnByKey("Z").setEditorComponent(ZField);

        Button saveButton = new Button("Save", e ->{
            editor.save();
        });
        Button cancelButton = new Button(VaadinIcon.CLOSE.create(),
                e -> editor.cancel());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ICON,
                ButtonVariant.LUMO_ERROR);
        HorizontalLayout actions = new HorizontalLayout(saveButton,
                cancelButton);
        actions.setPadding(false);
        editColumn.setEditorComponent(actions);
        Collection<Game> games = service.findAll();
        grid.setItems(games);
        add(grid);
    }

    private static void addCloseHandler(Component textField,
                                        Editor<Game> editor) {
        textField.getElement().addEventListener("keydown", e -> editor.cancel())
                .setFilter("event.code === 'Escape'");
    }

}
