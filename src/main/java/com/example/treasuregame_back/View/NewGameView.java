package com.example.treasuregame_back.View;

import com.example.treasuregame_back.Email.EmailServiceImpl;
import com.example.treasuregame_back.GameUsers.GameUsers;
import com.example.treasuregame_back.GameUsers.GameUsersService;
import com.example.treasuregame_back.game.Game;
import com.example.treasuregame_back.game.GameService;
import com.example.treasuregame_back.user.User;
import com.example.treasuregame_back.user.UserRepository;
import com.example.treasuregame_back.user.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.awt.*;

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


    public NewGameView(GameService service){
        var binder = new Binder<>(Game.class);
        binder.bindInstanceFields(this);

        add(
                new H1("New Game"),
                new FormLayout(name,start,NumberFieldStep(duration),x,y,w,z),
                validEmailField,
                new Button("Save", event ->{
                    var game = new Game();
                    User user = new User();
                    user = saveUserIfnotExist(user);
                    service.add(game);
                    binder.writeBeanIfValid(game);
                    service.addGameWithUser(game,user);
                    Notification.show("Game Saved.");
                    binder.readBean(new Game());
                    String msgtext;
                    GameUsers gameUsers = gameUsersService.searchCode(game, user);
                    msgtext = String.format("You have been invited to game: "+game.getName()+"  starting at  "+game.getStart()+"  use invitation code:  " +gameUsers.getCode());
                    emailService.sendSimpleMessage(user.getEmail(),"Treasure Game Invitation",msgtext);
                })

        );
    }



    public Component NumberFieldStep(NumberField numberField) {
        this.duration=numberField;
        numberField.setStep(0.5);
        numberField.setValue(0.5);
        numberField.setHasControls(true);
        return numberField;
    }
    public User saveUserIfnotExist(User user){
        user.setEmail(validEmailField.getValue());
        if(userService.IfUserExist(user)) {
            user = userService.findUserByEmail(user.getEmail());
            return user;
        }
        else{
            userService.add(user);
            return user;
        }
    }
}
