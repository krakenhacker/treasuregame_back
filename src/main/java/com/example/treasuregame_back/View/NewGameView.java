package com.example.treasuregame_back.View;

import com.example.treasuregame_back.Email.EmailServiceImpl;
import com.example.treasuregame_back.gamePuzzles.gamePuzzles;
import com.example.treasuregame_back.gamePuzzles.gamePuzzlesService;
import com.example.treasuregame_back.GameUsers.GameUsers;
import com.example.treasuregame_back.GameUsers.GameUsersService;
import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameService;
import com.example.treasuregame_back.user.User;
import com.example.treasuregame_back.user.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.View;
import com.vaadin.flow.component.map.configuration.layer.TileLayer;
import com.vaadin.flow.component.map.configuration.source.OSMSource;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.map.Map;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@Route(value = "NewGame", layout = MainLayoutView.class)
@RolesAllowed("ADMIN")
public class NewGameView extends Div {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private GameUsersService gameUsersService;
    @Autowired
    private  gamePuzzlesService gamePuzzlesService;
    private TextField name = new TextField("Name");
    private DateTimePicker start = new DateTimePicker("Starts");
    private NumberField duration = new NumberField("Duration (hours)");

    private NumberField x = new NumberField("X");
    private NumberField y = new NumberField("Y");
    private NumberField w = new NumberField("W");
    private NumberField z = new NumberField("Z");


    private EmailField validEmailField = new EmailField("Email Adress:");
    private List<User> invitedusers = new ArrayList<User>();

    private TextField puzzle = new TextField("Puzzle");
    private TextField puzzleAnswer = new TextField("Puzzle Answer");

    private NumberField puzzleX = new NumberField("Puzzle X");
    private NumberField puzzleY = new NumberField("Puzzle Y");

    private TextField puzzleHint = new TextField("Puzzle Hint");
    int count = 0;
    public NewGameView(GameService service){
//        x.setValue(null);
//        y.setValue(null);
//        w.setValue(null);
//        z.setValue(null);
        Map map = new Map();
        map.setCenter(new Coordinate(2615595.473095732,5027261.561026261));
//        map.setCenter(Coordinate.fromLonLat(23.496590, 41.101525));
        map.setZoom(15);
        map.addClickEventListener(e -> {
            Coordinate coordinates = e.getCoordinate();
            if(count%2==0 && count<=1) {
                x.setValue(coordinates.getX());
                y.setValue(coordinates.getY());
                w.setValue(null);
                z.setValue(null);
            } else if (count<=1) {
                w.setValue(coordinates.getX());
                z.setValue(coordinates.getY());
            }else
            {
//                puzzle = new TextField("Puzzle " + (count-2));
//                puzzleAnswer = new TextField("Puzzle " + (count-2) +" Answer");
//                puzzleX = new NumberField("Puzzle " + (count-2) +" Y");
//                puzzleY = new NumberField("Puzzle " + (count-2) +" Y");
                puzzleX.setValue(coordinates.getX());
                puzzleY.setValue(coordinates.getY());
            }
            count++;
        });

        var binder = new Binder<>(Game.class);
        binder.bindInstanceFields(this);
        Grid<User> grid = new Grid<>(User.class, false);
        validEmailField.setPattern("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        validEmailField.setClearButtonVisible(true);
        Button addusertolistbutton = new Button("Add to list");
        addusertolistbutton.addClickListener(e ->{
           invitedusers.add(new User(validEmailField.getValue()));
           grid.getDataProvider().refreshAll();
           validEmailField.clear();
           Notification.show("User added");
        });
        FormLayout formLayout  = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2),
                new FormLayout.ResponsiveStep("1000px", 3),
                new FormLayout.ResponsiveStep("1200px", 4)
        );
        formLayout.setColspan(map, 4);
        formLayout.setColspan(start, 2);
        formLayout.setColspan(x, 2);
        formLayout.setColspan(y, 2);
        formLayout.setColspan(w, 2);
        formLayout.setColspan(z, 2);
        formLayout.add(name,start,NumberFieldStep(duration),x,y,w,z,map,puzzle,puzzleAnswer,puzzleX,puzzleY,puzzleHint);
        add(
                new H1("New Game"),

                formLayout,
                validEmailField,addusertolistbutton,
                new Button("Save", event ->{
                    var game = new Game();
                    Long savedgameid = service.getNextVal();
                    binder.writeBeanIfValid(game);
                    service.add(game);
                    Notification.show("Game Saved");
                    binder.readBean(new Game());
                    game.setId(savedgameid);
                    for(int i=0;i<invitedusers.size();i++) {
                        GameUsers gameUsers = new GameUsers();
                        gameUsers.setGame(game);
                        User user = userService.saveUserIfnotExist(invitedusers.get(i));
                        gameUsers.setUser(user);
                        gameUsers.setCode(service.getRandomNumber());
                        gameUsersService.add(gameUsers);
//                        sendmail(game,user);
                        Notification.show("invited member: "+invitedusers.get(i).getEmail()+"\nto game: "+game.getName());
                    }
                    gamePuzzles gamePuzzles = new gamePuzzles(game,puzzle.getValue(),puzzleAnswer.getValue(),puzzleX.getValue(),puzzleY.getValue(),puzzleHint.getValue());
                    gamePuzzlesService.add(new gamePuzzles(game,"10-3","7",0,0,"no hint"));
                    gamePuzzlesService.add(gamePuzzles);
                    invitedusers.clear();
                    grid.getDataProvider().refreshAll();

                })

        );
        VirtualListSetup(grid);
    }
    public void sendmail(Game game,User user){
        if(user!=(null)){
            GameUsers gameUsers = gameUsersService.searchGameUser(game, user);
            String msgtext = String.format("You have been invited to game: " + game.getName() + "  starting at  " + game.getStart() + "  use invitation code:  " + gameUsers.getCode());
            emailService.sendSimpleMessage(user.getEmail(), "Treasure Game Invitation", msgtext);
        }
    }
    public Component NumberFieldStep(NumberField numberField) {
        this.duration=numberField;
        numberField.setStep(0.5);
        numberField.setValue(0.5);
        numberField.setHasControls(true);
        return numberField;
    }
    private void VirtualListSetup(Grid<User> grid) {
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, user) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> {
                        this.removeInvitation(user);
                        grid.getDataProvider().refreshAll();
                    });
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Manage").setTextAlign(ColumnTextAlign.END).setWidth("100px");
        grid.setAllRowsVisible(true);
        grid.setItems(invitedusers);
        add(grid);
    }

    private void removeInvitation(User user) {
        if (user == null)
            return;
        invitedusers.remove(user);
    }
}
