package com.example.treasuregame_back.View;

import com.example.treasuregame_back.Email.EmailServiceImpl;
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
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@Route("new")
@RolesAllowed("ADMIN")
public class NewGameView extends VerticalLayout  {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private GameUsersService gameUsersService;
    private TextField name = new TextField("Name");
    private DateTimePicker start = new DateTimePicker("Starts");
    private NumberField duration = new NumberField("Duration (hours)");

    private NumberField x = new NumberField("X");
    private NumberField y = new NumberField("Y");
    private NumberField w = new NumberField("W");
    private NumberField z = new NumberField("Z");

    private EmailField validEmailField = new EmailField("Email Adress:");
    private List<User> invitedusers = new ArrayList<User>();


    public NewGameView(GameService service){
        var binder = new Binder<>(Game.class);
        binder.bindInstanceFields(this);
        Grid<User> grid = new Grid<>(User.class, false);
        validEmailField.setPattern("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        validEmailField.setClearButtonVisible(true);
        validEmailField.setErrorMessage("Please enter a valid email address");
        Button addusertolistbutton = new Button("Add to list");
        addusertolistbutton.addClickListener(e ->{
           invitedusers.add(new User(validEmailField.getValue()));
           grid.getDataProvider().refreshAll();
           validEmailField.clear();
           Notification.show("User added");
        });

        add(
                new H1("New Game"),
                new FormLayout(name,start,NumberFieldStep(duration),x,y,w,z),
                validEmailField,addusertolistbutton,
                new Button("Save", event ->{
                    var game = new Game();
                    Long savedgameid = service.getNextVal();
                    binder.writeBeanIfValid(game);
                    service.add(game);
                    Notification.show("Game Saved.");
                    binder.readBean(new Game());
                    game.setId(savedgameid);
                    for(int i=0;i<invitedusers.size();i++) {
                        GameUsers gameUsers = new GameUsers();
                        gameUsers.setGame(game);
                        User user = saveUserIfnotExist(invitedusers.get(i));
                        gameUsers.setUser(user);
                        gameUsers.setCode(service.getRandomNumber());
                        gameUsersService.add(gameUsers);
                        sendmail(game,user);
                        Notification.show("invited member: "+invitedusers.get(i).getEmail()+"\nto game: "+game.getName());
                    }
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
    public User saveUserIfnotExist(User user){
        if(userService.IfUserExist(user)) {
            user = userService.findUserByEmail(user.getEmail());
            return user;
        }
        else{
            userService.add(user);
            user = userService.findUserByEmail(user.getEmail());
            return user;
        }
    }
    private void VirtualListSetup(Grid<User> grid) {
        grid.addColumn(User::getEmail).setHeader("Email");
        grid.setAllRowsVisible(true);
        grid.setItems(invitedusers);
        add(grid);
    }
}
